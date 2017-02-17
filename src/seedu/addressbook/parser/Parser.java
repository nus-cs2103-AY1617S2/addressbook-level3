package seedu.addressbook.parser;

import seedu.addressbook.commands.*;
import seedu.addressbook.common.Messages;
import seedu.addressbook.data.exception.IllegalValueException;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static seedu.addressbook.common.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

/**
 * Parses user input.
 */
public class Parser {

    public static final Pattern PERSON_INDEX_ARGS_FORMAT = Pattern.compile("(?<targetIndex>.+)");

    public static final Pattern KEYWORDS_ARGS_FORMAT =
            Pattern.compile("(?<keywords>\\S+(?:\\s+\\S+)*)"); // one or more keywords separated by whitespace

    public static final Pattern PERSON_DATA_ARGS_FORMAT = // '/' forward slashes are reserved for delimiter prefixes
            Pattern.compile("(?<name>[^/]+)"
                    + " (?<isPhonePrivate>p?)p/(?<phone>[^/]+)"
                    + " (?<isEmailPrivate>p?)e/(?<email>[^/]+)"
                    + " (?<isAddressPrivate>p?)a/(?<address>[^/]+)"
                    + "(?<tagArguments>(?: t/[^/]+)*)"); // variable number of tags

    /**
     * Signals that the user input could not be parsed.
     */
    public static class ParseException extends Exception {
        ParseException(String message) {
            super(message);
        }
    }

    /**
     * Used for initial separation of command word and args.
     */
    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     */
    public Command parseCommand(String userInput) {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

            case AddCommand.COMMAND_WORD:
                return prepareAdd(arguments);

            case DeleteCommand.COMMAND_WORD:
                return prepareDelete(arguments);

            case ClearCommand.COMMAND_WORD:
                return new ClearCommand();

            case FindCommand.COMMAND_WORD:
                return prepareFind(arguments);

            case ListCommand.COMMAND_WORD:
                return new ListCommand();

            case ViewCommand.COMMAND_WORD:
                return prepareView(arguments);

            case ViewAllCommand.COMMAND_WORD:
                return prepareViewAll(arguments);

            case ExitCommand.COMMAND_WORD:
                return new ExitCommand();

            case HelpCommand.COMMAND_WORD:
                return new HelpCommand();

            default:
                return suggestCommand(commandWord);
        }
    }

    /**
     * Parses arguments in the context of the add person command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    private Command prepareAdd(String args){
        final Matcher matcher = PERSON_DATA_ARGS_FORMAT.matcher(args.trim());
        // Validate arg string format
        if (!matcher.matches()) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }
        try {
            return new AddCommand(
                    matcher.group("name"),

                    matcher.group("phone"),
                    isPrivatePrefixPresent(matcher.group("isPhonePrivate")),

                    matcher.group("email"),
                    isPrivatePrefixPresent(matcher.group("isEmailPrivate")),

                    matcher.group("address"),
                    isPrivatePrefixPresent(matcher.group("isAddressPrivate")),

                    getTagsFromArgs(matcher.group("tagArguments"))
            );
        } catch (IllegalValueException ive) {
            return new IncorrectCommand(ive.getMessage());
        }
    }

    /**
     * Checks whether the private prefix of a contact detail in the add command's arguments string is present.
     */
    private static boolean isPrivatePrefixPresent(String matchedPrefix) {
        return matchedPrefix.equals("p");
    }

    /**
     * Extracts the new person's tags from the add command's tag arguments string.
     * Merges duplicate tag strings.
     */
    private static Set<String> getTagsFromArgs(String tagArguments) throws IllegalValueException {
        // no tags
        if (tagArguments.isEmpty()) {
            return Collections.emptySet();
        }
        // replace first delimiter prefix, then split
        final Collection<String> tagStrings = Arrays.asList(tagArguments.replaceFirst(" t/", "").split(" t/"));
        return new HashSet<>(tagStrings);
    }


    /**
     * Parses arguments in the context of the delete person command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    private Command prepareDelete(String args) {
        try {
            final int targetIndex = parseArgsAsDisplayedIndex(args);
            return new DeleteCommand(targetIndex);
        } catch (ParseException | NumberFormatException e) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Parses arguments in the context of the view command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    private Command prepareView(String args) {

        try {
            final int targetIndex = parseArgsAsDisplayedIndex(args);
            return new ViewCommand(targetIndex);
        } catch (ParseException | NumberFormatException e) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ViewCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Parses arguments in the context of the view all command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    private Command prepareViewAll(String args) {

        try {
            final int targetIndex = parseArgsAsDisplayedIndex(args);
            return new ViewAllCommand(targetIndex);
        } catch (ParseException | NumberFormatException e) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ViewAllCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Parses the given arguments string as a single index number.
     *
     * @param args arguments string to parse as index number
     * @return the parsed index number
     * @throws ParseException if no region of the args string could be found for the index
     * @throws NumberFormatException the args string region is not a valid number
     */
    private int parseArgsAsDisplayedIndex(String args) throws ParseException, NumberFormatException {
        final Matcher matcher = PERSON_INDEX_ARGS_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            throw new ParseException("Could not find index number to parse");
        }
        return Integer.parseInt(matcher.group("targetIndex"));
    }


    /**
     * Parses arguments in the context of the find person command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    private Command prepareFind(String args) {
        final Matcher matcher = KEYWORDS_ARGS_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    FindCommand.MESSAGE_USAGE));
        }

        // keywords delimited by whitespace
        final String[] keywords = matcher.group("keywords").split("\\s+");
        final Set<String> keywordSet = new HashSet<>(Arrays.asList(keywords));
        return new FindCommand(keywordSet);
    }

    /**
     * Feedback a suggested command to the user.
     *
     * @param incorrectCommandWord the incorrect command word
     * @return the suggested command
     */
    private IncorrectCommand suggestCommand(String incorrectCommandWord) {
        String suggestedCommandWord = findMostProbableCommand(incorrectCommandWord);
        switch (suggestedCommandWord) {

        case AddCommand.COMMAND_WORD:
            return new IncorrectCommand(String.format(Messages.MESSAGE_DID_YOU_MEAN_THIS,
                    AddCommand.MESSAGE_USAGE));

        case DeleteCommand.COMMAND_WORD:
            return new IncorrectCommand(String.format(Messages.MESSAGE_DID_YOU_MEAN_THIS,
                    DeleteCommand.MESSAGE_USAGE));

        case ClearCommand.COMMAND_WORD:
            return new IncorrectCommand(String.format(Messages.MESSAGE_DID_YOU_MEAN_THIS,
                    ClearCommand.MESSAGE_USAGE));

        case FindCommand.COMMAND_WORD:
            return new IncorrectCommand(String.format(Messages.MESSAGE_DID_YOU_MEAN_THIS,
                    FindCommand.MESSAGE_USAGE));

        case ListCommand.COMMAND_WORD:
            return new IncorrectCommand(String.format(Messages.MESSAGE_DID_YOU_MEAN_THIS,
                    ListCommand.MESSAGE_USAGE));

        case ViewCommand.COMMAND_WORD:
            return new IncorrectCommand(String.format(Messages.MESSAGE_DID_YOU_MEAN_THIS,
                    ViewCommand.MESSAGE_USAGE));

        case ViewAllCommand.COMMAND_WORD:
            return new IncorrectCommand(String.format(Messages.MESSAGE_DID_YOU_MEAN_THIS,
                    ViewAllCommand.MESSAGE_USAGE));

        case ExitCommand.COMMAND_WORD:
            return new IncorrectCommand(String.format(Messages.MESSAGE_DID_YOU_MEAN_THIS,
                    ExitCommand.MESSAGE_USAGE));

        case HelpCommand.COMMAND_WORD:
            // Fallthrough

        default:
            return new IncorrectCommand(String.format(Messages.MESSAGE_DID_YOU_MEAN_THIS,
                    HelpCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Find the most probable command word.
     *
     * Guarantees that the most probable command word always exist in the list of all command words
     *
     * @param incorrectCommandWord the incorrect command word
     * @return the most probable command word
     */
    private String findMostProbableCommand(String incorrectCommandWord) {
        final String[] allCommandWords = { AddCommand.COMMAND_WORD,
                DeleteCommand.COMMAND_WORD,
                ClearCommand.COMMAND_WORD,
                FindCommand.COMMAND_WORD,
                ListCommand.COMMAND_WORD,
                ViewCommand.COMMAND_WORD,
                ViewAllCommand.COMMAND_WORD,
                HelpCommand.COMMAND_WORD,
                ExitCommand.COMMAND_WORD };

        String mostProbableCommandWord = "";
        int lowestLevenshteinDistance = Integer.MAX_VALUE;
        // Obtain the most probable command word with the lowest Levenshtein's distance
        for (String commandWord : allCommandWords) {
            int levenshteinDistance = computeLevenshteinDistance(incorrectCommandWord, commandWord);
            if (levenshteinDistance < lowestLevenshteinDistance) {
                lowestLevenshteinDistance = levenshteinDistance;
                mostProbableCommandWord = commandWord;
            } else if (levenshteinDistance == lowestLevenshteinDistance
                    && commandWord.compareToIgnoreCase(mostProbableCommandWord) < 0 ) {
                // If tie breaker exist, take the lexicographically smaller command word
                mostProbableCommandWord = commandWord;
            }
        }

        return mostProbableCommandWord;
    }

    /**
     * Computes the Levenshtein's distance between two strings.
     *
     * @param string1 and string2
     * @return the Levenshtein's distance between string1 and string2
     */
    private static int computeLevenshteinDistance(String string1, String string2) {
        int[][] distance = new int[string1.length() + 1][string2.length() + 1];

        for (int i = 0; i <= string1.length(); i++) {
            distance[i][0] = i;
        }
        for (int j = 1; j <= string2.length(); j++) {
            distance[0][j] = j;
        }

        for (int i = 1; i <= string1.length(); i++) {
            for (int j = 1; j <= string2.length(); j++) {
                distance[i][j] = Math.min(
                        Math.min(distance[i - 1][j] + 1,
                                 distance[i][j - 1] + 1),
                        distance[i - 1][j - 1] + ((string1.charAt(i - 1) == string2.charAt(j - 1)) ? 0 : 1));
            }
        }

        return distance[string1.length()][string2.length()];
    }
}
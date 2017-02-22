package seedu.addressbook.parser;

import seedu.addressbook.commands.*;
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

    public static final Pattern KEYWORDS_ARGS_FORMAT = Pattern.compile("(?<keywords>\\S+(?:\\s+\\S+)*)"); // one
                                                                                                          // or
                                                                                                          // more
                                                                                                          // keywords
                                                                                                          // separated
                                                                                                          // by
                                                                                                          // whitespace

    public static final Pattern PERSON_DATA_ARGS_FORMAT = // '/' forward slashes
                                                          // are reserved for
                                                          // delimiter prefixes
            Pattern.compile("(?<name>[^/]+)" + " (?<isPhonePrivate>p?)p/(?<phone>[^/]+)"
                    + " (?<isEmailPrivate>p?)e/(?<email>[^/]+)" + " (?<isAddressPrivate>p?)a/(?<address>[^/]+)"
                    + "(?<tagArguments>(?: t/[^/]+)*)"); // variable number of
                                                         // tags

    public static final Pattern NAME_ARGS_FORMAT = Pattern.compile("(?<targetIndex>.+)" + "(?<name>[^/]+)");

    public static final Pattern PHONE_ARGS_FORMAT = Pattern
            .compile("(?<targetIndex>.+)" + " (?<isPhonePrivate>p?)p/(?<phone>[^/]+)");

    public static final Pattern EMAIL_ARGS_FORMAT = Pattern
            .compile("(?<targetIndex>.+)" + " (?<isEmailPrivate>p?)e/(?<email>[^/]+)");

    public static final Pattern ADDRESS_ARGS_FORMAT = Pattern
            .compile("(?<targetIndex>.+)" + " (?<isAddressPrivate>p?)a/(?<address>[^/]+)");

    public static final Pattern TAG_ARGS_FORMAT = Pattern
            .compile("(?<targetIndex>.+)" + "(?<tagArguments>(?: t/[^/]+)*)");

    public static List<Pattern> ARGS_FORMAT = new ArrayList<Pattern>();

    public Parser() {
        ARGS_FORMAT.add(NAME_ARGS_FORMAT);
        ARGS_FORMAT.add(PHONE_ARGS_FORMAT);
        ARGS_FORMAT.add(EMAIL_ARGS_FORMAT);
        ARGS_FORMAT.add(ADDRESS_ARGS_FORMAT);
        ARGS_FORMAT.add(TAG_ARGS_FORMAT);
    }

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
     * @param userInput
     *            full user input string
     * @return the command based on the user input
     * @throws IllegalValueException 
     * @throws NumberFormatException 
     */
    public Command parseCommand(String userInput) throws NumberFormatException, IllegalValueException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            return prepareAdd(arguments);

        case EditCommand.COMMAND_WORD:
            return prepareEdit(arguments);

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

        case HelpCommand.COMMAND_WORD: // Fallthrough
        default:
            return new HelpCommand();
        }
    }

    /**
     * Parses arguments in the context of the edit person command.
     *
     * @param args
     *            full command args string
     * @return the prepared command
     * @throws IllegalValueException 
     * @throws NumberFormatException 
     */
    private Command prepareEdit(String args) throws NumberFormatException, IllegalValueException {
        boolean isMatched = false;
        Matcher[] matchers = new Matcher[ARGS_FORMAT.size()];

        for (int i = 0; i < ARGS_FORMAT.size(); i++) {
            matchers[i] = ARGS_FORMAT.get(i).matcher(args.trim());
        }
        // Validate arg string format
        for (int i = 0; i < ARGS_FORMAT.size(); i++) {
            if (matchers[i].matches()) {
                isMatched = true;
            }
        }
        
        if (!isMatched) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        if (matchers[0].matches()) {
            return new EditCommand(Integer.parseInt(matchers[0].group("targetIndex")), 0, matchers[0].group("name"));
        }
        if (matchers[1].matches()) {
            return new EditCommand(Integer.parseInt(matchers[1].group("targetIndex")), 1, matchers[1].group("phone"),
                    isPrivatePrefixPresent(matchers[1].group("isPhonePrivate")));
        }
        if (matchers[2].matches()) {
            return new EditCommand(Integer.parseInt(matchers[2].group("targetIndex")), 2, matchers[2].group("email"),
                    isPrivatePrefixPresent(matchers[2].group("isEmailPrivate")));
        }
        if (matchers[3].matches()) {
            return new EditCommand(Integer.parseInt(matchers[3].group("targetIndex")), 3, matchers[3].group("address"),
                    isPrivatePrefixPresent(matchers[3].group("isAddressPrivate")));
        }

        try {
            return new EditCommand(Integer.parseInt(matchers[4].group("targetIndex")), 4,
                    getTagsFromArgs(matchers[4].group("tagArguments")));
        } catch (IllegalValueException ive) {
            return new IncorrectCommand(ive.getMessage());
        }
    }

    /**
     * Parses arguments in the context of the add person command.
     *
     * @param args
     *            full command args string
     * @return the prepared command
     */
    private Command prepareAdd(String args) {
        final Matcher matcher = PERSON_DATA_ARGS_FORMAT.matcher(args.trim());
        // Validate arg string format
        if (!matcher.matches()) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }
        try {
            return new AddCommand(matcher.group("name"),

                    matcher.group("phone"), isPrivatePrefixPresent(matcher.group("isPhonePrivate")),

                    matcher.group("email"), isPrivatePrefixPresent(matcher.group("isEmailPrivate")),

                    matcher.group("address"), isPrivatePrefixPresent(matcher.group("isAddressPrivate")),

                    getTagsFromArgs(matcher.group("tagArguments")));
        } catch (IllegalValueException ive) {
            return new IncorrectCommand(ive.getMessage());
        }
    }

    /**
     * Checks whether the private prefix of a contact detail in the add
     * command's arguments string is present.
     */
    private static boolean isPrivatePrefixPresent(String matchedPrefix) {
        return matchedPrefix.equals("p");
    }

    /**
     * Extracts the new person's tags from the add command's tag arguments
     * string. Merges duplicate tag strings.
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
     * @param args
     *            full command args string
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
     * @param args
     *            full command args string
     * @return the prepared command
     */
    private Command prepareView(String args) {

        try {
            final int targetIndex = parseArgsAsDisplayedIndex(args);
            return new ViewCommand(targetIndex);
        } catch (ParseException | NumberFormatException e) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Parses arguments in the context of the view all command.
     *
     * @param args
     *            full command args string
     * @return the prepared command
     */
    private Command prepareViewAll(String args) {

        try {
            final int targetIndex = parseArgsAsDisplayedIndex(args);
            return new ViewAllCommand(targetIndex);
        } catch (ParseException | NumberFormatException e) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewAllCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Parses the given arguments string as a single index number.
     *
     * @param args
     *            arguments string to parse as index number
     * @return the parsed index number
     * @throws ParseException
     *             if no region of the args string could be found for the index
     * @throws NumberFormatException
     *             the args string region is not a valid number
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
     * @param args
     *            full command args string
     * @return the prepared command
     */
    private Command prepareFind(String args) {
        final Matcher matcher = KEYWORDS_ARGS_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        // keywords delimited by whitespace
        final String[] keywords = matcher.group("keywords").split("\\s+");
        final Set<String> keywordSet = new HashSet<>(Arrays.asList(keywords));
        return new FindCommand(keywordSet);
    }

}
package seedu.addressbook.commands;

import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.parser.Parser;

import java.util.*;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case sensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" + "Finds all persons whose names contain any of "
            + "the specified keywords (case-sensitive) and displays them as a list with index numbers.\n\t"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n\t"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

    private final Set<String> keywords;

    public FindCommand(Set<String> keywords) {
        this.keywords = keywords;
    }

    /**
     * Returns copy of keywords in this command.
     */
    public Set<String> getKeywords() {
        return new HashSet<>(keywords);
    }

    @Override
    public CommandResult execute() {
        final List<ReadOnlyPerson> personsFound = getPersonsWithNameContainingAnyKeyword(keywords);
        return new CommandResult(getMessageForPersonListShownSummary(personsFound), personsFound);
    }

    /**
     * Retrieve all persons in the address book whose names contain some of the specified keywords.
     *
     * @param keywords for searching
     * @return list of persons found
     */
    private List<ReadOnlyPerson> getPersonsWithNameContainingAnyKeyword(Set<String> keywords) {
        final List<ReadOnlyPerson> matchedPersons = new ArrayList<>();
        for (ReadOnlyPerson person : addressBook.getAllPersons()) {
            Set<String> wordsInName = new HashSet<>(person.getName().getWordsInName());
            boolean foundFuzzyMatch = isNameContainsFuzzyKeyword(keywords, wordsInName);
            if (!Collections.disjoint(wordsInName, keywords) || foundFuzzyMatch) {
                matchedPersons.add(person);
            }
        }
        return matchedPersons;
    }

    /**
     * Checks if words in specified person's name are a fuzzy match to any of the search keywords.
     * 
     * @param keywords for searching
     * @param wordsInName words in specified person's name
     * @return true if name is a fuzzy match to any search keyword //TODO: figure out how to write this
     */
    private boolean isNameContainsFuzzyKeyword(Set<String> keywords, Set<String> wordsInName) {
        boolean isFuzzyMatch = false;
        for (String word : wordsInName) {
            for (String keyword : keywords) {
                isFuzzyMatch = isFuzzyMatch || Parser.isMinEditDistanceAcceptable(word, keyword);
            }
        }
        return isFuzzyMatch;
    }

}

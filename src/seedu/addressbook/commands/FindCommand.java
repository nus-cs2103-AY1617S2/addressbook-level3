package seedu.addressbook.commands;

import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.*;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case sensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" + "Finds all persons whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n\t"
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
            final Set<String> wordsInName = new HashSet<>(person.getName().getWordsInName());
            final Set<String> detailsInAddress = new HashSet<>(person.getAddress().getBreakDownOfAddress());
            final Set<String> detailsInEmailAddress = new HashSet<>(person.getEmail().getBreakDownOfEmailAddress());
            final Set<String> detailsInPhone = new HashSet<>(person.getPhone().getBreakDownOfPhone());
            final Set<String> wordsInTag = new HashSet<>(person.getTags().getPersonTag());
            if (!Collections.disjoint(wordsInName, keywords)) {
                matchedPersons.add(person);
            } else if (!Collections.disjoint(detailsInAddress, keywords)) {
                matchedPersons.add(person);
            } else if (!Collections.disjoint(detailsInEmailAddress, keywords)) {
                matchedPersons.add(person);
            } else if (!Collections.disjoint(wordsInTag, keywords)) {
                matchedPersons.add(person);
            } else if (!Collections.disjoint(detailsInPhone, keywords)) {
                matchedPersons.add(person);
            }
        }
        return matchedPersons;
    }

}

package seedu.addressbook.commands;

import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.tag.Tag;

import java.util.*;

/**
 * Finds and lists all persons in address book who are tagged with the given tag
 * Tag matching is case-insensitive
 */
public class FindTagCommand extends Command {

    public static final String COMMAND_WORD = "findtag";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" + "Finds all persons who are tagged with "
            + "the specified tag (case-insensitive) and displays them as a list with index numbers.\n\t"
            + "Parameters: TAG\n\t"
            + "Example: " + COMMAND_WORD + " classmate";

    public static final String SQAURE_BRACKETS_REGEX = "[\\]";

    private final String tagName;

    public FindTagCommand(String tagName) {
        this.tagName = tagName;
    }

    @Override
    public CommandResult execute() {
        final List<ReadOnlyPerson> personsFound = getPersonsWithTag(tagName);
        return new CommandResult(getMessageForPersonListShownSummary(personsFound), personsFound);
    }

    /**
     * Retrieve all persons in the address book whose names contain some of the specified keywords.
     *
     * @param tagName for searching
     * @return list of persons found
     */
    private List<ReadOnlyPerson> getPersonsWithTag(String tagName) {
        final List<ReadOnlyPerson> matchedPersons = new ArrayList<>();
        for (ReadOnlyPerson person : addressBook.getAllPersons()) {
            if(personHasTag(tagName, person)) {
                matchedPersons.add(person);
            }
        }
        return matchedPersons;
    }

    private boolean personHasTag(String tagName, ReadOnlyPerson person) {
        Iterable<Tag> personsTags = person.getTags();
        for(Tag currTag : personsTags) {
            String currTagName = currTag.getRawName();
            if(currTagName.equals(tagName)) {
                return true;
            }
        }
        return false;
    }

}

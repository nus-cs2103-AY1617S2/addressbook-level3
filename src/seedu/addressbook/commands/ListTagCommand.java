package seedu.addressbook.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.addressbook.data.person.*;
import seedu.addressbook.data.tag.Tag;
import seedu.addressbook.data.tag.UniqueTagList;

/**
 * List all contacts with a specific tag
 */
public class ListTagCommand extends Command{

	public static final String COMMAND_WORD = "listTag";
	public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" 
	+ "Displays all persons in the address book with the specified tag.\n\t"
	            + "Example: " + COMMAND_WORD + " friend";
	private final Set<Tag> tag;
	
	public ListTagCommand(Set<Tag> tag) {
		this.tag = tag;
	}
	
	@Override
	public CommandResult execute() {
		final List<ReadOnlyPerson> personsFound = getPersonsWithSpecifiedTag(tag);
        return new CommandResult(getMessageForPersonListShownSummary(personsFound), personsFound);
	}
	
	/**
     * Retrieve all persons in the address book who has the specified tags.
     *
     * @param tag for searching
     * @return list of persons found
     */
    private List<ReadOnlyPerson> getPersonsWithSpecifiedTag(Set<Tag> tag) {
        final List<ReadOnlyPerson> matchedPersons = new ArrayList<>();
        
        for (ReadOnlyPerson person : addressBook.getAllPersons()) {
            final Set<Tag> wordsInTag = person.getTags().toSet();
            if (!Collections.disjoint(wordsInTag, tag)) {
                matchedPersons.add(person);
            }
        }
        return matchedPersons;
    }

}

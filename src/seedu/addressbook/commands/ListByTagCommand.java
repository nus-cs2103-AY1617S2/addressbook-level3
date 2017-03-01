package seedu.addressbook.commands;

import java.util.ArrayList;
import java.util.List;

import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.tag.Tag;
import seedu.addressbook.data.tag.UniqueTagList;

public class ListByTagCommand extends Command {

	public static final String COMMAND_WORD = "listbytag";

	public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" + "Finds all persons whose tags contain "
			+ "the specified tag (case-sensitive) and displays them as a list with index numbers.\n\t"
			+ "Parameters: Tag \n\t" + "Example: " + COMMAND_WORD + " friend";

	private final String tag;

	public ListByTagCommand(String tag) {
		this.tag = tag;
	}

	@Override
	public CommandResult execute() {
		// TODO Auto-generated method stub
		final List<ReadOnlyPerson> personsFound = getPersonsWithGivenTag(tag);
		return new CommandResult(getMessageForPersonListShownSummary(personsFound), personsFound);
	}

	/**
	 * Retrieve all persons in the address book who have the given tag.
	 *
	 * @param given tag for searching
	 * @return list of persons found
	 * @param tag
	 * @return
	 */
	private List<ReadOnlyPerson> getPersonsWithGivenTag(String tag) {
		// TODO Auto-generated method stub
		Tag targetTag;
		final List<ReadOnlyPerson> matchedPersons = new ArrayList<>();
		try {
			targetTag = new Tag(tag);
			for (ReadOnlyPerson person : addressBook.getAllPersons()) {
				final UniqueTagList tags = person.getTags();
				if (tags.contains(targetTag)) {
					matchedPersons.add(person);
				}
			}
			return matchedPersons;
		} catch (IllegalValueException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return matchedPersons;
	}
}

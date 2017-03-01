package seedu.addressbook.commands;

import java.util.ArrayList;
import java.util.List;

import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.tag.Tag;

public class ListByTagCommand extends Command {
	public static final String COMMAND_WORD = "list_by_tag";
	
	public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n"
			+ "List all the persons with the specified tag.\n\t"
			+ "Examples: " + COMMAND_WORD + " friends";
	
	public static final String NO_TAG_SUPPLIED_MESSAGE = "please specify a tag";
	
	private final Tag tagSpecified;
	
	public ListByTagCommand(Tag tag) throws IllegalValueException {
		tagSpecified = tag;
	}
	
	private CommandResult execute(Tag tag) {
		List<ReadOnlyPerson> allPersons = addressBook.getAllPersons().immutableListView();
		List<ReadOnlyPerson> personsWithTag = filterPersonWithTag(allPersons, tag);
		return new CommandResult(getMessageForPersonListShownSummary(personsWithTag), personsWithTag);
	}
	
	private List<ReadOnlyPerson> filterPersonWithTag(List<ReadOnlyPerson> persons, Tag tag) {
		List<ReadOnlyPerson> filteredPersons = new ArrayList<ReadOnlyPerson>();
		for (ReadOnlyPerson person : persons) {
			if (person.getTags().contains(tag)) {
				filteredPersons.add(person);
			}
		}
		return filteredPersons;
	}
	
    @Override
    public CommandResult execute() {
        return execute(tagSpecified);
    }
}

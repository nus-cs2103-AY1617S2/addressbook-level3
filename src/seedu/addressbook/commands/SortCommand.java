package seedu.addressbook.commands;

import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * Lists all persons in the address book to the user in alphabetical order.
 */
public class SortCommand extends Command {
	
	public static final String COMMAND_WORD = "sort";
	
	public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" 
			 + "Displays all persons in the address book as a list with index numbers in alphabetical order.\n\t"
			 + "Example: " + COMMAND_WORD;


	@Override
	public CommandResult execute() {
		final Comparator<ReadOnlyPerson> nameComparator = new Comparator<ReadOnlyPerson>() {
			public int compare(ReadOnlyPerson personA, ReadOnlyPerson personB) {
				String nameA = personA.getName().toString();
				String nameB = personB.getName().toString();
				return nameA.compareTo(nameB);
			}
		};
		List<ReadOnlyPerson> allPersons = addressBook.getAllPersons().immutableListView();
		Collections.sort(allPersons, nameComparator);
	    return new CommandResult(getMessageForSortedPersonListShownSummary(allPersons), allPersons);
	}

}

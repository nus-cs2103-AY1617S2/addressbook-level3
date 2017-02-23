package seedu.addressbook.commands;

import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.person.UniquePersonList;

import java.util.List;

public class SortCommand extends Command{

	public static final String COMMAND_WORD = "sort";
	
	public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" + "Sorts all persons in addressbook according to name"
			+ "and displays them as a list with index numbers.\n\t"
			+ "Example: " + COMMAND_WORD;

	@Override
	public CommandResult execute() {
		UniquePersonList uniquePersonList = addressBook.getAllPersons();
		uniquePersonList.sortListByName();
		List<ReadOnlyPerson> allPersons = uniquePersonList.immutableListView();
		return new CommandResult(getMessageForPersonListShownSummary(allPersons), allPersons);
		
	}
	

}

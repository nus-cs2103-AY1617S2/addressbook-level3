package seedu.addressbook.commands;

public class SortCommand extends Command{

	public static final String COMMAND_WORD = "sort";
	
	public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" + "Sorts all persons in addressbook according to name"
			+ "and displays them as a list with index numbers.\n\t"
			+ "Example: " + COMMAND_WORD;
	

}

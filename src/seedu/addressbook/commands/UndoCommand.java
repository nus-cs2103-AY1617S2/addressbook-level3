package seedu.addressbook.commands;

abstract public class UndoCommand extends Command {

	public static final String COMMAND_WORD = "undo";

	public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" + "Undo a previous add/ delete command"
			+ "Example: " + COMMAND_WORD;

	public static final String MESSAGE_SUCCESS = "Previous command has been undo";

	//Get the last add/ delete command entered by the user

	private String previousCommand = Command.getLastCommand();

	//Get the last person added/ deleted by the comamand

	private Person previousPerson = Command.getLastPerson();

	public UndoCommand(){

		if(previousCommand = "add")
		{
			deleteCommand(previousPerson);
		}

		else if(previousCommand = "delete")
		{
			AddCommand(previousPerson);
		}

		else break;
	}

}

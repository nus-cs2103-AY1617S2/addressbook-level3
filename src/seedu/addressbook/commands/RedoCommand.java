package seedu.addressbook.commands;

import seedu.addressbook.common.Pair;
import seedu.addressbook.data.person.Person;

public class RedoCommand extends Command {
	
	public static final String COMMAND_WORD = "redo";
	
	public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" 
											   + "Redoes the previous addition or deletion undone to AddressBook";
	
	public static final String MESSAGE_SUCCESS = "Successfully redone previous undone action";
	public static final String MESSAGE_ERROR = "Cannot redo previous undone action";
	public static final String MESSAGE_NO_CHANGE = "No undone action to be redone";
	
	public RedoCommand() {
	}
	
	@Override
	public CommandResult execute() {
		try {
			Pair<String, Person> previousCommand = session.popRedoStack();
			if (previousCommand != null) {
				processCommand(previousCommand);
				return new CommandResult(MESSAGE_SUCCESS);
			} else {
				return new CommandResult(MESSAGE_NO_CHANGE);
			}
		} catch (Exception e) {
			return new CommandResult(MESSAGE_ERROR);
		}
	}
	
	private void processCommand(Pair<String, Person> prevCommand) throws Exception {
		String command = prevCommand.getFirst();
		Person person = prevCommand.getSecond();
		if (command.equals("add")) {
			addressBook.removePerson(person);
			session.pushUndoStack(new Pair<String, Person>("delete", person));
		} else {
			addressBook.addPerson(person);
			session.pushUndoStack(new Pair<String, Person>("add", person));
		}
	}
	
}

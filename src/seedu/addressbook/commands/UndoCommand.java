package seedu.addressbook.commands;

import seedu.addressbook.common.Pair;
import seedu.addressbook.data.person.Person;

public class UndoCommand extends Command {
	
	public static final String COMMAND_WORD = "undo";
	
	public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" 
											   + "Undoes the previous addition or deletion done to AddressBook";
	
	public static final String MESSAGE_SUCCESS = "Successfully undone previous change";
	public static final String MESSAGE_ERROR = "Cannot undo previous change";
	public static final String MESSAGE_NO_CHANGE = "No change to be undone";
	
	public UndoCommand() {
	}
	
	@Override
	public CommandResult execute() {
		try {
			Pair<String, Person> previousCommand = session.popUndoStack();
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
			session.pushRedoStack(new Pair<String, Person>("delete", person));
		} else {
			addressBook.addPerson(person);
			session.pushRedoStack(new Pair<String, Person>("add", person));
		}
	}
	
}

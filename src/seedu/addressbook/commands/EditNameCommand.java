package seedu.addressbook.commands;

import seedu.addressbook.common.Messages;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.UniquePersonList.PersonNotFoundException;

/**
 * Edit a person's name in address book.
 */
public class EditNameCommand extends Command {
	
	public static final String COMMAND_WORD = "editName";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" 
            + "Edits the name of the person identified by the index number used in the last person listing.\n\t"
            + "Parameters: INDEX NEW_NAME\n\t"
            + "Example: " + COMMAND_WORD + " 1" + " Christina";

    public static final String MESSAGE_EDIT_NAME_SUCCESS = "Name successfully edited.";
    
    public static final String MESSAGE_INVALID_INDEX = "Invalid index.";


    private String newName;
    
    public EditNameCommand(int targetVisibleIndex, String newName) {
    	super(targetVisibleIndex);
    	this.newName = newName;
    }

	@Override
	public CommandResult execute() {
		 try {
			 int targetIndex = getTargetIndex();
			 addressBook.editPersonName(targetIndex, this.newName);
			 return new CommandResult(String.format(MESSAGE_EDIT_NAME_SUCCESS));
		 } catch (PersonNotFoundException pnfe) {
			 return new CommandResult(Messages.MESSAGE_PERSON_NOT_IN_ADDRESSBOOK);
		 } catch (IllegalValueException e) {
			return new CommandResult(MESSAGE_INVALID_INDEX);
		}

	}
}

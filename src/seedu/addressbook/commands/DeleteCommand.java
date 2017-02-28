package seedu.addressbook.commands;

import seedu.addressbook.commands.exception.*;
import seedu.addressbook.common.Messages;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.person.UniquePersonList;
import seedu.addressbook.data.person.UniquePersonList.DuplicatePersonException;
import seedu.addressbook.data.person.UniquePersonList.PersonNotFoundException;


/**
 * Deletes a person identified using it's last displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" 
            + "Deletes the person identified by the index number used in the last person listing.\n\t"
            + "Parameters: INDEX\n\t"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "%1$s already exists in the address book";
    
    private boolean commandSuccess;


    public DeleteCommand(int targetVisibleIndex) {
        super(targetVisibleIndex);
        commandSuccess = false;
    }


    @Override
    public CommandResult execute() {
        try {
            final ReadOnlyPerson target = getTargetPerson();
            addressBook.removePerson(target);
            commandSuccess = true;
            commandHistory.push(this);
            return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, target));
        } catch (IndexOutOfBoundsException ie) {
            return new CommandResult(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        } catch (PersonNotFoundException pnfe) {
            return new CommandResult(Messages.MESSAGE_PERSON_NOT_IN_ADDRESSBOOK);
        }
    }


    @Override
    public boolean isMutating() {
        return true;
    }


    @Override
    public boolean isUndoable() {
        return commandSuccess;
    }


    @Override
    public String undo() throws UndoFailedException {
        final ReadOnlyPerson target = getTargetPerson();
        if (!isUndoable()) {
            throw new IllegalUndoOperationException(String.format("%s %s", COMMAND_WORD, target));
        } else {
            try {
                addressBook.addPerson(new Person(target));
            } catch (DuplicatePersonException e) {
                throw new UndoFailedException(String.format(MESSAGE_DUPLICATE_PERSON, target));
            }
            return String.format(MESSAGE_DELETE_PERSON_SUCCESS, target);
        }
    }

}

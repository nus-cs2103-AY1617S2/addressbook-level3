package seedu.addressbook.commands;

import seedu.addressbook.commands.exception.*;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" + "Clears address book and undo history permanently.\n\t"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Address book and undo history has been cleared!";

    @Override
    public CommandResult execute() {
        addressBook.clear();
        super.clearCommandHistory();
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean isMutating() {
        return true;
    }

    @Override
    public boolean isUndoable() {
        return false;
    }

    @Override
    public CommandResult undo() throws UndoFailedException {
        throw new IllegalUndoOperationException(COMMAND_WORD);
    }
}

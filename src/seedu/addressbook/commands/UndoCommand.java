package seedu.addressbook.commands;

import seedu.addressbook.commands.exception.*;

public class UndoCommand extends Command {
    
    public static final String COMMAND_WORD = "undo";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" + "Undoes the last change to the address book.\n\t"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Undone: %s";
    public static final String MESSAGE_EMPTY_HISTORY = "Nothing to undo!";
    public static final String MESSAGE_FAILURE = "Failed to undo: %s";

    @Override
    public CommandResult execute() {
        // TODO Auto-generated method stub
        while (!commandHistory.isEmpty()) {
            Command lastCommand = commandHistory.pop();
            if (!lastCommand.isUndoable()){
                continue;
            } else {
                try {
                    String undoResult = lastCommand.undo();
                    return new CommandResult(String.format(MESSAGE_SUCCESS, undoResult));
                } catch (UndoFailedException e) {
                    return new CommandResult(String.format(MESSAGE_FAILURE, e.getMessage()));
                }
            }
        }
        return new CommandResult(MESSAGE_EMPTY_HISTORY);
    }

    @Override
    public boolean isMutating() {
        return true;
    }

    @Override
    public boolean isUndoable() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public String undo() throws UndoFailedException {
        // TODO Auto-generated method stub
        throw new IllegalUndoOperationException(COMMAND_WORD);
    }

}

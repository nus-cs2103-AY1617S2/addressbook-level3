package seedu.addressbook.commands;

import seedu.addressbook.commands.exception.*;

/**
 * Represents an incorrect command. Upon execution, produces some feedback to the user.
 */
public class IncorrectCommand extends Command{

    private static final String COMMAND_WORD = "Incorrect Command";
    public final String feedbackToUser;

    public IncorrectCommand(String feedbackToUser){
        this.feedbackToUser = feedbackToUser;
    }

    @Override
    public CommandResult execute() {
        return new CommandResult(feedbackToUser);
    }

    @Override
    public boolean isMutating() {
        return false;
    }

    @Override
    public boolean isUndoable() {
        return false;
    }

    @Override
    public String undo() throws UndoFailedException {
        throw new IllegalUndoOperationException(COMMAND_WORD);
    }

}

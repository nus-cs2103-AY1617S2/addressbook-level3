package seedu.addressbook.commands;

import seedu.addressbook.commands.exception.UndoFailedException;

public class UndoCommand extends Command {

    public UndoCommand(int targetIndex) {
        super(targetIndex);
        // TODO Auto-generated constructor stub
    }

    public UndoCommand() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public CommandResult execute() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isMutating() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isUndoable() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public CommandResult undo() throws UndoFailedException {
        // TODO Auto-generated method stub
        return null;
    }

}

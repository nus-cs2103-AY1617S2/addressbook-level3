package seedu.addressbook.commands;

public abstract class RedoableCommand extends UndoableCommand{
    public abstract void redo() throws Exception;
}

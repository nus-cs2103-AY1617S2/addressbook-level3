package seedu.addressbook.commands;

public abstract class UndoableCommand extends Command{
    public abstract void undo() throws Exception;
}

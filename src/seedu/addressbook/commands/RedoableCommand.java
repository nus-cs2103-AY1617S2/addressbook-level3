package seedu.addressbook.commands;

public abstract class RedoableCommand extends Command{
    public abstract void redo();
}

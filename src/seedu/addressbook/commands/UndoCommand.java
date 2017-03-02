package seedu.addressbook.commands;

/* * 
 * Undo the last change to the address book in the current session
 */

public class UndoCommand extends Command{
    public static final String COMMAND_WORD = "undo";
    
    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" + "Undo last change to address "
            + "book in this session.\n\t"
            + "Example: " + COMMAND_WORD;
    
    public static final String MESSAGE_SUCCESS = "Last change has been undone!";

    @Override
    public CommandResult execute() {
        return new CommandResult(MESSAGE_SUCCESS);
    }
    
    @Override
    public boolean isMutating() {
        return true;
    }
    

    @Override
    public boolean isUndo() {
        return true;
    }
}

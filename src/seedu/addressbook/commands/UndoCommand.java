package seedu.addressbook.commands;

/**
 * Undoes the last command
 *
 */
public class UndoCommand extends Command {

    public static final String COMMAND_WORD = "undo";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" + "Undoes the last command that changed the information stored.\n\t"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "The last change of the information in the Address Book has been undone!";

    @Override
    public CommandResult execute() {
    	addressBook.undo();
        return new CommandResult(MESSAGE_SUCCESS);
    }
    
    private final boolean isMutating = true;
    
    @Override
    public boolean isMutating(){
    	return isMutating;
    }
}

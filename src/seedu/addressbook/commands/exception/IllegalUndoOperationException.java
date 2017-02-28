package seedu.addressbook.commands.exception;

/**
 * Signals that the undo operation was called illegally
 * @author Ang Zhi Yuan
 *
 */
public class IllegalUndoOperationException extends RuntimeException {
    
    public static final String ERROR_MESSAGE_PREFIX = "Error: tried to undo non-undoable command %s";

    public IllegalUndoOperationException(String command) {
        super(String.format(ERROR_MESSAGE_PREFIX, command));
        // TODO Auto-generated constructor stub
    }

}

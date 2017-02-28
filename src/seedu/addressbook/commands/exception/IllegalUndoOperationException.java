package seedu.addressbook.commands.exception;

/**
 * Signals that the undo operation was called illegally
 * @author Ang Zhi Yuan
 *
 */
public class IllegalUndoOperationException extends RuntimeException {

    public IllegalUndoOperationException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

}

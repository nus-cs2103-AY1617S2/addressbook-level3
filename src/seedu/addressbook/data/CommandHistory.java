package seedu.addressbook.data;

import java.util.*;

/**
 * Represents command history. Contains the previously typed commands
 *
 * - Every command will be recorded in command history, including invalid
 *   commands, undo-ed commands and the show-history command as well.
 * - The command history will not be saved in storage, meaning that it only 
 *   starts recording commands since the beginning of user session.
 */
public class CommandHistory {

    private final ArrayList<String> commands;

    /**
     * Creates an empty command history.
     */
    public CommandHistory() {
        this.commands = new ArrayList<String> ();
    }

    /**
     * Records a new command.
     * 
     * @param command a user command in text
     */
    public void recordCommand(String command) {
        commands.add(command);
    }

    /**
     * Clears all commands from history.
     */
    public void clear() {
        commands.clear();
    }

    /**
     * Returns the number of commands recorded
     */
    public int size() {
        return commands.size();
    }

    /**
     * Returns all commands recorded in reverse order
     * from how they were added.
     */
    public ArrayList<String> getAll() {
        ArrayList<String> list = new ArrayList<String>();
        for(String s : this.commands) {
            list.add(s);
        }
        Collections.reverse(list);
        return list;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CommandHistory // instanceof handles nulls
                && this.commands.equals(((CommandHistory) other).commands));
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(commands);
    }
}

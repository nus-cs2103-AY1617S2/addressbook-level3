package seedu.addressbook.commands;

import java.util.HashMap;

/** 
 * Checks if a command word is listed as an alternative command word, 
 * and executes the command in the same way as the standard command format.
 */

public class AlternativeCommand extends Command {
    
    private static HashMap<String, String> altCommands = new HashMap<>();
    
    public AlternativeCommand() {
        altCommands.put("create", AddCommand.COMMAND_WORD);
        altCommands.put("show", ListCommand.COMMAND_WORD);
        altCommands.put("search", FindCommand.COMMAND_WORD);
        altCommands.put("lookup", ViewCommand.COMMAND_WORD);
        altCommands.put("remove", DeleteCommand.COMMAND_WORD);
    }

    /** Returns true if the given commandWord is an alternative command word. */
    public boolean isAlternative(String commandWord) {
        return altCommands.containsKey(commandWord);
    }

    /** Returns the standard CommandWord for the given alternative word. */
    public String getStandardCommandWord(String commandWord) {
        return altCommands.get(commandWord);
    }

    @Override
    public CommandResult execute() {
        return null;
    }

}

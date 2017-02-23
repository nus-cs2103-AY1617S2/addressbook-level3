package seedu.addressbook.commands;

import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.List;
import java.util.ArrayList;

/**
 * Lists all commands entered since starting the program to the user.
 */
public class HistoryCommand extends Command {

    public static final String COMMAND_WORD = "history";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" 
            + "Displays all commands entered since starting the program, in sequential order.\n\t"
            + "Example: " + COMMAND_WORD;


    @Override
    public CommandResult execute() {
        ArrayList<String> allCommands = Command.getCommandList();
        return new CommandResult(getMessageForCommandListShownSummary(), allCommands);
    }
}

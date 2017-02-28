package seedu.addressbook.commands;

import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.ui.Formatter;

import java.util.ArrayList;
import java.util.List;


/**
 * Lists all past commands in the current session to the user.
 */
public class HistoryCommand extends Command {

    public static final String COMMAND_WORD = "history";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" 
            + "Displays all past commands in the current session as a list with index numbers.\n\t"
            + "Example: " + COMMAND_WORD;


    @Override
    public CommandResult execute() {
    	ArrayList<Command> history = session.getHistory();
        List<ReadOnlyPerson> allPersons = addressBook.getAllPersons().immutableListView();
        return new CommandResult(new Formatter().format(history));
    }
}

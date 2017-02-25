package seedu.addressbook.commands;

import java.util.ArrayList;

/**
 * Views command history
 */
public class ViewHistoryCommand extends Command {

    public static final String COMMAND_WORD = "history";

    public static final String MESSAGE_USAGE = COMMAND_WORD;
    public static final String COMMAND_HISTORY_LINE_FORMAT = "%1$. %2$\n";

    /**
     * Default empty constructor.
     */
    public ViewHistoryCommand() {
    }

    /**
     * Constructs a feedback message to display command history
     * 
     * @return feedback message
     */
    public String getMessageForCommandHistoryShow() {
        StringBuilder message = new StringBuilder();
        ArrayList<String> commandList = commandHistory.getAll();
        int displayIndex = 1;
        for(String command : commandList) {
            message.append(String.format(COMMAND_HISTORY_LINE_FORMAT, displayIndex, command));
            displayIndex++;
        }
        return message.toString();
    }

    @Override
    public CommandResult execute() {
        return new CommandResult(getMessageForCommandHistoryShow());
    }

}

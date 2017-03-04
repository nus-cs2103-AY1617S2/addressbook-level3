package seedu.addressbook.commands;

import static seedu.addressbook.common.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.addressbook.data.MostRecentCommand;
import seedu.addressbook.parser.Parser;
import seedu.addressbook.parser.Parser.ParseException;

public class UndoCommand extends Command {
    public static final String COMMAND_WORD = "undo";
    
    public static final String MESSAGE_USAGE = COMMAND_WORD ;

    public static final String MESSAGE_SUCCESS = "Successfully undone previous change";
    public static final String MESSAGE_ERROR = "Cannot undo previous change";
    public static final String MESSAGE_NO_CHANGE = "No change to be undone";
    
    public UndoCommand() {
    }
    
    @Override
    public CommandResult execute() {
        String recentCommand = "";
        recentCommand = mostRecent.getMostRecent();
        String trimmedRecent = recentCommand.trim();

        try {
            if (!trimmedRecent.equals("")) {
                processCommand(trimmedRecent);
                return new CommandResult(MESSAGE_SUCCESS);
            } else {
                return new CommandResult(MESSAGE_NO_CHANGE);
            }
        } catch (Exception e) {
            return new CommandResult(trimmedRecent);
        }
    }
    
    private void processCommand(String command) throws Exception {
        String[] recentArray = command.split(" ");
        
        if(recentArray[0].equals("add")){
            int index = addressBook.getSize();  
            addressBook.removePersonByIndex(index-1);
        }
    }



}

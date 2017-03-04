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

    private static final String MESSAGE_UNDO_COMMAND_SUCCESS = "Undo successful";
    
    public static final Pattern PERSON_INDEX_ARGS_FORMAT = Pattern.compile("(?<targetIndex>.+)");
    
    
    @Override
    public CommandResult execute() {
        String recentCommand = mostRecent.getMostRecent();
        String trimmedRecent = recentCommand.trim();
        String[] recentArray = trimmedRecent.split(" ");
        String help = "help";

        if(recentArray[0].equals("add")){
            int index = addressBook.getSize();
            recentCommand = ("delete " + index);
            help = recentCommand;
            prepareDelete(recentCommand);
            
        }
        
        else if(recentArray[0].equals("delete")){
            // call add
        }
        else if(recentArray[0].equals("edit")){
            // undo edit
        }
        return new CommandResult(String.format(help));
    }
    
    private Command prepareDelete(String args) {
        try {
            final int targetIndex = parseArgsAsDisplayedIndex(args);
            return new DeleteCommand(targetIndex);
        } catch (ParseException | NumberFormatException e) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
        }
    }
    
    private int parseArgsAsDisplayedIndex(String args) throws ParseException, NumberFormatException {
        final Matcher matcher = PERSON_INDEX_ARGS_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            throw new ParseException("Could not find index number to parse");
        }
        return Integer.parseInt(matcher.group("targetIndex"));
    }
    
    public static class ParseException extends Exception {
        ParseException(String message) {
            super(message);
        }
    }
    
    

}

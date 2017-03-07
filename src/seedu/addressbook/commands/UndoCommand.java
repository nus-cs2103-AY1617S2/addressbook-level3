package seedu.addressbook.commands;

import seedu.addressbook.data.person.Person;

/* 
 * Undo the previous command
 */
public class UndoCommand extends Command {
    public static final String COMMAND_WORD = "undo";
    
    public static final String MESSAGE_USAGE = COMMAND_WORD ;

    public static final String MESSAGE_SUCCESS = "Successfully undone previous change";
    public static final String MESSAGE_ERROR = "Cannot undo previous change";
    public static final String MESSAGE_NO_CHANGE = "No change to be undone";
    
    /**
     * Default empty constructor.
     */
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
                mostRecent.recordCommand("");
                return new CommandResult(MESSAGE_SUCCESS);
            } else {
                return new CommandResult(MESSAGE_NO_CHANGE);
            }
        } catch (Exception e) {
            return new CommandResult(trimmedRecent);
        }
    }
    
    /*
     * Process undo command and make changes to address book
     */
    private void processCommand(String command) throws Exception {
        String[] recentArray = command.split(" ");
        
        if(recentArray[0].equals("add")){
            int index = addressBook.getSize();  
            addressBook.removePersonByIndex(index-1);
        }
        else if(recentArray[0].equals("delete")){
            Person toAdd = mostRecent.getPerson();
            addressBook.addPerson(toAdd);
        }
    }
}

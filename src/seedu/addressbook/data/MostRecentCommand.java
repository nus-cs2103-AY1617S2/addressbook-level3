package seedu.addressbook.data;


/**
 * Contains previously typed commands that makes changes to data in address book.
 * (can be modified in the future to contain a stack of commands to undo multiple commands) 
 *
 * - Commands such as add, delete and edit will be recorded in most recent command.
 * - The most recent command will not be saved in storage
 */

public class MostRecentCommand {
    private String command;
    
    /** 
     * Creates an empty string at the start of program
     */
    public MostRecentCommand() {
        this.command = "";
    }
    
    /**
     * Records a new command.
     * 
     * @param a user command that changes data in address book
     */
    public void recordCommand(String command) {
        if(modifiesData(command)){
            this.command = command;
        }
    }

    /** 
     * Checks if command passed by user modifies data and can be undone
     * @param a user command
     */
    private boolean modifiesData(String command){
        String trimmedCommand = command.trim();
        String[] commandArray = trimmedCommand.split(" ");
        if(commandArray[0].equals("add") || commandArray[0].equals("edit") ||
           commandArray[0].equals("delete")){
            return true;
        }
        return false;
    }

    public String getMostRecent() {
        return this.command;
    }

}

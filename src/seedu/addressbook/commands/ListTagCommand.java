package seedu.addressbook.commands;

import seedu.addressbook.data.tag.Tag;
import seedu.addressbook.data.tag.UniqueTagList;

public class ListTagCommand extends Command{
    
    public static final String COMMAND_WORD = "list-tag";
    
    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" 
            + "Displays all tags in the address book as a list with index numbers.\n\t"
            + "Example: " + COMMAND_WORD;
    
    @Override
    public CommandResult execute() {
        UniqueTagList allTags = addressBook.getAllTags();
        String tags = "";
        for(Tag tag:allTags){
            tags += tag.toString();
        }
        return new CommandResult(tags);
    }

}

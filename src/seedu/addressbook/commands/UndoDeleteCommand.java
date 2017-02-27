package seedu.addressbook.commands;

import seedu.addressbook.common.Messages;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.UniquePersonList;

public class UndoDeleteCommand extends Command{
    
    public static final String COMMAND_WORD = "undo-delete";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" 
            + "Undo last deletino."
            + "Example: " + COMMAND_WORD;
    public static final String MESSAGE_UNDODELETE_PERSON_SUCCESS = "Deletion undone.";
    
    @Override
    public CommandResult execute(ReadOnlyCommand previousCommand) {
        if (!previousCommand.getCommandWord().equals("delete")){
            return new CommandResult(Messages.MESSAGE_NOT_DELETE_COMMAND);
        }
        try{
            addressBook.addPerson((Person) previousCommand.getRelevantPersons().get(0));
        } catch (UniquePersonList.DuplicatePersonException dpe) {
            return new CommandResult(Messages.MESSAGE_DUPLICATE_PERSON);
        }
        return new CommandResult(MESSAGE_UNDODELETE_PERSON_SUCCESS);
    }

    @Override
    public String getCommandWord() {
        return COMMAND_WORD;
    }

}

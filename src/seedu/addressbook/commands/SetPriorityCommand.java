package seedu.addressbook.commands;

import seedu.addressbook.common.Messages;
import seedu.addressbook.data.person.ReadOnlyPerson;

public class SetPriorityCommand extends Command{
    
    public static final String COMMAND_WORD = "reprioritize";
    
    public static final String MESSAGE_INVALID_PRIORITIZE_LEVEL = "The prioritize level should between 1 to 3.";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" 
            + "Repriorize the person identified by the index number used in the last person listing.\n\t"
            + "Parameters: INDEX NEW_PRIORITIZE_LEVEL\n\t"
            + "Example: " + COMMAND_WORD + " 1" + " 3";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Repriorize Person: %1$s";

    private int newLevel;

    public SetPriorityCommand(int targetVisibleIndex, int newLevel) {
        super(targetVisibleIndex);
        this.newLevel = newLevel;
    }

    @Override
    public CommandResult execute() {
        try {
            final ReadOnlyPerson target = getTargetPerson();
            addressBook.repriorize(target, this.newLevel);
            return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, target));

        } catch (IndexOutOfBoundsException ie) {
            return new CommandResult(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
    }
    
    
}

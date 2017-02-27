package seedu.addressbook.commands;


import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.Person;

public abstract class PriorityCommand extends Command{
    public static final String COMMAND_WORD = "priority";
    
    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" + "prioritise your contacts. "
            + "Parameters: NAME PRIORITY_LEVEL...\n"
            + "Example: " + COMMAND_WORD
            + "John Doe High"; 
    public static final String MESSAGE_SUCCESS = "Priority Updated! : %1$s"; //%1$2 refer to Formatter Java;
    public static final String MESSAGE_NAME_CONSTRAINTS = "Priority should be in alphabetic characters";
 
    private String priority;
    private Person toSet; 
    
    public PriorityCommand(String name, String prioritylevel) throws IllegalValueException {
         this.toSet = findPerson(name); // find person by name
         this.priority = prioritylevel;
    }
    
    abstract public Person findPerson(String name);
    
    @Override
    public CommandResult execute() {
        try {
            toSet.setPriority(this.priority);
            return new CommandResult(String.format(MESSAGE_SUCCESS, toSet));
        } catch (IllegalValueException e) {
            return new CommandResult(MESSAGE_NAME_CONSTRAINTS);
        }
    }
}

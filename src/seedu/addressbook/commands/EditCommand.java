package seedu.addressbook.commands;

import seedu.addressbook.common.Messages;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.Person;

public class EditCommand extends Command {
    public static final String COMMAND_WORD = "edit";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n\t"
            + "Edit the person given an index number.\n\t"
            + "The index number could be retrieved from the list command.\n\t"
            + "Parameters: INDEX FIELD VALUE\n\t"
            + "Example: " + COMMAND_WORD + " 1 name Grace";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    private enum PersonField { NAME, PHONE, EMAIL, ADDRESS };  

    private final PersonField fieldToEdit;
    private final String newValue;
    
    public EditCommand(int Index, String field, String value) {
        super(Index);        
        
        char fieldChar = field.charAt(0);
        switch (fieldChar) {

        case 'n':
           this.fieldToEdit = PersonField.NAME;
           break;

        case 'p':
            this.fieldToEdit = PersonField.PHONE;
            break;

        case 'e':
            this.fieldToEdit = PersonField.EMAIL;
            break;

        case 'a':
            this.fieldToEdit = PersonField.ADDRESS;
            break;
            
       default:            
            this.fieldToEdit = PersonField.NAME;
        }
        
        this.newValue = value;        
    }
    
    public String getNewValue() {
        return newValue;
    }

    @Override
    public CommandResult execute() {
        try {
            final Person target = (Person) getTargetPerson();
            
            if (fieldToEdit == PersonField.NAME){
                target.setName(newValue);                
            } else if (fieldToEdit == PersonField.PHONE){
                target.setPhone(newValue);                
            } else if (fieldToEdit == PersonField.EMAIL){
                target.setEmail(newValue);                
            } else if (fieldToEdit == PersonField.ADDRESS){
                target.setAddress(newValue);                
            }             
           
            return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, target));

        } catch (IndexOutOfBoundsException ie) {
            return new CommandResult(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        } catch (IllegalValueException ve) {
            return new CommandResult(Messages.MESSAGE_INVALID_VALUE);
        } 
    }

}

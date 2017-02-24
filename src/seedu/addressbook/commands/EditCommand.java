package seedu.addressbook.commands;

import seedu.addressbook.common.Messages;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.Address;
import seedu.addressbook.data.person.Email;
import seedu.addressbook.data.person.Name;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.Phone;

/**
 * Edit a person identified using it's last displayed index from the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" 
            + "Edit the person identified by the index number used in the last person listing.\n\t"
            + "Parameters: INDEX\n\t"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";

    private enum PersonProperty { NAME, PHONE, EMAIL, ADDRESS }  

    private final PersonProperty propertyToEdit;
    private final String newValue;
    
    public EditCommand(int targetVisibleIndex, String editInfo) {
        super(targetVisibleIndex);
        
        char toEdit = editInfo.charAt(0);
        switch (toEdit) {

        case 'n':
            this.propertyToEdit = PersonProperty.NAME;
            break;

        case 'p':
            this.propertyToEdit = PersonProperty.PHONE;
            break;

        case 'e':
            this.propertyToEdit = PersonProperty.EMAIL;
            break;

        case 'a':
            this.propertyToEdit = PersonProperty.ADDRESS;
            break;
            
        default:            
            this.propertyToEdit = PersonProperty.NAME;
        }
        
        this.newValue = editInfo.substring(3);        
    }

    @Override
    public CommandResult execute() {
        try {
            final Person target = (Person) getTargetPerson();
            
            if (propertyToEdit == PersonProperty.NAME){
                target.setName(newValue);                
            } else if (propertyToEdit == PersonProperty.PHONE){
                target.setPhone(newValue);                
            } else if (propertyToEdit == PersonProperty.EMAIL){
                target.setName(newValue);                
            } else if (propertyToEdit == PersonProperty.ADDRESS){
                target.setName(newValue);                
            }             
            
            return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, target));

        } catch (IndexOutOfBoundsException ie) {
            return new CommandResult(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        } 
    }

}

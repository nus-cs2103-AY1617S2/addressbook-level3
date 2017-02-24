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
            + "Edit the person identified by the index number used in the last person listing. "
            + "Indicate only ONE field to edit with n/, p/, e/ or a/\n\t"
            + "Parameters: INDEX (n/NAME | p/PHONE | e/EMAIL | a/ADDRESS)\n\t"
            + "Example: " + COMMAND_WORD + " 1 n/new name here";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";

    private enum PersonProperty { NAME, PHONE, EMAIL, ADDRESS }  

    private final PersonProperty propertyToEdit;
    private final String newValue;
    
    public EditCommand(int targetVisibleIndex, String option, String newValue) throws IllegalValueException {
        super(targetVisibleIndex);
        
        if (option.equals("n")){
            
            if (!Name.isValidName(newValue)){
                throw new IllegalValueException(Name.MESSAGE_NAME_CONSTRAINTS);
            }
            
            this.propertyToEdit = PersonProperty.NAME;
            
        } else if (option.equals("p")){
            
            if (!Phone.isValidPhone(newValue)){
                throw new IllegalValueException(Phone.MESSAGE_PHONE_CONSTRAINTS);
            }
            
            this.propertyToEdit = PersonProperty.PHONE;
            
        } else if (option.equals("e")){
            
            if (!Email.isValidEmail(newValue)){
                throw new IllegalValueException(Email.MESSAGE_EMAIL_CONSTRAINTS);
            }
            
            this.propertyToEdit = PersonProperty.EMAIL;
            
        } else {
            
            if (!Address.isValidAddress(newValue)){
                throw new IllegalValueException(Address.MESSAGE_ADDRESS_CONSTRAINTS);
            }
            
            this.propertyToEdit = PersonProperty.ADDRESS;
        } 
        
        this.newValue = newValue;        
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
                target.setEmail(newValue);                
            } else if (propertyToEdit == PersonProperty.ADDRESS){
                target.setAddress(newValue);                
            }             
            
            return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, target));

        } catch (IndexOutOfBoundsException ie) {
            return new CommandResult(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        } 
    }

}

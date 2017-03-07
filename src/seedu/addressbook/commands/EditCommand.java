package seedu.addressbook.commands;

import seedu.addressbook.common.Messages;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.person.UniquePersonList;
import seedu.addressbook.data.person.UniquePersonList.PersonNotFoundException;

import java.util.*;

public class EditCommand extends Command {
	
	private String phone, email,address;
	
	public static final String COMMAND_WORD = "edit";
	
    public static final String MESSAGE_DUPLICATE_PERSON = "Error, entry not editted for some reason.";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" 
    		+ "Edits the person identified by the index number used in the last person listing.\n\t"
            + "Parameters: KEYWORD INDEX p/PHONE e/EMAIL a/ADDRESS\n\t"
            + "Example: " + COMMAND_WORD + " 1 " + " p/98765432 e/johnd@gmail.com a/311, Clementi Ave 2, #02-25";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edit Person: %1$s";
    
    private  Person toEdit;

    public EditCommand(String targetVisibleIndex,
            String phone,
            String email,
            String address) {
        super(Integer.parseInt(targetVisibleIndex));
        this.phone = phone;
        this.email = email;
        this.address = address;
        
    }

		
    @Override
    public CommandResult execute() {
        try {

            this.toEdit = new Person(getTargetPerson());
            toEdit.setAddress(this.address);
            toEdit.setEmail(this.email);
            toEdit.setPhone(this.phone);
            final ReadOnlyPerson target = getTargetPerson();
            addressBook.removePerson(target);
            addressBook.addPerson(toEdit);
            return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, target));

        } catch (IndexOutOfBoundsException ie) {
            return new CommandResult(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        } catch (PersonNotFoundException pnfe) {
            return new CommandResult(Messages.MESSAGE_PERSON_NOT_IN_ADDRESSBOOK);
        } catch (UniquePersonList.DuplicatePersonException dpe) {
            return new CommandResult(MESSAGE_DUPLICATE_PERSON);
        }
    }

}

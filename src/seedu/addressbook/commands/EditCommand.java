package seedu.addressbook.commands;

import seedu.addressbook.common.Messages;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.*;
import seedu.addressbook.data.person.UniquePersonList.PersonNotFoundException;
import seedu.addressbook.data.person.UniquePersonList.DuplicatePersonException;
import seedu.addressbook.data.tag.Tag;
import seedu.addressbook.data.tag.UniqueTagList;

import java.util.HashMap;

/**
 * 
 * @author ryuus
 *
 */
public class EditCommand extends Command {
	
	public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" + "Edits an entry of the address book. "
            + "An entry is specified by the index number given by the list command"
            + "Parameters: INDEX\n\t"
            + "Example: " + COMMAND_WORD + " 42";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Address book updated: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book but I doubt you will ever see this message";
    
    private final ReadOnlyPerson originalPerson;
    private Person updatedPerson;
    private Name name = null;
    private Email email = null;
    private Phone phone = null;
    private Address address = null;
    private Race race = null;
    private Religion religion = null;
    
    public EditCommand(int targetVisibleIndex, HashMap<String, String> editData) {
    	super(targetVisibleIndex);
    	originalPerson = getTargetPerson();
    	try {
			if (editData.containsKey("name")) {
				name = new Name(editData.get("name"));
			}
			if (editData.containsKey("email")) {
				email = new Email(editData.get("email"), Boolean.valueOf(editData.get("emailIsPrivate")));
			}
			if (editData.containsKey("phone")) {
				phone = new Phone(editData.get("phone"), Boolean.valueOf(editData.get("phoneIsPrivate")));
			}
			if (editData.containsKey("address")) {
				address = new Address(editData.get("address"), Boolean.valueOf(editData.get("addressIsPrivate")));
			}
			if (editData.containsKey("race")) {
				race = new Race(editData.get("race"), Boolean.valueOf(editData.get("raceIsPrivate")));
			}
			if (editData.containsKey("religion")) {
				religion = new Religion(editData.get("religion"), Boolean.valueOf(editData.get("religionIsPrivate")));
			}
		} catch (IllegalValueException e) {
			e.printStackTrace();
		}
    }
    
    private void buildUpdatedPerson() {
    	updatedPerson = new Person(
    		name != null ? name : originalPerson.getName(),
    		phone != null ? phone : originalPerson.getPhone(),
    		email != null ? email : originalPerson.getEmail(),
    		address != null ? address : originalPerson.getAddress(),
    		race != null ? race : originalPerson.getRace(),
    		religion != null ? religion : originalPerson.getReligion(),
    		originalPerson.getTags()
    	);
    }
	
    @Override
    public CommandResult execute() {
        try {
        	final int targetIndex = getTargetIndex();
            addressBook.editPerson(targetIndex, updatedPerson, originalPerson);
            return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, originalPerson));

        } catch (IndexOutOfBoundsException ie) {
            return new CommandResult(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        } catch (PersonNotFoundException pnfe) {
            return new CommandResult(Messages.MESSAGE_PERSON_NOT_IN_ADDRESSBOOK);
        } catch (DuplicatePersonException dpe) {
        	return new CommandResult(MESSAGE_DUPLICATE_PERSON);
        }
    }
}

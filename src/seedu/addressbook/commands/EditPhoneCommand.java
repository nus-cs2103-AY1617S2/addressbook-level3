package seedu.addressbook.commands;

import seedu.addressbook.common.Messages;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.Address;
import seedu.addressbook.data.person.Email;
import seedu.addressbook.data.person.Name;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.Phone;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.person.UniquePersonList.DuplicatePersonException;
import seedu.addressbook.data.person.UniquePersonList.PersonNotFoundException;
import seedu.addressbook.data.tag.UniqueTagList;


/**
 * Deletes a person identified using it's last displayed index from the address book.
 */
public class EditPhoneCommand extends Command {

    public static final String COMMAND_WORD = "editp";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" 
            + "Changes the phone number of the person identified by the index number used in the last person listing.\n\t"
            + "Parameters: INDEX [p]p/NEWPHONE\n\t"
            + "Example: " + COMMAND_WORD + " 1" + " pp/98765432";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Changed Phone Number for Person: %1$s";
    
    private Phone newPhone;


    public EditPhoneCommand(int targetVisibleIndex, String newNumber, boolean isPrivate) throws IllegalValueException {
        super(targetVisibleIndex);
        newPhone = new Phone(newNumber, isPrivate);
    }


    @Override
    public CommandResult execute() throws DuplicatePersonException {
        try {
            final ReadOnlyPerson target = getTargetPerson();
            addressBook.removePerson(target);
            addressBook.addPerson(new Person(target.getName(), this.newPhone, target.getEmail(), target.getAddress(), target.getTags()));
            return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, target));

        } catch (IndexOutOfBoundsException ie) {
            return new CommandResult(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        } catch (PersonNotFoundException pnfe) {
            return new CommandResult(Messages.MESSAGE_PERSON_NOT_IN_ADDRESSBOOK);
        }
    }

}

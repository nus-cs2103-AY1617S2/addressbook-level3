package seedu.addressbook.commands;

import seedu.addressbook.common.Messages;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.*;
import seedu.addressbook.data.person.UniquePersonList.PersonNotFoundException;
import seedu.addressbook.data.person.UniquePersonList.DuplicatePersonException;
import seedu.addressbook.data.tag.Tag;
import seedu.addressbook.data.tag.UniqueTagList;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author ryuus
 *
 */
public class EditCommand extends Command {
	
	public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" + "Edits an entry of the address book. "
            + "An entry is specified by the index number given by the list command"
            + "Parameters: INDEX\n"
            + "Example: " + COMMAND_WORD + " 42 John Doe p/98765432 e/johnd@gmail.com a/311, Clementi Ave 2, #02-25 b/Malay r/Buddhism t/friends t/owesMoney";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Address book updated: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "Doubt that this message will ever be shown.";
    
    private final ReadOnlyPerson originalPerson;
    private Person updatedPerson;
    
    public EditCommand(int targetVisibleIndex,
					String name,
		            String phone, boolean isPhonePrivate,
		            String email, boolean isEmailPrivate,
		            String address, boolean isAddressPrivate,
		            String race, boolean isRacePrivate,
		            String religion, boolean isReligionPrivate,
		            Set<String> tags) throws IllegalValueException {
    	super(targetVisibleIndex);
    	
		final Set<Tag> tagSet = new HashSet<>();
		for (String tagName : tags) {
		  tagSet.add(new Tag(tagName));
		}
		this.originalPerson = getTargetPerson();
		this.updatedPerson = new Person(
		      new Name(name),
		      new Phone(phone, isPhonePrivate),
		      new Email(email, isEmailPrivate),
		      new Address(address, isAddressPrivate),
		      new Race(race, isRacePrivate),
		      new Religion(religion, isReligionPrivate),
		      new UniqueTagList(tagSet)
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

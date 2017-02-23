package seedu.addressbook.commands;

import seedu.addressbook.common.Messages;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.*;
import seedu.addressbook.data.person.UniquePersonList.PersonNotFoundException;
import seedu.addressbook.data.tag.Tag;
import seedu.addressbook.data.tag.UniqueTagList;

import java.util.HashSet;
import java.util.Set;

/**
 * Adds a person to the address book.
 */
abstract public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" + "Edits a person in the address book. "
            + "Contact details can be marked private by prepending 'p' to the prefix.\n\t"
            + "Parameters: NAME NEWNAME [p]p/PHONE [p]e/EMAIL [p]a/ADDRESS  [t/TAG]...\n\t"
            + "Example: " + COMMAND_WORD
            + " John Doe John Dew p/98765432 e/johnd@gmail.com a/311, Clementi Ave 2, #02-25 t/friends t/owesMoney";

    public static final String MESSAGE_SUCCESS = "Person edited: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book";

    private final Person toAdd;
    private final Person toEdit;

    /**
     * Convenience constructor using raw values.
     *
     * @throws IllegalValueException if any of the raw values are invalid
     */
    public EditCommand(String oldName,
                      String name,
                      String phone, boolean isPhonePrivate,
                      String email, boolean isEmailPrivate,
                      String address, boolean isAddressPrivate,
                      Set<String> tags) throws IllegalValueException {
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(new Tag(tagName));
        }
        this.toEdit = findPerson(oldName);
        String newName = name.equals(null) ? toEdit.getName().toString() : name;
        String newPhone = phone.equals(null) ? toEdit.getPhone().toString() : phone;
        String newEmail = email.equals(null) ? toEdit.getEmail().toString() : email;
        String newAddress = address.equals(null) ? toEdit.getAddress().toString() : address;
        this.toAdd = new Person(
                new Name(newName),
                new Phone(newPhone, isPhonePrivate),
                new Email(newEmail, isEmailPrivate),
                new Address(newAddress, isAddressPrivate),
                new UniqueTagList(tagSet)
        );
    }
    
    /**
     * Finds person in the current address book based on name
     * @param name
     * @return
     */
    abstract public Person findPerson(String name);

    public ReadOnlyPerson getPerson() {
        return toAdd;
    }

    @Override
    public CommandResult execute() {
        try {
            addressBook.removePerson(toEdit);
            addressBook.addPerson(toAdd);
            return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
        } catch (UniquePersonList.DuplicatePersonException dpe) {
            return new CommandResult(MESSAGE_DUPLICATE_PERSON);
        } catch (PersonNotFoundException e) {
            return new CommandResult(Messages.MESSAGE_PERSON_NOT_IN_ADDRESSBOOK);
        }
    }

}

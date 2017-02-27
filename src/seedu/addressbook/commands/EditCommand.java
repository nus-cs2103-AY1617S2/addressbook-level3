package seedu.addressbook.commands;

import seedu.addressbook.common.Messages;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.*;
import seedu.addressbook.data.person.UniquePersonList.PersonNotFoundException;

/**
 * Edits a person in the address book identified using its last displayed index
 * from the address book. Can edit 1 of multiple fields - name, phone, email,
 * address Implemented this way because unlikely for contact to change
 * name/phone/email/address all at once
 */

public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n"
            + "Edit fields of a person identified by the index number used in the last person listing. \n\t"
            + "Parameters: INDEX EDITFIELD/NEWINFO \n\t" + "Example: " + COMMAND_WORD + " 3 n/Meowr";

    private static final String PERSON_EDIT_FIELD_NAME = "n";
    private static final String PERSON_EDIT_FIELD_PHONE = "p";
    private static final String PERSON_EDIT_FIELD_EMAIL = "e";
    private static final String PERSON_EDIT_FIELD_ADDRESS = "a";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";

    private final String editField;
    private final String newInfo;

    public EditCommand(int targetVisibleIndex, String editField, String newInfo) {
        super(targetVisibleIndex);
        this.editField = editField;
        this.newInfo = newInfo;
    }

    @Override
    public CommandResult execute() {
        try {
            final ReadOnlyPerson target = getTargetPerson();
            Person newPerson = getNewPersonWithEditedInfo(target);
            addressBook.removePerson(target);
            addressBook.addPerson(newPerson);
            return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, newPerson));

        } catch (IndexOutOfBoundsException ie) {
            return new CommandResult(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        } catch (PersonNotFoundException pnfe) {
            return new CommandResult(Messages.MESSAGE_PERSON_NOT_IN_ADDRESSBOOK);
        } catch (IllegalValueException ive) {
            return new CommandResult(Messages.MESSAGE_INVALID_PERSON_FIELD);
        }
    }

    private Person getNewPersonWithEditedInfo(ReadOnlyPerson target) throws IllegalValueException {
        switch (editField) {
            case "n":
                return new Person(new Name(newInfo), target.getPhone(), target.getEmail(), target.getAddress(), target.getTags());
            case "p":
                return new Person(target.getName(), new Phone(newInfo, target.getPhone().isPrivate()),
                           target.getEmail(), target.getAddress(), target.getTags());
            case "e":
                return new Person(target.getName(), target.getPhone(), new Email(newInfo, target.getEmail().isPrivate()),
                           target.getAddress(), target.getTags());
            case "a":
                return new Person(target.getName(), target.getPhone(), target.getEmail(),
                        new Address(newInfo, target.getAddress().isPrivate()), target.getTags());
            default:
                throw new IllegalValueException(); // won't get here. parser will already catch exception if not n/p/e/a
        }
    }

}

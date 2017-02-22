package seedu.addressbook.commands;

import java.util.HashSet;
import java.util.Set;

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
import seedu.addressbook.data.tag.Tag;
import seedu.addressbook.data.tag.UniqueTagList;


/**
 * Edits the info of a person identified using it's last displayed index from the address book.
 */
public class EditCommand extends Command {
    
    private Person toAdd;
    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" 
            + "Edits the info of the person identified by the index number used in the last person listing.\n\t"
            + "Parameters: INDEX [p]p/PHONE [p]e/EMAIL [p]a/ADDRESS  [t/TAG]...\n\t"
            + "Example: " + COMMAND_WORD + " 1"
            + " John Doe p/98765432 e/johnd@gmail.com a/311, Clementi Ave 2, #02-25 t/friends t/owesMoney";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Updated Person: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book";


    public EditCommand(int targetVisibleIndex, int itemNum, String newInfo, boolean isPrivate) throws IllegalValueException {
        this.setTargetIndex(targetVisibleIndex);
        ReadOnlyPerson target = getTargetPerson();
        switch (itemNum){
            case 1:
                this.toAdd = new Person(
                        new Name(target.getName().toString()),
                        new Phone(newInfo, isPrivate),
                        new Email(target.getEmail().toString(), target.getEmail().isPrivate()),
                        new Address(target.getAddress().toString(), target.getAddress().isPrivate()),
                        new UniqueTagList(target.getTags().toSet())
                );
            case 2:
                this.toAdd = new Person(
                        new Name(target.getName().toString()),
                        new Phone(target.getPhone().toString(), target.getPhone().isPrivate()),
                        new Email(newInfo, isPrivate),
                        new Address(target.getAddress().toString(), target.getAddress().isPrivate()),
                        new UniqueTagList(target.getTags().toSet())
                );
            case 3:
                this.toAdd = new Person(
                        new Name(target.getName().toString()),
                        new Phone(target.getPhone().toString(), target.getPhone().isPrivate()),
                        new Email(target.getEmail().toString(), target.getEmail().isPrivate()),
                        new Address(newInfo, isPrivate),
                        new UniqueTagList(target.getTags().toSet())
                );
        }
        
    }
    
    public EditCommand(int targetVisibleIndex, int itemNum, String newInfo) throws IllegalValueException {
        this.setTargetIndex(targetVisibleIndex);
        ReadOnlyPerson target = getTargetPerson();
        this.toAdd = new Person(
                new Name(newInfo),
                new Phone(target.getPhone().toString(), target.getPhone().isPrivate()),
                new Email(target.getEmail().toString(), target.getEmail().isPrivate()),
                new Address(target.getAddress().toString(), target.getAddress().isPrivate()),
                new UniqueTagList(target.getTags().toSet())
        );
                
    }


    public EditCommand(int targetVisibleIndex, int itemNum, Set<String> tags) throws IllegalValueException {
        this.setTargetIndex(targetVisibleIndex);
        ReadOnlyPerson target = getTargetPerson();
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(new Tag(tagName));
        }
        this.toAdd = new Person(
                new Name(target.getName().toString()),
                new Phone(target.getPhone().toString(), target.getPhone().isPrivate()),
                new Email(target.getEmail().toString(), target.getEmail().isPrivate()),
                new Address(target.getAddress().toString(), target.getAddress().isPrivate()),
                new UniqueTagList(tagSet)
        );
    }

    @Override
    public CommandResult execute() {
        try {
            final ReadOnlyPerson target = getTargetPerson();
            addressBook.removePerson(target);
            addressBook.addPerson(toAdd);
            return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, target));

        } catch (IndexOutOfBoundsException ie) {
            return new CommandResult(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        } catch (PersonNotFoundException pnfe) {
            return new CommandResult(Messages.MESSAGE_PERSON_NOT_IN_ADDRESSBOOK);
        } catch (DuplicatePersonException dpe) {
            return new CommandResult(MESSAGE_DUPLICATE_PERSON);
        }
    }

}

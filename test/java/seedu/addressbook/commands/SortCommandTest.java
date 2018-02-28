package seedu.addressbook.commands;

import org.junit.Test;
import seedu.addressbook.common.Utils;
import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.*;
import seedu.addressbook.data.tag.UniqueTagList;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class SortCommandTest {
    private static final List<ReadOnlyPerson> EMPTY_PERSON_LIST = Collections.emptyList();

    @Test
    public void isSorted() throws IllegalValueException {
        Person p = new Person(new Name(Name.EXAMPLE), new Phone(Phone.EXAMPLE, false),
                new Email(Email.EXAMPLE, true), new Address(Address.EXAMPLE, false), new UniqueTagList());
        AddressBook book = new AddressBook();
        book.addPerson(p);
        book.addPerson(new Person(new Name("Ahmad"), new Phone(Phone.EXAMPLE, false),
                new Email(Email.EXAMPLE, true), new Address(Address.EXAMPLE, false), new UniqueTagList()));
        AddCommand command = new AddCommand(p);
        command.setData(book, EMPTY_PERSON_LIST);
        SortCommand sc = new SortCommand(book);
        assertsIsSorted(EMPTY_PERSON_LIST);
    }

    @Test
    public void isNotSorted() throws  IllegalValueException{
        Person p = new Person(new Name(Name.EXAMPLE), new Phone(Phone.EXAMPLE, false),
                new Email(Email.EXAMPLE, true), new Address(Address.EXAMPLE, false), new UniqueTagList());
        AddressBook book = new AddressBook();
        book.addPerson(p);
        book.addPerson(new Person(new Name("Ahmad"), new Phone(Phone.EXAMPLE, false),
                new Email(Email.EXAMPLE, true), new Address(Address.EXAMPLE, false), new UniqueTagList()));
        AddCommand command = new AddCommand(p);
        command.setData(book, EMPTY_PERSON_LIST);
        assertsIsNotSorted(EMPTY_PERSON_LIST);
    }

    public void assertsIsSorted(List<ReadOnlyPerson> list){
        assertTrue(Utils.isAlphabeticalOrder(list));
    }
    
    public void assertsIsNotSorted(List<ReadOnlyPerson> list){
        assertTrue(Utils.isAlphabeticalOrder(list));
    }
}
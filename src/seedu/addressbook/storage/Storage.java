package seedu.addressbook.storage;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.exception.IllegalValueException;

public abstract class Storage {
    public abstract AddressBook load() throws Exception;
    public abstract String getPath();
    public abstract void save(AddressBook addressbook) throws Exception;
}

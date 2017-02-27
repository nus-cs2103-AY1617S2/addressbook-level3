package seedu.addressbook.storage;

import seedu.addressbook.data.AddressBook;

/**
 * Represents the mechanism used to store address book data.
 */
public abstract class Storage {

    public abstract void save(AddressBook addressBook) throws StorageFile.StorageOperationException;
    public abstract AddressBook load() throws StorageFile.StorageOperationException;

}

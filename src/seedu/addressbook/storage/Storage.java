package seedu.addressbook.storage;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.storage.StorageFile.StorageOperationException;

/**
 * Abstract class for address book storage.
 */
public interface Storage {

    public void save(AddressBook addressBook)
            throws StorageOperationException, seedu.addressbook.storage.StorageStub.StorageOperationException;
    public AddressBook load()
            throws StorageOperationException, seedu.addressbook.storage.StorageStub.StorageOperationException;
    public String getPath();
}

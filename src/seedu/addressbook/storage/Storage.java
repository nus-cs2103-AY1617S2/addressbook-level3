package seedu.addressbook.storage;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.storage.StorageFile.StorageOperationException;

public abstract class Storage {

    /**
     * Loads data from this storage file.
     *
     * @throws StorageOperationException
     *             if there were errors reading and/or converting data from
     *             file.
     */
    abstract public AddressBook load() throws StorageOperationException;

    abstract public String getPath();

    /**
     * Saves all data to this storage file.
     *
     * @throws StorageOperationException
     *             if there were errors converting and/or storing data to file.
     */
    abstract public void save(AddressBook addressBook) throws StorageOperationException;

}

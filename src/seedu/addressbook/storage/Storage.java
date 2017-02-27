package seedu.addressbook.storage;

import seedu.addressbook.data.AddressBook;

/**
 * Represents the mechanism used to store address book data.
 */
public abstract class Storage {

    /**
     * Saves all data to this storage file.
     *
     * @throws StorageFile.StorageOperationException if there were errors converting and/or storing data to file.
     */
    public abstract void save(AddressBook addressBook) throws StorageFile.StorageOperationException;


    /**
     * Loads data from this storage file.
     *
     * @throws StorageFile.StorageOperationException if there were errors reading and/or converting data from file.
     */
    public abstract AddressBook load() throws StorageFile.StorageOperationException;

    /**
     * Shows the location of storage
     */
    public abstract String getStorageLocation();
}

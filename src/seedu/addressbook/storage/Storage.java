package seedu.addressbook.storage;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.storage.Storage.StorageOperationException;

public abstract class Storage {

    /**
     * Signals that some error has occured while trying to convert and read/write data between the application
     * and the storage file.
     */
    public static class StorageOperationException extends Exception {
        public StorageOperationException(String message) {
            super(message);
        }
    }

    /**
     * Saves all data to this storage object.
     *
     * @throws StorageOperationException if there were errors converting and/or storing data to object.
     */
    public abstract AddressBook load() throws StorageOperationException;

    /**
     * Loads data from this storage object.
     *
     * @throws StorageOperationException if there were errors reading and/or converting data from object.
     */
    public abstract void save(AddressBook addressBook) throws StorageOperationException;

}
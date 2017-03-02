package seedu.addressbook.storage;

import seedu.addressbook.data.AddressBook;

public abstract class Storage {
    
    /**
     * Signals that some error has occurred while trying to convert and read/write data between 
     * the application and the storage object.
     */
    public static class StorageOperationException extends Exception {
        public StorageOperationException(String message) {
            super(message);
        }
    }

    /**
     * Saves all data to the storage object.
     *
     * @throws StorageOperationException if there were errors converting and/or storing data to storage object.
     */
    public abstract void save(AddressBook addressBook) throws StorageOperationException;
    
    /**
     * Loads data from this storage object.
     *
     * @throws StorageOperationException if there were errors reading and/or converting data from the storage.
     */
    public abstract AddressBook load() throws StorageOperationException;

    /** Gets the location of storage and returns it as a String. */
    public abstract String getLocation();
    
}

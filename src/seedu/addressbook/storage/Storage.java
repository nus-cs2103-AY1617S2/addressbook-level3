package seedu.addressbook.storage;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.exception.IllegalValueException;

public abstract class Storage {
	
    /**
     * Signals that some error has occurred while trying to convert and read/write data between the application
     * and the storage.
     */
    public static class StorageOperationException extends Exception {
        public StorageOperationException(String message) {
            super(message);
        }
    }
	
    /**
     * Creates the Storage object based on the user specified path (if any) or the default storage path.
     * @throws Storage.InvalidStoragePathException if the target file path is incorrect.
     */
    public static StorageFile initializeStorage() throws Storage.InvalidStoragePathException {
		return new StorageFile();
	}

	/**
     * Loads data from this storage.
     *
     * @throws StorageOperationException if there were errors reading and/or converting data from file.
     */
    public abstract AddressBook load() throws StorageOperationException;
    
    /**
     * Signals that the given path does not fulfill the storage path constraints.
     */
    public static class InvalidStoragePathException extends IllegalValueException {
        public InvalidStoragePathException(String message) {
            super(message);
        }
    }
    
    /**
     * Returns path of storage. 
     */
    public abstract String getPath();
    
    /**
     * Saves all data to this storage file.
     *
     * @throws StorageOperationException if there were errors converting and/or storing data to file.
     */
    public abstract void save(AddressBook addressBook) throws StorageOperationException;
}

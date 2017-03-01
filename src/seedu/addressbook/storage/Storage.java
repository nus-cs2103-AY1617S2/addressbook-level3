package seedu.addressbook.storage;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.exception.IllegalValueException;

public abstract class Storage {
	/**
     * Signals that the given file path does not fulfill the storage filepath constraints.
     */
    public static class InvalidStoragePathException extends IllegalValueException {
        public InvalidStoragePathException(String message) {
            super(message);
        }
    }

    /**
     * Signals that some error has occured while trying to convert and read/write data between the application
     * and the storage file.
     */
    public static class StorageOperationException extends Exception {
        public StorageOperationException(String message) {
            super(message);
        }
    }
    
    public abstract void save(AddressBook addressBook) throws StorageOperationException;
    
    public abstract AddressBook load() throws StorageOperationException;
    
    public abstract String getPath();
    
}

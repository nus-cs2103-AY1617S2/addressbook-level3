package seedu.addressbook.storage;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.exception.IllegalValueException;

public abstract class Storage {
    
    /**
     * Gets this storage file's path
     * @return storage file's path
     */
    public abstract String getPath();
    
    /**
     * Saves all storage file data
     * @throws StorageOperationException if there are errors converting or storing data to file
     */
    public abstract void save(AddressBook addressBook) throws StorageOperationException;
    
    /**
     * Loads all data to this storage file
     * @throws StorageOperationException if there are errors reading or storing data to file
     */
    public abstract AddressBook load() throws StorageOperationException;
    
    /**
     * Signals that the given file path does not fulfill the storage filepath constraints
     */
    public static class InvalidStoragePathException extends IllegalValueException {
        public InvalidStoragePathException(String message) {
            super(message);
        }
    }
    
    /**
     * Signals that some error has occurred while trying to convert and read or write 
     * data between the application and the storage file
     */
    public static class StorageOperationException extends Exception {
        public StorageOperationException(String message) {
            super(message);
        }
    }
}

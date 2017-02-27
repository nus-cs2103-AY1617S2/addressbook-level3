package seedu.addressbook.storage;

import java.nio.file.Path;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.storage.StorageFile.InvalidStorageFilePathException;

/*
 * Interface for StorageFile in order to fulfill Responsibility Dependency Principle
 */

public interface Storage {
	
    /**
     * Signals that the given file path does not fulfill the storage filepath constraints.
     */
    public static class InvalidStorageFilePathException extends IllegalValueException {
        public InvalidStorageFilePathException(String message) {
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
    
    public boolean isValidPath(Path filePath) ;
    public String getPath();
    public AddressBook load();
    public void save(AddressBook addressBook) throws StorageOperationException;
    
  
}

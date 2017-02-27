package seedu.addressbook.storage;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.storage.Storage.InvalidStorageFilePathException;
import seedu.addressbook.storage.Storage.StorageOperationException;

public abstract class Storage {
	public static class StorageOperationException extends Exception {
        public StorageOperationException(String message) {
            super(message);
        }
    }
	public static class InvalidStorageFilePathException extends IllegalValueException {
        public InvalidStorageFilePathException(String message) {
            super(message);
        }
    }
	
	public abstract AddressBook load() throws StorageOperationException;
	
	public abstract String getPath();
	
	public abstract void save(AddressBook addressBook) throws StorageOperationException;

	public abstract Storage initializeStorage() throws InvalidStorageFilePathException;
	
}

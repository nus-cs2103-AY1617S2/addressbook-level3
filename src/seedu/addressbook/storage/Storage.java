package seedu.addressbook.storage;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.storage.StorageFile.StorageOperationException;

public abstract class Storage {
    
    public abstract static class InvalidStoragePathException extends IllegalValueException {
        public InvalidStoragePathException(String message) {
            super(message);
        }
    }
    public abstract void save(AddressBook addressBook) throws StorageOperationException;
    public abstract AddressBook load() throws StorageOperationException;
    public abstract String getPath();
    
}

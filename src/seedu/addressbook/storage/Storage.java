package seedu.addressbook.storage;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.storage.StorageFile.StorageOperationException;

public abstract class Storage {

    public abstract String getPath();

    public abstract void save(AddressBook addressBook)  throws StorageOperationException;
   
    public abstract AddressBook load()  throws StorageOperationException;
    
    public abstract class InvalidStorageFilePathException extends IllegalValueException{
        public InvalidStorageFilePathException(String message) {
            super(message);
        }
    }
}

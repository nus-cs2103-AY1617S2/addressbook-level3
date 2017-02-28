package seedu.addressbook.storage;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.storage.StorageFile.InvalidStorageFilePathException;
import seedu.addressbook.storage.StorageFile.StorageOperationException;

public abstract class Storage {
    
    public static StorageFile set() throws InvalidStorageFilePathException {
        return new StorageFile();
    }
    
    public abstract AddressBook load() throws StorageOperationException;

    public abstract String getPath();

    public abstract void save(AddressBook addressBook) throws StorageOperationException;
    
}
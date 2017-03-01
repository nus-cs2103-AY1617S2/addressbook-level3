package seedu.addressbook.storage;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.storage.StorageFile.InvalidStoragePathException;
import seedu.addressbook.storage.StorageFile.StorageOperationException;

public abstract class Storage {

	public abstract String getPath();
	public abstract AddressBook load() throws StorageOperationException;
	public abstract void save(AddressBook addressBook) throws StorageOperationException, StorageOperationException;
	
    public StorageFile initializeStorage() throws InvalidStoragePathException {
        return new StorageFile();
    }
    
}

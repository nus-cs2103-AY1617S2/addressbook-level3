package seedu.addressbook.storage;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.storage.StorageFile.StorageOperationException;

public abstract class Storage {
    
    /** Default file path used if the user doesn't provide the file name. */
    public static final String DEFAULT_STORAGE_FILEPATH = "addressbook.txt";
    
    public abstract void save(AddressBook addressBook) throws StorageOperationException;
    public abstract AddressBook load() throws StorageOperationException;
    public abstract String getPath();
}

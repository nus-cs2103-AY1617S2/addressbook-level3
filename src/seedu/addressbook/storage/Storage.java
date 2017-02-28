package seedu.addressbook.storage;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.storage.StorageFile.InvalidStorageFilePathException;
import seedu.addressbook.storage.StorageFile.StorageOperationException;

public abstract class Storage {
    
    
    /**
     * Creates a new StorageFile and returns it
     * @throws InvalidStorageFilePathException if the default path is invalid
     */
    public StorageFile setStorage() throws InvalidStorageFilePathException{
        return new StorageFile();
    }
    
    public abstract AddressBook load() throws StorageOperationException;  
    
    public abstract String getPath();                
    
    public abstract void save(AddressBook addressBook) throws StorageOperationException;

}

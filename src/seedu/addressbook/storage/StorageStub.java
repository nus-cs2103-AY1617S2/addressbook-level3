package seedu.addressbook.storage;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.storage.StorageFile.InvalidStorageFilePathException;
import seedu.addressbook.storage.StorageFile.StorageOperationException;

public class StorageStub extends Storage{

    public StorageStub(String filePath) throws InvalidStorageFilePathException {
      
    }
    
    @Override
    public void save(AddressBook addressBook) throws StorageOperationException {
   
    }

    public AddressBook load() throws StorageOperationException {
        new StorageOperationException("Tried to load from dummy save path!");
        
        return AddressBook.empty();
    }

    public String getPath() {
        return "";
    }
}

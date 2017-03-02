package seedu.addressbook.storage;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.storage.StorageFile.StorageOperationException;

public class StorageStub extends Storage{
    

    private AddressBook addressBook;
    private String path;
    
    //Constructor
    public StorageStub(String path){
        this.path = path;
    }
    
    @Override
    public void save(AddressBook addressBook) throws StorageOperationException {
        this.addressBook = addressBook;
        
    }

    @Override
    public AddressBook load() throws StorageOperationException {
        return this.addressBook;
    }

    @Override
    public String getPath() {
        return this.path;
    }

}

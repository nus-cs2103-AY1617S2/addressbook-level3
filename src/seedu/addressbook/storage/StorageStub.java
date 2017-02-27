package seedu.addressbook.storage;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.storage.Storage.StorageOperationException;

public class StorageStub extends Storage{
    
    public StorageStub(String s){
        
    }
    
    public AddressBook load() throws StorageOperationException{
        return null;
    }
    
    public void save(AddressBook addressBook) throws StorageOperationException{
        
    };
    
    public String getPath(){
        return null;
    }
}

package seedu.addressbook.logic;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.storage.Storage;
import seedu.addressbook.storage.StorageFile.StorageOperationException;

public class StorageStub extends Storage {

    @Override
    public AddressBook load() throws StorageOperationException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getPath() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void save(AddressBook addressBook) throws StorageOperationException {
        // TODO Auto-generated method stub
        
    }
    
}

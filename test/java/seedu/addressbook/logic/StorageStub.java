package seedu.addressbook.logic;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.storage.Storage;
import seedu.addressbook.storage.StorageFile.StorageOperationException;

public class StorageStub extends Storage{
    
    public StorageStub(String path){
    }

    @Override
    public void save(AddressBook addressBook) throws StorageOperationException {
    }

    @Override
    public AddressBook load() throws StorageOperationException {
        return null;
    }

    @Override
    public String getPath() {
        return null;
    }

}

package seedu.addressbook.storage;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.storage.StorageFile.StorageOperationException;

public class StorageStub extends Storage {
    
    public StorageStub(String path) {
    }

    @Override
    public void save(AddressBook addressBook) throws StorageOperationException {
    }
}
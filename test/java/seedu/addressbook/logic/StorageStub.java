package seedu.addressbook.logic;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.storage.Storage;
import seedu.addressbook.storage.Storage.StorageOperationException;

/**
 * Stub class to facilitate unit testing on Storage
 * @author yanzh
 *
 */
public class StorageStub extends Storage {

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

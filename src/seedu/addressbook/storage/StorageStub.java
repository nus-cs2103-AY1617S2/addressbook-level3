package seedu.addressbook.storage;

import seedu.addressbook.data.AddressBook;

/**
 * Stub class to facilitate unit testing on Storage
 * @author yanzh
 *
 */
public class StorageStub implements Storage {

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

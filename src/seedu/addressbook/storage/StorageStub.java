package seedu.addressbook.storage;

import seedu.addressbook.data.AddressBook;

/**
 * Represents the file used for testing the Logic class in JUnit
 **/
public class StorageStub extends Storage {

    @Override
    public void save(AddressBook addressBook) throws StorageOperationException {
    }

    @Override
    public AddressBook load() throws StorageOperationException {
        return null;
    }

    @Override
    public String getPath(){
        return null;
    }

}
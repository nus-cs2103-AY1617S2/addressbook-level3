package seedu.addressbook.storage;

import java.nio.file.Path;
import seedu.addressbook.data.AddressBook;

/**
 * Represents the Stub to be used for testing.
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

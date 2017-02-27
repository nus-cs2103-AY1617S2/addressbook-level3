package seedu.addressbook.storage.jaxb;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.person.Address;
import seedu.addressbook.storage.Storage;
import seedu.addressbook.storage.StorageFile;

/**
 * A stub that acts as a placeholder for Storage.
 */
public class StorageStub extends Storage {

    public StorageStub(String string) {

    }

    public void save(AddressBook addressBook) throws StorageFile.StorageOperationException {

    }

    public AddressBook load() throws StorageFile.StorageOperationException {
        return null;
    }

    public String getStorageLocation() {
        return null;
    }
}

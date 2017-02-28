package seedu.addressbook.storage;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.storage.StorageFile.StorageOperationException;

abstract public class Storage {
	abstract public String getIdentifier();
	abstract public AddressBook load() throws StorageOperationException;
	abstract public void save(AddressBook addressBook) throws StorageOperationException;
}

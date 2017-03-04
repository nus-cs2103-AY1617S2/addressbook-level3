package seedu.addressbook.storage;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.storage.StorageFile.StorageOperationException;

/**
 * Represents an executable storage.
 */
public abstract class Storage {
	public abstract void save(AddressBook addressBook) throws StorageOperationException;
	public abstract AddressBook load() throws StorageOperationException;
	public abstract String getPath();
}

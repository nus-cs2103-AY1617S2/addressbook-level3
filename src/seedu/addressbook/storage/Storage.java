package seedu.addressbook.storage;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.storage.StorageFile.StorageOperationException;

/**
 * Represents the file used to store address book data.
 */
public abstract class Storage {
	public abstract AddressBook load() throws StorageOperationException;
	public abstract void save(AddressBook addressBook) throws StorageOperationException;
	public abstract String getPath();
}

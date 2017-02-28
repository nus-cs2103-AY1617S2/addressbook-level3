package seedu.addressbook.storage;

import java.nio.file.Path;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.storage.StorageFile.StorageOperationException;

abstract public class Storage {
	public Path path;
	public String getPath() {
		return path.toString();
	}
	abstract public AddressBook load() throws StorageOperationException;
	abstract public void save(AddressBook addressBook) throws StorageOperationException;
}

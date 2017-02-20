package seedu.addressbook.logic;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.storage.Storage;
import seedu.addressbook.storage.StorageFile;

/**
 * A storage stub used to inject dependent in LogicTest
 */
public class StorageStub implements Storage {
	
	@Override
	public void save(AddressBook addressBook) throws StorageOperationException {}
	
	@Override
	public AddressBook load() throws StorageOperationException {
		return new AddressBook();
	}
	
	@Override
	public String getPath() {
		return null;
	}
	
	@Override
	public Storage initializeStorage() throws InvalidStorageFilePathException {
		return new StorageFile();
	}
}

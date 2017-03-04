package seedu.addressbook.storage;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.storage.StorageFile.StorageOperationException;

/**
 * A StorageStub class implemented to do JUnit testing on StorageFile.
 */
public class StorageStub extends Storage {
	
	private String path;
	private AddressBook addressBook;
	
	public StorageStub(String path) {
		this.path = path;
	}
	
	@Override
	public void save(AddressBook addressBook) throws StorageOperationException {
		this.addressBook = addressBook;
	}

	@Override
	public AddressBook load() throws StorageOperationException {
		return this.addressBook;
	}

	@Override
	public String getPath() {
		return this.path;
	}
	
}

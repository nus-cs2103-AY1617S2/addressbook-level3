package seedu.addressbook.storage;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.storage.StorageFile.StorageOperationException;

public class StorageStub extends Storage {
	
	private String path;
	
	public StorageStub(String path) {
		this.path = path;
	}

	@Override
	public void save(AddressBook addressBook) throws StorageOperationException {
		return;
	}

	@Override
	public AddressBook load() throws StorageOperationException {
		return null;
	}

	@Override
	public String getPath() {
		return path;
	}

}

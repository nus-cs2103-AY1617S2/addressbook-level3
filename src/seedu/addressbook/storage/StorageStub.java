package seedu.addressbook.storage;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.storage.StorageFile.StorageOperationException;

public class StorageStub extends Storage {

	private String path;
	private AddressBook addressBook;
	
	public StorageStub(String path) {
		this.path = path;
	}
	
	@Override
	public void save(AddressBook addressBook) throws StorageOperationException {
		// do nothing
	}

	@Override
	public AddressBook load() throws StorageOperationException {
		return this.addressBook;
	}

	@Override
	public String getPath() {
		return path;  
	}
	
}

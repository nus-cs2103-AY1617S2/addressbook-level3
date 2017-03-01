package seedu.addressbook.storage;

import seedu.addressbook.data.AddressBook;

/**
 * Represents the stub for Storage 
 */
public class StorageStub extends Storage {

	public StorageStub(String filePath) {
		// constructor stub
	}
	
	@Override
	public AddressBook load() throws StorageOperationException {
		// method stub
		return null;
	}

	@Override
	public void save(AddressBook addressBook) throws StorageOperationException {
		// method stub
	}

	@Override
	public String getPath() {
		// method stub
		return null;
	}

}

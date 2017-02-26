package seedu.addressbook.storage;

import seedu.addressbook.data.AddressBook;

public class StorageStub extends Storage {
	public StorageStub(String path) {
		// TODO Auto-generated constructor stub		
	}
	@Override
	public String getPath() {
		return null;
	}

	@Override
	public void save(AddressBook addressBook) throws StorageOperationException {

	}

	@Override
	public AddressBook load() throws StorageOperationException {
		return null;
	}
}

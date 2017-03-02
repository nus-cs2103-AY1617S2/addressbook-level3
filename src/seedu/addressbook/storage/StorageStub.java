package seedu.addressbook.storage;

import seedu.addressbook.data.AddressBook;

public class StorageStub implements Storage {

	@Override
	public AddressBook load() throws StorageOperationException {
		
		return null;
	}

	@Override
	public String getPath() {
		return null;
	}

	@Override
	public void save(AddressBook addressBook) throws StorageOperationException {
	}

}

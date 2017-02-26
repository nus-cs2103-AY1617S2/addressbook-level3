package seedu.addressbook.logic;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.storage.Storage;

public class StorageStub implements Storage	{

	public StorageStub(String path) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void save(AddressBook addressBook) throws StorageOperationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AddressBook load() throws StorageOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPath() {
		// TODO Auto-generated method stub
		return null;
	}

}

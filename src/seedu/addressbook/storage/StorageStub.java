package seedu.addressbook.storage;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.storage.StorageFile.StorageOperationException;

/**
 * StorageStub class to facilitate DIP
 *
 */


public class StorageStub extends Storage{

	@Override
	public void save(AddressBook addressBook) throws StorageOperationException {
		;
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

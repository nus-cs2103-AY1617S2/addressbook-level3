package seedu.addressbook.storage;

import seedu.addressbook.data.AddressBook;

public class StorageStub extends StorageFile {
	public StorageStub() throws InvalidStorageFilePathException {
		super();
	}
	
	public StorageStub(String filePath) throws InvalidStorageFilePathException {
		super(filePath);
	}
	
	@Override
	public void save(AddressBook addressBook) {
		// do nothing
	}
}

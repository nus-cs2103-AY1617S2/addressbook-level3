package seedu.addressbook.storage;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.storage.Storage;
import seedu.addressbook.storage.StorageFile.StorageOperationException;

/**
 * Stub for Logic Test
 */
public class StorageStub extends Storage {

	private String path;
	private AddressBook addressBook;
	
	public StorageStub() {
		
	}
	
	public StorageStub(String filePath) {
		this.path = filePath;
	}
	
	@Override
	public void save(AddressBook addressBook) throws StorageOperationException{
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

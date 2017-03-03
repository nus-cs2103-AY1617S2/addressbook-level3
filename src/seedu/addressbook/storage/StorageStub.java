package seedu.addressbook.storage;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.storage.StorageFile.StorageOperationException;

import java.nio.file.Path;
import java.nio.file.Paths;

public class StorageStub extends Storage {

	public StorageStub(String path) {
	}

	@Override
	public void save(AddressBook addressBook) throws StorageOperationException {
		
	}

	@Override
	public AddressBook load() throws StorageOperationException {
 		return null;
	}
	
	@Override
	public String getPath() {
		return null;
	}
 
 }
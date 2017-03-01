package seedu.addressbook.storage;
import seedu.addressbook.data.AddressBook;
import seedu.addressbook.storage.StorageFile;

public class StorageStub extends StorageFile{
	
	public StorageStub(String filePath) throws InvalidStorageFilePathException {
		super(filePath);
	}

	public StorageStub() throws InvalidStorageFilePathException {
		super();
	}
	
	@Override
	public void save(AddressBook addressBook) throws StorageOperationException {
		// Stubbed out to do nothing
	}
}

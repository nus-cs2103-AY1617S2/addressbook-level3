package seedu.addressbook.storage;
import seedu.addressbook.data.AddressBook;
import seedu.addressbook.storage.StorageFile.StorageOperationException;

public abstract class Storage {
	public abstract AddressBook load() throws StorageOperationException, seedu.addressbook.storage.StorageStub.StorageOperationException;
	public abstract String getPath();
	public abstract void save(AddressBook addressBook) throws StorageOperationException;
}

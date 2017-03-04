package seedu.addressbook.storage;
import seedu.addressbook.data.AddressBook;


/**
 * Represents the interface between Storagefile and logic in the address book.
 */

public abstract class Storage {
	
	public abstract void save(AddressBook addressBook) throws Exception;
	public abstract AddressBook load() throws Exception;
	public abstract String getPath();
	
}

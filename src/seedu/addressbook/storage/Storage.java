package seedu.addressbook.storage;

import java.io.IOException;

import seedu.addressbook.data.AddressBook;

/*
 * A storage interface that provide basic file access functions.
 */
public abstract class Storage {
	

    /**
     * Signals that the given file path does not fulfill the storage filepath constraints.
     */
    public static class InvalidStorageFilePathException extends StorageException {
        public InvalidStorageFilePathException(String message) {
            super(message);
        }
    }

    /**
     * Signals that some error has occured while trying to convert and read/write data between the application
     * and the storage file.
     */
    public static class StorageOperationException extends StorageException {
        public StorageOperationException(String message) {
            super(message);
        }
    }
	
	
	/*
	 * Backup current addresss book to a designated place
	 */
	public abstract void backup(AddressBook addressbook) throws StorageException, IOException;
	
	/*
	 * Get the path of current storage
	 */
	public abstract String getPath();
	
	/*
	 * Load an address book from the storage
	 */
	public abstract AddressBook load() throws StorageException;
	
	/*
	 * Restore the address book from the backup
	 */
	public abstract void restore(AddressBook addressbook) throws StorageException, IOException;
	
	/*
	 * Save the current address book to storage
	 */
	public abstract void save(AddressBook addressbook) throws StorageException, IOException;
}

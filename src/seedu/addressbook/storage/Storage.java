package seedu.addressbook.storage;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.exception.IllegalValueException;

public interface Storage {

    /**
     * Signals that the given file path does not fulfill the storage filepath constraints.
     */
    public class InvalidStorageFilePathException extends IllegalValueException {
		public InvalidStorageFilePathException(String message) {
			super(message);
			// TODO Auto-generated constructor stub
		}}
    /**
     * Signals that some error has occured while trying to convert and read/write data between the application
     * and the storage file.
     */
    public static class StorageOperationException extends Exception {
        public StorageOperationException(String message) {
            super(message);
        }
    }
    public String getPath();
    public void save(AddressBook addressBook)throws StorageOperationException;
    public AddressBook load() throws StorageOperationException ;
    public  Storage newStorage() throws InvalidStorageFilePathException;
    public  Storage newStorage(String path) throws InvalidStorageFilePathException;


}

package seedu.addressbook.storage;

public class StorageException extends Exception {

	public StorageException(String message) {
	    super(message);
	}

	public StorageException(String message, Throwable throwable) {
	    super(message, throwable);
	}

}

package seedu.addressbook.storage;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.exception.IllegalValueException;

import java.nio.file.Path;

/** Represents a form of storage for the application */
public abstract class Storage {
    /** Default file path used if the user doesn't provide the file name. */
    public static final String DEFAULT_STORAGE_FILEPATH = "addressbook.txt";
    protected Path path;

    /* Note: Note the use of nested classes below.
     * More info https://docs.oracle.com/javase/tutorial/java/javaOO/nested.html
     */

    /**
     * Signals that the given file path does not fulfill the storage filepath constraints.
     */
    public static class InvalidStorageFilePathException extends IllegalValueException {
        public InvalidStorageFilePathException(String message) {
            super(message);
        }
    }

    /**
     * Signals that some error has occured while trying to convert and read/write data between the application
     * and the storage file.
     */
    public static class StorageOperationException extends Exception {
        public StorageOperationException(String message) {
            super(message);
        }
    }

    /**
     * Returns true if the given path is acceptable as a storage file.
     * The file path is considered acceptable if it ends with '.txt'
     */
    static boolean isValidPath(Path filePath) {
        return filePath.toString().endsWith(".txt");
    }

    /** Returns the path of the storage */
    public String getPath() {
        return path.toString();
    }

    /**
     * Creates the StorageFile object based on the default storage path.
     * @throws StorageFile.InvalidStorageFilePathException if the target file path is incorrect.
     */
    public static StorageFile initializeStorageFile() throws Storage.InvalidStorageFilePathException {
        return new StorageFile();
    }

    public abstract void save(AddressBook addressBook) throws StorageOperationException;
    public abstract AddressBook load() throws StorageOperationException;

}

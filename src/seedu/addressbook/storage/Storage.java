package seedu.addressbook.storage;

import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.storage.jaxb.AdaptedAddressBook;

public abstract class Storage {
    /** Default file path used if the user doesn't provide the file name. */
    public static final String DEFAULT_STORAGE_FILEPATH = "addressbook.txt";

    /*
     * Note: Note the use of nested classes below. More info
     * https://docs.oracle.com/javase/tutorial/java/javaOO/nested.html
     */

    /**
     * Signals that the given file path does not fulfill the storage filepath
     * constraints.
     */
    public static class InvalidStorageFilePathException extends IllegalValueException {
        private static final long serialVersionUID = 1L;

        public InvalidStorageFilePathException(String message) {
            super(message);
        }
    }

    /**
     * Signals that some error has occured while trying to convert and
     * read/write data between the application and the storage file.
     */
    public static class StorageOperationException extends Exception {
        private static final long serialVersionUID = 1L;

        public StorageOperationException(String message) {
            super(message);
        }
    }

    public final JAXBContext jaxbContext;

    public final Path path;

    /**
     * @throws InvalidStorageFilePathException
     *             if the default path is invalid
     */
    public Storage() throws InvalidStorageFilePathException {
        this(DEFAULT_STORAGE_FILEPATH);
    }

    public Storage(String filePath) throws InvalidStorageFilePathException {
        try {
            jaxbContext = JAXBContext.newInstance(AdaptedAddressBook.class);
        } catch (JAXBException jaxbe) {
            throw new RuntimeException("jaxb initialisation error");
        }
        path = Paths.get(filePath);
        if (!isValidPath(path)) {
            throw new InvalidStorageFilePathException("Storage file should end with '.txt'");
        }
    }

    /**
     * Returns true if the given path is acceptable as a storage file. The file
     * path is considered acceptable if it ends with '.txt'
     */
    public abstract boolean isValidPath(Path filePath);

    /**
     * Saves all data to this storage file.
     *
     * @throws StorageOperationException
     *             if there were errors converting and/or storing data to file.
     */
    public abstract void save(AddressBook addressBook) throws StorageOperationException;

    /**
     * Loads data from this storage file.
     *
     * @throws StorageOperationException
     *             if there were errors reading and/or converting data from
     *             file.
     */
    public abstract AddressBook load() throws StorageOperationException;

    public abstract String getPath();
}

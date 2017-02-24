package seedu.addressbook.storage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.storage.Storage.StorageOperationException;
import seedu.addressbook.storage.jaxb.AdaptedAddressBook;

public class StorageStub extends Storage{
    
    /** Default file path used if the user doesn't provide the file name. */
    public static final String DEFAULT_STORAGE_FILEPATH = "addressbook.txt";

    /* Note: Note the use of nested classes below.
     * More info https://docs.oracle.com/javase/tutorial/java/javaOO/nested.html
     */

    private final JAXBContext jaxbContext;

    public final Path path;

    /**
     * @throws InvalidStoragePathException if the default path is invalid
     */
    public StorageStub() throws InvalidStoragePathException {
        this(DEFAULT_STORAGE_FILEPATH);
    }

    /**
     * @throws InvalidStoragePathException if the given file path is invalid
     */
    public StorageStub(String filePath) throws InvalidStoragePathException {
        try {
            jaxbContext = JAXBContext.newInstance(AdaptedAddressBook.class);
        } catch (JAXBException jaxbe) {
            throw new RuntimeException("jaxb initialisation error");
        }

        path = Paths.get(filePath);
        if (!isValidPath(path)) {
            throw new InvalidStoragePathException("Storage file should end with '.txt'");
        }
    }
    
    /**
     * Returns true if the given path is acceptable as a storage file.
     * The file path is considered acceptable if it ends with '.txt'
     */
    private static boolean isValidPath(Path filePath) {
        return filePath.toString().endsWith(".txt");
    }

    @Override
    public AddressBook load() throws StorageOperationException {
        return null;
    }

    @Override
    public String getPath() {
        return null;
    }

    @Override
    public void save(AddressBook addressBook) throws StorageOperationException {}
    
}

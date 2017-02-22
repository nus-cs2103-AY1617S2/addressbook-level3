package seedu.addressbook.storage;

import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.storage.Storage.InvalidStoragePathException;
import seedu.addressbook.storage.jaxb.AdaptedAddressBook;

public class StorageStub extends Storage {
	
	/** Default file path used if the user doesn't provide the file name. */
	public final static String DEFAULT_STORAGE_FILEPATH = "addressbook.txt";

   /* Note: Note the use of nested classes below.
     * More info https://docs.oracle.com/javase/tutorial/java/javaOO/nested.html
     */
    private final JAXBContext jaxbContext;

    
    public final Path path;
    public StorageStub() throws InvalidStoragePathException {
        this(DEFAULT_STORAGE_FILEPATH);
    }

    /**
     * @throws InvalidStorageFilePathException if the given file path is invalid
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
    
	private boolean isValidPath(Path filePath) {
		return filePath.toString().endsWith(".txt");
	}

	@Override
	public AddressBook load() throws StorageOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(AddressBook addressBook) throws StorageOperationException {
		return;
		
	}

	@Override
	public String getPath() {
		// TODO Auto-generated method stub
		return null;
	}
	
}

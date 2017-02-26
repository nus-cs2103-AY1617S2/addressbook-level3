package seedu.addressbook.storage;

import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.storage.Storage.InvalidStorageFilePathException;
import seedu.addressbook.storage.jaxb.AdaptedAddressBook;

public class StorageStub extends Storage {
	
	public final Path path;
	private final JAXBContext jaxbContext;
	public static final String DEFAULT_STORAGE_FILEPATH = "addressbook.txt";

	
	public StorageStub() throws InvalidStorageFilePathException {
        this(DEFAULT_STORAGE_FILEPATH);
    }
	
	public StorageStub(String filePath)throws InvalidStorageFilePathException{
		try {
            jaxbContext = JAXBContext.newInstance(AdaptedAddressBook.class);
        } catch (JAXBException jaxbe) {
            throw new RuntimeException("jaxb initialisation error");
        }

        path = Paths.get(filePath);
	}
	
	@Override
	public AddressBook load() throws StorageOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPath() {
		// TODO Auto-generated method stub
		return path.toString();
	}

	@Override
	public void save(AddressBook addressBook) throws StorageOperationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Storage initializeStorage() throws InvalidStorageFilePathException {
		return new StorageStub();
	}
}

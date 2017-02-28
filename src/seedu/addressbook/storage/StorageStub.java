package seedu.addressbook.storage;

import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.storage.StorageFile.InvalidStorageFilePathException;
import seedu.addressbook.storage.StorageFile.StorageOperationException;
import seedu.addressbook.storage.jaxb.AdaptedAddressBook;

public class StorageStub extends Storage{
    
    private final JAXBContext jaxbContext;

    public final Path path;
    
    private AddressBook addressBook;
    
    /**
     * @throws InvalidStorageFilePathException if the default path is invalid
     */
    public StorageStub() throws InvalidStorageFilePathException {
        this(DEFAULT_STORAGE_FILEPATH);
    }
    
    /**
     * @throws InvalidStorageFilePathException if the given file path is invalid
     */
    public StorageStub(String filePath) throws InvalidStorageFilePathException {
        try {
            jaxbContext = JAXBContext.newInstance(AdaptedAddressBook.class);
        } catch (JAXBException jaxbe) {
            throw new RuntimeException("jaxb initialisation error");
        }
        path = Paths.get(filePath);
    }
    
    public void setAddressBook (AddressBook addressBook){
        this.addressBook = addressBook;
    }
    
    @Override
    public void save(AddressBook addressBook) throws StorageOperationException {}

    @Override
    public AddressBook load() throws StorageOperationException {
        return addressBook;
    }

    @Override
    public String getPath() {
        return null;
    }
}

package seedu.addressbook.storage;

import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.storage.StorageFile.InvalidStorageFilePathException;
import seedu.addressbook.storage.StorageFile.StorageOperationException;
import seedu.addressbook.storage.jaxb.AdaptedAddressBook;

public class StorageStub extends Storage {
    
    private AddressBook addressBook;
    
    public StorageStub(AddressBook addressBook) {
        this.addressBook = addressBook;
    }

    @Override
    public void save(AddressBook addressBook) {
        
    }

    @Override
    public AddressBook load() {
        return addressBook;
    }

    @Override
    public String getPath() {
        return null;
    }

}

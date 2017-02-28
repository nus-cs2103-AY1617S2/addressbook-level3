package seedu.addressbook.storage;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.storage.jaxb.AdaptedAddressBook;
import seedu.addressbook.storage.Storage;
import seedu.addressbook.storage.Storage.StorageOperationException;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;

public class StorageStub extends Storage {


    AddressBook addressBook;

    public StorageStub() throws InvalidStorageFilePathException {
        super();
    }
    public StorageStub(String filePath) throws InvalidStorageFilePathException {
        super(filePath);
    }

    /**
     * Saves all data to this storage file.
     * This method is empty in StorageStub
     *
     * @throws StorageOperationException if there were errors converting and/or storing data to file.
     */
    public void save(AddressBook addressBook) throws StorageOperationException {
        this.addressBook = addressBook;
    }

    /**
     * Loads data from this storage file.
     *
     * @throws StorageOperationException if there were errors reading and/or converting data from file.
     */
    public AddressBook load() throws StorageOperationException {
        return this.addressBook;
    }

}

package seedu.addressbook.storage;

import java.nio.file.Path;

import javax.xml.bind.JAXBContext;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.storage.StorageFile.StorageOperationException;

public abstract class Storage {

    
    public abstract void save(AddressBook addressBook) throws StorageOperationException;
    
    public abstract AddressBook load() throws StorageOperationException;
    
    public abstract String getPath();
}

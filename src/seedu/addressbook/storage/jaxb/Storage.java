package seedu.addressbook.storage.jaxb;

/**
 * Created by YeFenyi on 2017/2/16.
 */

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.storage.StorageFile.StorageOperationException;


public interface Storage {
    public abstract String getPath();
    public abstract AddressBook load() throws StorageOperationException;
    public abstract void save(AddressBook addressBook) throws StorageOperationException;


}


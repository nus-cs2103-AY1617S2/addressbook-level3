package seedu.addressbook.storage;

import seedu.addressbook.storage.Storage;
import seedu.addressbook.storage.StorageFile;

/**
 * Factory class to create Storage objects
 */
public class StorageFactory {

    public StorageFile createStorageFile() throws Storage.InvalidStorageFilePathException{
        return new StorageFile();
    }
}

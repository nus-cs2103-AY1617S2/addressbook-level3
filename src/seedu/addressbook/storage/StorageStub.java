/**
 *
 */
package seedu.addressbook.storage;

import seedu.addressbook.data.AddressBook;

import java.nio.file.Paths;

/** StorageStub is used for testing the Logic class in JUnit */
public class StorageStub extends Storage {

    /**
     * @throws InvalidStorageFilePathException if the given file path is invalid
     */
    public StorageStub(String filePath) throws InvalidStorageFilePathException {
        path = Paths.get(filePath);
        if (!Storage.isValidPath(path)) {
            throw new InvalidStorageFilePathException("Storage file should end with '.txt'");
        }
    }

    @Override
    public void save(AddressBook addressBook) throws StorageOperationException {
        //Do Nothing
    }


    @Override
    public AddressBook load() throws StorageOperationException {
        //Do Nothing
        return null;
    }

}

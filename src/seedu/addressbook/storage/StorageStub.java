package seedu.addressbook.storage;

import seedu.addressbook.data.AddressBook;

public class StorageStub implements Storage {

    /**
     * @throws InvalidStorageFilePathException if the default path is invalid
     */
    public StorageStub() throws InvalidStorageFilePathException {
    }

    /**
     * @throws InvalidStorageFilePathException if the given file path is invalid
     */
    public StorageStub(String filePath) throws InvalidStorageFilePathException {
        
    }
    
    @Override
    public AddressBook load() throws StorageOperationException {
        return AddressBook.empty();
    }

    @Override
    public String getPath() {
        return "";
    }

    @Override
    public void save(AddressBook addressBook) throws StorageOperationException {
        
    }

}

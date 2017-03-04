package seedu.addressbook.storage;

import java.nio.file.Path;

import seedu.addressbook.data.AddressBook;

public class StorageStub extends Storage {

    public StorageStub() throws InvalidStorageFilePathException {
        super();
    }

    public StorageStub(String filePath) throws InvalidStorageFilePathException {
        super(filePath);
    }

    @Override
    public boolean isValidPath(Path filePath) {
        return false;
    }

    @Override
    public void save(AddressBook addressBook) throws StorageOperationException {

    }

    @Override
    public AddressBook load() throws StorageOperationException {
        return null;
    }

    @Override
    public String getPath() {
        return null;
    }

}
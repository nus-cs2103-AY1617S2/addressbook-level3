package seedu.addressbook.storage;

import java.nio.file.Path;
import java.nio.file.Paths;

import seedu.addressbook.data.AddressBook;


/**
 * Substitutes dependency on StorageFile
 *
 */
public class StorageStub extends Storage {
    
    public StorageStub(String path) throws InvalidStoragePathException{
        if(!isValidPath(Paths.get(path))) {
            throw new InvalidStoragePathException("Storage file should end with '.txt'");
        }
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
    
    /**
     * Returns true if the given path is acceptable as a storage file.
     * The file path is considered acceptable if it ends with '.txt'
     */
    private static boolean isValidPath(Path filePath) {
        return filePath.toString().endsWith(".txt");
    }
    
}

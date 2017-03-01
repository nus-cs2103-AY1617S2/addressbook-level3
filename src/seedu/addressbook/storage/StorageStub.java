package seedu.addressbook.storage;
import seedu.addressbook.data.AddressBook;
import seedu.addressbook.logic.Logic;
import seedu.addressbook.storage.StorageFile.InvalidStorageFilePathException;
import seedu.addressbook.storage.StorageFile.StorageOperationException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.rules.TemporaryFolder;

public class StorageStub extends Storage{
	public String getPath(){
         return "";
	};
	public void save(AddressBook addressBook)throws StorageOperationException{};
	public AddressBook load() throws StorageOperationException{
		return new AddressBook();
		}

	    @Before
	    public StorageFile setup(TemporaryFolder saveFolder, String documentPath) throws Exception {
	    	StorageFile saveFile = new StorageFile(saveFolder.newFile(documentPath).getPath());
            return saveFile;
	    }
}

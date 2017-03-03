package seedu.addressbook.storage;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.storage.StorageFile.StorageOperationException;

public class StorageStub extends Storage {
	
	private String path;
	private AddressBook addressBook;

	public StorageStub(String p) {
		// TODO Auto-generated constructor stub
		this.path = p;
	}
	
	  public void save(AddressBook addressBook) throws StorageOperationException{
	  
	  }
	  
	  
	  
	  public AddressBook load() throws StorageOperationException{
		  return this.addressBook;
	  }
	  
	  public String getPath(){
		  return this.path;
	  }
}

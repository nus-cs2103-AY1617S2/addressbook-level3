package seedu.addressbook.commands;

import seedu.addressbook.storage.StorageFile;
import seedu.addressbook.storage.StorageFile.InvalidStorageFilePathException;
import seedu.addressbook.storage.StorageFile.StorageOperationException;

/**
 * Specify a specific folder and a file for data storage
 */
public class SaveFileCommand extends Command{
    
    public static final String COMMAND_WORD = "saveFile";
    
    public static final String MESSAGE_SUCCESS = "Saved to the file!";

    public static final String MESSAGE_INVALID_FILE = "The new file path is invalid";
    
    private String newFilePath;
    
    private StorageFile newStorage;

    public SaveFileCommand(String newPath){
       newFilePath=newPath;
    }
    

    @Override
    public CommandResult execute() throws StorageOperationException {
        try {
            newStorage = new StorageFile(this.newFilePath);
        } catch(InvalidStorageFilePathException e){
             return new CommandResult(MESSAGE_INVALID_FILE);
        }
        newStorage.save(addressBook);
        return new CommandResult(String.format(MESSAGE_SUCCESS));
    }


}

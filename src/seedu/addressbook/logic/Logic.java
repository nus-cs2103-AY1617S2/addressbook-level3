package seedu.addressbook.logic;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Stack;

import seedu.addressbook.commands.Command;
import seedu.addressbook.commands.CommandResult;
import seedu.addressbook.commands.UndoableCommand;
import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.parser.Parser;
import seedu.addressbook.storage.StorageFile;

/**
 * Represents the main Logic of the AddressBook.
 */
public class Logic {


    private StorageFile storage;
    private AddressBook addressBook;
    
    private Stack<UndoableCommand> undoStack;

    /** The list of person shown to the user most recently.  */
    private List<? extends ReadOnlyPerson> lastShownList = Collections.emptyList();

    public Logic() throws Exception{
        setStorage(initializeStorage());
        setAddressBook(storage.load());
        
        undoStack = new Stack<UndoableCommand>();
    }

    Logic(StorageFile storageFile, AddressBook addressBook){
        setStorage(storageFile);
        setAddressBook(addressBook);
        
        undoStack = new Stack<UndoableCommand>();
    }

    void setStorage(StorageFile storage){
        this.storage = storage;
    }

    void setAddressBook(AddressBook addressBook){
        this.addressBook = addressBook;
    }

    /**
     * Creates the StorageFile object based on the user specified path (if any) or the default storage path.
     * @throws StorageFile.InvalidStorageFilePathException if the target file path is incorrect.
     */
    private StorageFile initializeStorage() throws StorageFile.InvalidStorageFilePathException {
        return new StorageFile();
    }

    public String getStorageFilePath() {
        return storage.getPath();
    }

    /**
     * Unmodifiable view of the current last shown list.
     */
    public List<ReadOnlyPerson> getLastShownList() {
        return Collections.unmodifiableList(lastShownList);
    }

    protected void setLastShownList(List<? extends ReadOnlyPerson> newList) {
        lastShownList = newList;
    }

    /**
     * Parses the user command, executes it, and returns the result.
     * @throws Exception if there was any problem during command execution.
     */
    public CommandResult execute(String userCommandText) throws Exception {
        Command command = new Parser().parseCommand(userCommandText);
        CommandResult result = execute(command);
        
        if(command instanceof UndoableCommand){
            //TODO: Avoid downcasting?
            undoStack.push((UndoableCommand)command);
        }
        
        recordResult(result);
        return result;
    }

    /**
     * Executes the command, updates storage, and returns the result.
     *
     * @param command user command
     * @return result of the command
     * @throws Exception if there was any problem during command execution.
     */
    private CommandResult execute(Command command) throws Exception {
        command.setData(addressBook, lastShownList);
        CommandResult result = command.execute();
        
        if(command instanceof UndoableCommand){
            //TODO: Avoid downcasting?
            undoStack.push((UndoableCommand)command);
        }
        
        storage.save(addressBook);
        return result;
    }
    
    public boolean undo() {
        if(!undoStack.isEmpty()){
            UndoableCommand cmd = undoStack.pop();
            try {
                cmd.undo();
                return true;
            } catch (Exception e) {
                // TODO Auto-generated catch block
                return false;
            }
        }
        
        return false;
    }
    

    /** Updates the {@link #lastShownList} if the result contains a list of Persons. */
    private void recordResult(CommandResult result) {
        final Optional<List<? extends ReadOnlyPerson>> personList = result.getRelevantPersons();
        if (personList.isPresent()) {
            lastShownList = personList.get();
        }
    }
}

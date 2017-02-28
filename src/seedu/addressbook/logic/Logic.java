package seedu.addressbook.logic;

import seedu.addressbook.commands.Command;
import seedu.addressbook.commands.CommandResult;
import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.parser.Parser;
import seedu.addressbook.storage.Storage;
import seedu.addressbook.storage.StorageFile;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Represents the main Logic of the AddressBook.
 */
public class Logic {


    private StorageFile storage;
    private AddressBook addressBook;

    /** The list of person shown to the user most recently.  */
    private List<? extends ReadOnlyPerson> lastShownList = Collections.emptyList();

    public Logic() throws Exception{
        setStorage(Storage.initializeStorage());
        setAddressBook(storage.load());
    }

    Logic(StorageFile storage, AddressBook addressBook){
        setStorage(storage);
        setAddressBook(addressBook);
    }

    void setStorage(StorageFile storageFile){
        this.storage = storageFile;
    }

    void setAddressBook(AddressBook addressBook){
        this.addressBook = addressBook;
    }

    public String getStoragePath() {
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
        storage.save(addressBook);
        return result;
    }

    /** Updates the {@link #lastShownList} if the result contains a list of Persons. */
    private void recordResult(CommandResult result) {
        final Optional<List<? extends ReadOnlyPerson>> personList = result.getRelevantPersons();
        if (personList.isPresent()) {
            lastShownList = personList.get();
        }
    }
}

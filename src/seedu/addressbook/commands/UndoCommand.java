package seedu.addressbook.commands;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import seedu.addressbook.common.Messages;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.Address;
import seedu.addressbook.data.person.Email;
import seedu.addressbook.data.person.Name;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.Phone;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.person.UniquePersonList;
import seedu.addressbook.data.person.UniquePersonList.PersonNotFoundException;
import seedu.addressbook.data.tag.Tag;
import seedu.addressbook.data.tag.UniqueTagList;


/**
 * Undo previous modifications to the address book.
 */
public class UndoCommand extends Command {
    public static final String COMMAND_WORD = "undo";


    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" + "Undo the previous command done on the address book."
            + "Example: " + COMMAND_WORD;
    
    public static final String MESSAGE_SUCCESS_ADD = "'Add' successfully reverted";
    public static final String MESSAGE_SUCCESS_DELETE = "'Delete' successfully reverted";
    public static final String MESSAGE_NO_PREVIOUS_COMMAND = "No previous command to undo";
    public static final String MESSAGE_FAILURE = "Undo Failed";

    private static Stack<CommandRecord> history = new Stack<CommandRecord>();

    
    public UndoCommand() {};

    public static void recordAddCommand(Person person) {
        CommandRecord addCommand = new CommandRecord(CommandRecord.Action.ADD, person);
        history.push(addCommand);
    }
    
    public static void recordDeleteCommand(Person person) {
        CommandRecord deleteCommand = new CommandRecord(CommandRecord.Action.DELETE, person);
        history.push(deleteCommand);
    }
    
    @Override
    public CommandResult execute() {
        CommandRecord commandRecord = history.pop();
        if (commandRecord.getAction() == CommandRecord.Action.DELETE) {
            return executeAdd(commandRecord.getPerson());
        }
        else {
            return executeDelete(commandRecord.getPerson());
        }
    }
    
    private CommandResult executeAdd(Person toAdd) {
        try {
            addressBook.addPerson(toAdd);
            return new CommandResult(String.format(MESSAGE_SUCCESS_DELETE, toAdd));
        } catch (UniquePersonList.DuplicatePersonException dpe) {
            return new CommandResult(MESSAGE_FAILURE);
        }
    }
    
    private CommandResult executeDelete(ReadOnlyPerson toDelete) {
        try {
            addressBook.removePerson(toDelete);
            return new CommandResult(String.format(MESSAGE_SUCCESS_ADD, toDelete));
        } catch (PersonNotFoundException pnfe) {
            return new CommandResult(MESSAGE_FAILURE);
        }
    }
}

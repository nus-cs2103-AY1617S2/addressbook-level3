package seedu.addressbook.commands;

import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.Collections;
import java.util.List;


/**
 * Lists all persons in the address book to the user in lexicographic order.
 */
public class ListInOrderCommand extends Command {

    public static final String COMMAND_WORD = "listinorder";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" 
            + "Displays all persons in the address book as a list in lexicographic order.\n\t"
            + "Example: " + COMMAND_WORD;


    @Override
    public CommandResult execute() {
        List<Person> allPersons = addressBook.getAllPersons().unsortedListView();
        Collections.sort(allPersons);
        return new CommandResult(getMessageForPersonListShownSummary(allPersons), allPersons);
    }
}

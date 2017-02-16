package seedu.addressbook.commands;

import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * Lists all persons in the address book to the user.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n"
            + "Displays all persons in the address book in alphabetical order as a list with index numbers.\n\t"
            + "Example: " + COMMAND_WORD;


    @Override
    public CommandResult execute() {
        List<ReadOnlyPerson> allPersons = addressBook.getAllPersons().immutableListView();
        Collections.sort(allPersons, new PersonComparator());
        return new CommandResult(getMessageForPersonListShownSummary(allPersons), allPersons);
    }
}

/** Comparator to sort a list of ReadOnlyPerson by their names */
class PersonComparator implements Comparator<ReadOnlyPerson> {

    @Override
    public int compare(ReadOnlyPerson o1, ReadOnlyPerson o2) {
        return o1.getName().fullName.compareTo(o2.getName().fullName);
    }

}
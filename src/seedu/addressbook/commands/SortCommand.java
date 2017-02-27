package seedu.addressbook.commands;

import seedu.addressbook.data.person.Person;
import java.util.*;

/**
 * Sorts and lists all persons in address book in alphabetical order.
 * Keyword matching is case sensitive.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" + "Sorts and lists all persons in address book in alphabetical order.\n\t"
            + "Example: " + COMMAND_WORD;

    @Override
    public CommandResult execute() {
        final List<Person> personsFound = getSortedNames();
        return new CommandResult(getMessageForPersonListShownSummary(personsFound), personsFound);
    }

    /**
     * Sorts all persons in the address book according to names in alphabetical order.
     *
     * @return list of persons sorted
     */
    private List<Person> getSortedNames() {
        return addressBook.getAllPersons().sort();
    }

}

package seedu.addressbook.commands;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import seedu.addressbook.data.person.ReadOnlyPerson;

/**
 * Sorts the persons by a field and displays it to the user. 
 *
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" 
            + "Displays all the persons in the address book with index numbers, sorted by a field.\n\t"
            + "Parameters: field name\n\t"
            + "Example: " + COMMAND_WORD + " name";
    
    public static final int SORT_BY_NAME = 0;
    public static final int SORT_BY_PHONE = 1;
    public static final int SORT_BY_ADDRESS = 2;
    
    public final int sortBy;
    private final Comparator<ReadOnlyPerson> sorter;
    
    public SortCommand(int sortBy){
        this.sortBy = sortBy;
        switch(sortBy){
            case SORT_BY_ADDRESS:
                sorter = new AddressComparator();
                break;
            case SORT_BY_NAME:
                sorter = new NameComparator();
                break;
            case SORT_BY_PHONE:
                sorter = new PhoneComparator();
                break;
            default:
                sorter = null;
        }
    }
    
    @Override
    public CommandResult execute() {
        List<ReadOnlyPerson> sortedPersons = sortList(sorter, addressBook.getAllPersons().immutableListView());
        return new CommandResult(getMessageForPersonListShownSummary(sortedPersons), sortedPersons);
    }
    
    public static List<ReadOnlyPerson> sortList(Comparator<ReadOnlyPerson> sorter, List<ReadOnlyPerson> allPersons){
        Collections.sort(allPersons, sorter);
        return allPersons;
    }
    
    public class AddressComparator implements Comparator<ReadOnlyPerson>{
        public int compare(ReadOnlyPerson first, ReadOnlyPerson second){
            return first.getAddress().toString().compareTo(second.getAddress().toString());
        }
    }
    
    public class NameComparator implements Comparator<ReadOnlyPerson>{
        public int compare(ReadOnlyPerson first, ReadOnlyPerson second){
            return first.getName().toString().compareTo(second.getName().toString());
        }
    }
    
    public class PhoneComparator implements Comparator<ReadOnlyPerson>{
        public int compare(ReadOnlyPerson first, ReadOnlyPerson second){
            return first.getPhone().toString().compareTo(second.getPhone().toString());
        }
    }
    
}

package seedu.addressbook.commands;

import java.util.Comparator;
import java.util.List;

import seedu.addressbook.data.person.Person;
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
    public static final int SORT_BY_EMAIL = 3; 
    
    private final String sortFieldString;

    private final Comparator<Person> fieldComparator;
    
    public SortCommand(int sortBy){
        switch(sortBy){
            case SORT_BY_ADDRESS:
                fieldComparator = new AddressComparator();
                sortFieldString = "address";
                break;
                
            case SORT_BY_NAME:
                fieldComparator = new NameComparator();
                sortFieldString = "name";
                break;
                
            case SORT_BY_PHONE:
                fieldComparator = new PhoneComparator();
                sortFieldString = "phone number";
                break;
                
            case SORT_BY_EMAIL:
                fieldComparator = new EmailComparator();
                sortFieldString = "email";
                break;
                
            default: //guaranteed not to fall to default. Not too sure what exception to throw.
                sortFieldString = null;
                fieldComparator = null;
                break;
        }
    }
    
    @Override
    public CommandResult execute() {
        List<ReadOnlyPerson> sortedPersons = addressBook.getAllPersons().sortedImmutableList(fieldComparator);
        return new CommandResult(getMessageForSortedPersonShownSummary(sortedPersons, sortFieldString), sortedPersons);
    }
    
    /**
     * Compares two Person objects based on the lexicographical string values of Address field. 
     *
     */
    public class AddressComparator implements Comparator<Person>{
        public int compare(Person first, Person second){
            return first.getAddress().toString().compareTo(second.getAddress().toString());
        }
    }
    
    /**
     * Compares two Person objects based on the lexicographical string values of Name field.
     *
     */
    public class NameComparator implements Comparator<Person>{
        public int compare(Person first, Person second){
            return first.getName().toString().compareTo(second.getName().toString());
        }
    }
    
    /**
     * Compares two Person objects based on the lexicographical string values of Phone field.
     *
     */
    public class PhoneComparator implements Comparator<Person>{
        public int compare(Person first, Person second){
            return first.getPhone().toString().compareTo(second.getPhone().toString());
        }
    }
    
    /**
     * Compares two Person objects based on the lexicographical string values of Email field.
     *
     */
    public class EmailComparator implements Comparator<Person>{
        public int compare(Person first, Person second){
            return first.getEmail().toString().compareTo(second.getEmail().toString());
        }
    }
}

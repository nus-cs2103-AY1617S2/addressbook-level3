package seedu.addressbook.commands;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.Priority.PriorityLevel;
import seedu.addressbook.data.person.ReadOnlyPerson;


/**
 * Lists all persons in the address book to the user.
 */
public class SortCommand extends Command {

    public enum SortType {
        PRIORITY
    }
    
    public SortType sortType;
    
    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" + "Sorts previously listed people." + "\n"
            + "Can sort by priority for now." + "\n"
            + "Parameters: SORTTYPE " + "\n"
            + "Example: " + COMMAND_WORD
            + " priority";
    
    public static final String MESSAGE_SORT_CONSTRAINTS =
            "You can only " + COMMAND_WORD + "\n"
            + SortType.PRIORITY.toString();

    public SortCommand(SortType theSortType) throws IllegalValueException {
        if (theSortType == null) {
            throw new IllegalValueException(MESSAGE_SORT_CONSTRAINTS);
        }
        this.sortType = theSortType;
    }
    @Override
    public CommandResult execute() {
        
        List<? extends ReadOnlyPerson> sortedPersons = sortByPriority(relevantPersons);
        return new CommandResult(getMessageForPersonListSortedSummary(sortType), sortedPersons);
    }
    
    private List<? extends ReadOnlyPerson> sortByPriority(List<? extends ReadOnlyPerson> persons) {
        List<ReadOnlyPerson> sortedPersons = new ArrayList<ReadOnlyPerson>(persons);
        sortedPersons.sort(new ReadOnlyPersonPriorityComparator());
        return sortedPersons;
    }
    
    public class ReadOnlyPersonPriorityComparator implements Comparator<ReadOnlyPerson> {

        @Override
        public int compare(ReadOnlyPerson o1, ReadOnlyPerson o2)
        {
            // Highest priority to lowest priority
            PriorityLevel o1Priority = o1.getPriority().getPriorityLevel();
            PriorityLevel o2Priority = o2.getPriority().getPriorityLevel();
            return o2Priority.compareTo(o1Priority);
        }
    }
}

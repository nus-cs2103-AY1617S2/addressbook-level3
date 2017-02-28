package seedu.addressbook.commands;

import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.UniquePersonList;
import java.util.*;

/**
 * Sorts and lists all persons in address book according to the keywords in alphabetical order.
 * With multiple keywords, sorting is done starting from the 1st keyword from the left.
 * Returns an empty list if any keyword is not found/invalid.
 * Keyword matching is non case sensitive.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" + "Sorts and lists all persons in address book according to the keywords in alphabetical order.\n\t"
            + "Parameters: KEYWORD [MORE_KEYWORDS]... \n\t"
            + "Example: " + COMMAND_WORD + " name address";
    
    private final Set<String> keywords;
    
    
    public SortCommand(Set<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public CommandResult execute() {
        final List<Person> personsFound = getSortedList(keywords);
        return new CommandResult(getMessageForPersonSortedShownSummary(personsFound), personsFound);
    }

    /**
     * Sorts all persons in the address book according to names in alphabetical order.
     *
     * @return list of persons sorted
     */
    private List<Person> getSortedList(Set<String> keywords) {
        
        // Convert String array to Set<String>
        final String[] defaultKeywordsInStringArray = {"NAME", "EMAIL", "ADDRESS", "PHONE"};
        final Set<String> defaultKeywordsInSet = new HashSet<>(Arrays.asList(defaultKeywordsInStringArray));
        
        // Returns a empty list if any keyword is not one of the default keywords.
        for (String kw : keywords) {
            if (!defaultKeywordsInSet.contains(kw))
                return new ArrayList<Person>();
        }
        
        final UniquePersonList listOfAllPersons = addressBook.getAllPersons();
        
        return listOfAllPersons.sort(keywords);
    }

}

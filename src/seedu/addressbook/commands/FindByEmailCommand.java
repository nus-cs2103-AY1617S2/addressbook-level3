package seedu.addressbook.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.addressbook.data.person.ReadOnlyPerson;

/**
 * Finds and lists all persons in address book whose email contains any of the argument keywords.
 * Keyword matching is case sensitive.
 */

public class FindByEmailCommand extends Command {
    
    public static final String COMMAND_WORD = "findEmail";
    
    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" + "Finds all persons whose emails contain any of "
            + "the specified keywords (case-sensitive) and displays them as a list with index numbers.\n\t"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n\t"
            + "Example: " + COMMAND_WORD + " alice1212@yahoo.com";

    private final Set<String> keywords;

    public FindByEmailCommand(Set<String> keywords) {
        this.keywords = keywords;
        
    }
    
    /**
     * Returns copy of keywords in this command.
     */
    public Set<String> getKeywords() {
        return new HashSet<>(keywords);
    }
    
    @Override
    public CommandResult execute() {
        
        final List<ReadOnlyPerson> personsFound = getPersonsWithEmailContainingAnyKeyword(keywords);
        return new CommandResult(getMessageForPersonListShownSummary(personsFound), personsFound);
    }

    private List<ReadOnlyPerson> getPersonsWithEmailContainingAnyKeyword(Set<String> keywords) {
        
        final List<ReadOnlyPerson> matchedPersons = new ArrayList<>();
              
        for (ReadOnlyPerson person : addressBook.getAllPersons()) {
            for(String key: keywords){
                if(person.getEmail().toString().contains(key)){
                    matchedPersons.add(person);
                }
            }
            
        }
        
        return matchedPersons;
    }

}

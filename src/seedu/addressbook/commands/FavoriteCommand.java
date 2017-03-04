package seedu.addressbook.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.addressbook.data.person.ReadOnlyPerson;


/**
 * Finds and adds the person whose name contains any of the argument keywords to the favorites list 
 * Keyword matching is case sensitive.
 */

public class FavoriteCommand extends Command {
	
    public static final String COMMAND_WORD = "favorite";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" + "Finds person whose name contain any of "
            + "the specified keywords (case-sensitive) and adds them to the Favorites list .\n\t"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n\t"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

    public static final String MESSAGE_SUCCESS = "Person added to favorites: %1$s";
    //Person already in favorites
    
    private final Set<String> keywords;

    public FavoriteCommand(Set<String> keywords) {
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
        final List<ReadOnlyPerson> personsFound = getPersonsWithNameContainingAnyKeyword(keywords);
        return new CommandResult(getMessageForPersonListShownSummary(personsFound), personsFound);
    }

    /**
     * Retrieve all persons in the address book whose names contain some of the specified keywords.
     *
     * @param keywords for searching
     * @return list of persons found
     */
    private List<ReadOnlyPerson> getPersonsWithNameContainingAnyKeyword(Set<String> keywords) {
        final List<ReadOnlyPerson> FavoritePersons = new ArrayList<>();
        for (ReadOnlyPerson person : addressBook.getAllPersons()) {
            final Set<String> wordsInName = new HashSet<>(person.getName().getWordsInName());
            if (!Collections.disjoint(wordsInName, keywords)) {
            	FavoritePersons.add(person);
            }
        }
        return FavoritePersons;
    }

}

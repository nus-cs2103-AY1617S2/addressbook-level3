package seedu.addressbook.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.tag.Tag;
import seedu.addressbook.data.tag.UniqueTagList;

public class FindByTagCommand extends Command{

    public static final String COMMAND_WORD = "find-t";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" + "Finds all persons whose contain the entered tag.\n\t"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n\t"
            + "Example: " + COMMAND_WORD + " owesMoney";
    
    private final Set<String> keywords;

    public FindByTagCommand(Set<String> keywords) {
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
        List<ReadOnlyPerson> personsFound = null;
        try {
            personsFound = getPersonsWithTagsContainingAnyKeyword(keywords);
        } catch (IllegalValueException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new CommandResult(getMessageForPersonListShownSummary(personsFound), personsFound);
    }
    
    /**
     * Retrieve all persons in the address book whose tags contain keyword 
     *
     * @param keywords for searching
     * @return list of persons found
     * @throws IllegalValueException 
     */
    private List<ReadOnlyPerson> getPersonsWithTagsContainingAnyKeyword(Set<String> keywords) throws IllegalValueException {
        final List<ReadOnlyPerson> matchedPersons = new ArrayList<>();
        for (ReadOnlyPerson person : addressBook.getAllPersons()) {
            final UniqueTagList allTags = addressBook.getAllTags();
            for (String keyword:keywords){
                if(allTags.contains(new Tag(keyword))){
                    matchedPersons.add(person);
                }
            }
        }
        return matchedPersons;
    }
    
}

package seedu.addressbook.commands;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

import seedu.addressbook.data.tag.*;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.ReadOnlyPerson;
/**Find list of persons having the same tag in the addressbook and returns the persons as a list.
 * 
 * @author pengcheng
 * 
 */

public class FindByTagCommand extends Command{
	public static final String COMMAND_WORD = "findByTag";
	
	public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" + "Finds all persons with the same tag and display them"
			+ "as a list with index numbers.\n\t"
			+ "Parameters: TAG_NAME [MORE TAG_NAME]...\n\t"
			+ "Example: " + COMMAND_WORD + " CS2103T CS2101";
	
	public static final String MESSAGE_SUCCESS = "List of persons with the following tags: ";
	public static final String MESSSAGE_NO_SUCH_TAGS = "No persons has this tag.";
	
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
    
    /**
     * Checks the addressbook and returns a list of persons having the same tag.
     * @param tags
     * @return
     * 
     */
    private List<ReadOnlyPerson> getPersonsWithSameTag (Set<String> tags) throws IllegalValueException{
    	List<ReadOnlyPerson> personsWithMatchedTag = new ArrayList<ReadOnlyPerson>();
    	for(ReadOnlyPerson person: addressBook.getAllPersons()) {
    		for(String tag: tags) {
    			Tag tagToCheck = new Tag(tag);
    			if(person.getTags().contains(tagToCheck)) {
    				personsWithMatchedTag.add(person);
    			}
    		}
    	}    	
    	return personsWithMatchedTag;
    }
    
    
    @Override
    public CommandResult execute(){
    	List<ReadOnlyPerson> personsFound = null;
    	try {
    		personsFound = getPersonsWithSameTag(keywords);
    	} catch(IllegalValueException ive) {
    		return new CommandResult(MESSSAGE_NO_SUCH_TAGS);
    	}
    	return new CommandResult(getMessageForPersonListShownSummary(personsFound), personsFound);
    }
}

package seedu.addressbook.commands;

import seedu.addressbook.data.person.Email;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.Phone;

import java.util.*;

/**
 * Updates the first person in address book whose name contains any of the argument keywords.
 * Keyword matching is case sensitive.
 */
public class UpdateCommand extends Command {
	
	public enum UpdateWhich {
		EMAIL, PHONE
	};

    public static final String COMMAND_WORD = "update";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" + "Update the email/phone of one specified person "
            + "and displays them as a list with index numbers.\n\t"
            + "Parameters: KEYWORD [EMAIL/PHONE] [NEW EMAIL or PHONE]...\n\t"
            + "Example: " + COMMAND_WORD + " alice bob charlie" + " EMAIL" + " /blablabla@u.nus.edu";
    
    public static final String MESSAGE_PERSON_NOT_FOUND = "This Person is not in the addressbook";

    private final Set<String> keywords;

	private UpdateWhich updateWhich;

	private String updateString;
	
	private boolean isPrivate;

    public UpdateCommand(Set<String> keywords, UpdateWhich updateWhich, String updateString, Boolean isPrivate){
        this.keywords = keywords;
        this.updateWhich = updateWhich;
        this.updateString = updateString;
        this.isPrivate = isPrivate;
    }

    /**
     * Returns copy of keywords in this command.
     */
    public Set<String> getKeywords() {
        return new HashSet<>(keywords);
    }

    @Override
    public CommandResult execute() {
        final List<Person> personsFound = getPersonsWithNameContainingAnyKeyword(keywords);
        try {
        	final Person personFound = personsFound.get(0);
            if (updateWhich == UpdateWhich.EMAIL) {
            	Email email = new Email(updateString, isPrivate);
            	personFound.setEmail(email);
            }
            else {
            	Phone phone = new Phone(updateString, isPrivate);
            	personFound.setPhone(phone);
            }
            return new CommandResult(getMessageForPersonListShownSummary(personsFound), personsFound);
    	} catch (Exception e){
    		return new CommandResult(MESSAGE_PERSON_NOT_FOUND);
    	}
    }

    /**
     * Retrieve all persons in the address book whose names contain some of the specified keywords.
     *
     * @param keywords for searching
     * @return list of persons found
     */
    private List<Person> getPersonsWithNameContainingAnyKeyword(Set<String> keywords) {
        final List<Person> matchedPersons = new ArrayList<>();
        for (Person person : addressBook.getAllPersons()) {
            final Set<String> wordsInName = new HashSet<>(person.getName().getWordsInName());
            if (!Collections.disjoint(wordsInName, keywords)) {
                matchedPersons.add(person);
            }
        }
        return matchedPersons;
    }
}
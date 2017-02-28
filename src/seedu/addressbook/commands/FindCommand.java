package seedu.addressbook.commands;

import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.tag.Tag;
import seedu.addressbook.data.tag.UniqueTagList;

import java.util.*;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case sensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";
    public static final String MESSAGE_TAGS_LABEL = "\nTags: ";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" + "Finds all persons whose names contain any of "
            + "the specified keywords (case-sensitive) or whose tags contain any of the specified tags "
    		+ "and displays them as a list with index numbers.\n\t"
            + "Parameters: KEYWORD [MORE_KEYWORDS] [t/TAG]...\n\t"
            + "Example: " + COMMAND_WORD + " alice bob charlie t/owesMoney";

    private final Set<String> keywords;
    private final UniqueTagList tags;

    public FindCommand(Set<String> keywords, Set<String> tags) throws IllegalValueException {
        this.keywords = keywords;
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(new Tag(tagName));
        }
        this.tags = new UniqueTagList(tagSet);
    }

    /**
     * Returns copy of keywords in this command.
     */
    public Set<String> getKeywords() {
        return new HashSet<>(keywords);
    }

    @Override
    public CommandResult execute() {
        final List<ReadOnlyPerson> personsFound = getPersonsWithNameContainingAnyKeywordAndTag(keywords);
        final String personString = getMessageForPersonListShownSummary(personsFound);
        final String findMessage = tags.isEmpty() ? personString : personString + MESSAGE_TAGS_LABEL + tags.toString();
        return new CommandResult(findMessage, personsFound);
    }

    /**
     * Retrieve all persons in the address book whose names contain some of the specified keywords.
     *
     * @param keywords for searching
     * @return list of persons found
     */
    private List<ReadOnlyPerson> getPersonsWithNameContainingAnyKeywordAndTag(Set<String> keywords) {
        final List<ReadOnlyPerson> matchedPersons = new ArrayList<>();
        for (ReadOnlyPerson person : addressBook.getAllPersons()) {
            final Set<String> wordsInName = new HashSet<>(person.getName().getWordsInName());
            boolean isTagMatched = tags.toSet().isEmpty() || !Collections.disjoint(person.getTags().toSet(), tags.toSet());
            boolean isNameMatched = keyMatchName(keywords, wordsInName);
            if (isNameMatched && isTagMatched) {
                matchedPersons.add(person);
            }
        }
        return matchedPersons;
    }

    /**
     * Matches names ignoring case sensitivity
     * 
     * @param keywords for searching
     * @param wordsInName 
     * @return
     */
   private static boolean keyMatchName(Collection<String> keywords, Set<String> wordsInName) {
        for(String keyword: keywords){
            for(String word: wordsInName){
                if(keyword.equalsIgnoreCase(word)){
                    return true;
                }
            }
        }
        
        return false;
    }

}

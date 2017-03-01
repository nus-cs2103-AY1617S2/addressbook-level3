package seedu.addressbook.commands;

import seedu.addressbook.commands.exception.*;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.tag.Tag;

import java.util.*;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case sensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" + "Finds all persons whose names contain any of "
            + "the specified keywords (case-sensitive) and displays them as a list with index numbers.\n\t"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n\t"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

    private final Set<String> keywords;

    public FindCommand(Set<String> keywords) {
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
        final List<ReadOnlyPerson> matchedPersons = new ArrayList<>();
        Set<String> lowerCaseKeywords = new HashSet<String>();
        for(String k : keywords){
            lowerCaseKeywords.add(k.toLowerCase());
        }
        for (ReadOnlyPerson person : addressBook.getAllPersons()) {
            final Set<String> wordsToSearch = new HashSet<String>();
            for(String w : person.getName().getWordsInName()) {
                wordsToSearch.add(w.toLowerCase());
            }
            for(String w : person.getAddress().getWordsInAddress()) {
                wordsToSearch.add(w.toLowerCase());
            }
            for(Tag t : person.getTags().toSet()) {
                wordsToSearch.add(t.tagName.toLowerCase());
            }
            wordsToSearch.add(person.getPhone().toString());
            wordsToSearch.add(person.getEmail().toString());
            
            /* Exact Match */
            if (!Collections.disjoint(wordsToSearch, lowerCaseKeywords)) {
                matchedPersons.add(person);
                continue;
            }
            /* Partial Match
             * Near Match (Error Margin Allowed: 2)*/
            for(String k: lowerCaseKeywords) {
                for(String w : wordsToSearch) {
                    if(w.contains(k)) {
                        matchedPersons.add(person);
                        break;
                    }else if(computeLevenshteinDistance(w,k) <= 2) {
                        matchedPersons.add(person);
                        break;
                    }
                }
            }
            
        }
        return matchedPersons;
    }

    @Override
    public boolean isMutating() {
        return false;
    }

    @Override
    public boolean isUndoable() {
        return false;
    }

    @Override
    public String undo() throws UndoFailedException {
        throw new IllegalUndoOperationException(COMMAND_WORD);
    }

    private int computeLevenshteinDistance(CharSequence str1, CharSequence str2 ) {
        int[][] distance = new int[str1.length() + 1][str2.length() + 1];
    
        for (int i = 0; i <= str1.length(); i++)
           distance[i][0] = i;
        for (int j = 1; j <= str2.length(); j++)
           distance[0][j] = j;
    
        for (int i = 1; i <= str1.length(); i++)
           for (int j = 1; j <= str2.length(); j++)
              distance[i][j] =
                 minimum(
                    distance[i - 1][j] + 1,
                    distance[i][j - 1] + 1,
                    distance[i - 1][j - 1] +
                        ((str1.charAt(i - 1) == str2.charAt(j - 1)) ? 0 : 1));
    
        return distance[str1.length()][str2.length()];
    }
    private int minimum(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }
}

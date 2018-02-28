package seedu.addressbook.common;

import seedu.addressbook.data.group.Group;
import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.*;

/**
 * Utility methods
 */
public class Utils {

    /**
     * Checks whether any of the given items are null.
     */
    public static boolean isAnyNull(Object... items) {
        for (Object item : items) {
            if (item == null) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if every element in a collection are unique by {@link Object#equals(Object)}.
     */
    public static boolean elementsAreUnique(Collection<?> items) {
        final Set<Object> testSet = new HashSet<>();
        for (Object item : items) {
            final boolean itemAlreadyExists = !testSet.add(item); // see Set documentation
            if (itemAlreadyExists) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the list is sorted in alphabetical order
     * @param list
     * @return
     */

    public static boolean isAlphabeticalOrder(List<ReadOnlyPerson> list){
        for(int i=0;i<list.size() - 1;i++){
            if(list.get(i).getName().toString().compareTo(list.get(i+1).getName().toString())<0){
                return false;
            }
        }
        return true;
    }

    /**
     * Determine whether the name of the group has been added in
     * @param listOfGroups
     * @param name
     * @return
     */
    public static boolean groupIsAdded(ArrayList<Group> listOfGroups, String name){
        for(int i=0;i<listOfGroups.size();i++){
            if(listOfGroups.get(i).getNameGroup().equals(name)){
                return true;
            }
        }
        return false;
    }
}

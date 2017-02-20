package seedu.addressbook.common;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

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
     * Computes the Levenshtein's distance between two strings.
     *
     * @param string1 and string2
     * @return the Levenshtein's distance between string1 and string2
     */
    public static int computeLevenshteinDistance(String string1, String string2) {
        int[][] distance = new int[string1.length() + 1][string2.length() + 1];

        for (int i = 0; i <= string1.length(); i++) {
            distance[i][0] = i;
        }
        for (int j = 1; j <= string2.length(); j++) {
            distance[0][j] = j;
        }

        for (int i = 1; i <= string1.length(); i++) {
            for (int j = 1; j <= string2.length(); j++) {
                distance[i][j] = Math.min(
                        Math.min(distance[i - 1][j] + 1,
                                 distance[i][j - 1] + 1),
                        distance[i - 1][j - 1] + ((string1.charAt(i - 1) == string2.charAt(j - 1)) ? 0 : 1));
            }
        }

        return distance[string1.length()][string2.length()];
    }
}

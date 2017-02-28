package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;


/**
 * Represents a Person's race in the address book.
 * Guarantees: immutable;
 */
public class Race {
    public final String value;
    private boolean isPrivate;

    public static String EXAMPLE = "Chinese";
    public static String MESSAGE_RACE_CONSTRAINTS = "Race should be valid";

    /**
     * Validates given race
     *
     * @throws IllegalValueException if given race string is invalid.
     */
    public Race(String race, boolean isPrivate) throws IllegalValueException {
        this.isPrivate = isPrivate;
        String raceCorrectFormat = formatRaceString(race);
  
        this.value = raceCorrectFormat;
    }

    /**
     * Return race string in a correct format
     * @param raceString
     * @return race correct format
     */
    public String formatRaceString(String raceString) {
    	String raceStringLower = raceString.toLowerCase();
    	String raceStringLowerFirstLetter = raceStringLower.substring(0, 1).toUpperCase();
    	String raceStringCorrectFormat = raceStringLowerFirstLetter + raceStringLower.substring(1, raceStringLower.length());
    	return raceStringCorrectFormat;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Race // instanceof handles nulls
                && this.value.equals(((Race) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }


    public boolean isPrivate() {
        return isPrivate;
    }
}
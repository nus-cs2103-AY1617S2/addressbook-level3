package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;


/**
 * Represents a Person's nationality in the address book.
 * Guarantees: immutable;
 */
public class Nationality {
    public final String value;
    private boolean isPrivate;

    public static String EXAMPLE = "Singaporean";
    public static String MESSAGE_RACE_CONSTRAINTS = "Nationality should be valid";

    /**
     * Validates given nationality
     *
     * @throws IllegalValueException if given nationality string is invalid.
     */
    public Nationality(String nationality, boolean isPrivate) throws IllegalValueException {
        this.isPrivate = isPrivate;
        String nationalityCorrectFormat = formatNationalityString(nationality);
  
        this.value = nationalityCorrectFormat;
    }

    /**
     * Return nationality string in a correct format
     * @param nationalityString
     * @return nationality correct format
     */
    public String formatNationalityString(String nationalityString) {
        String nationalityStringLower = nationalityString.toLowerCase();
        String nationalityStringLowerFirstLetter = nationalityStringLower.substring(0, 1).toUpperCase();
        String nationalityStringCorrectFormat = nationalityStringLowerFirstLetter + nationalityStringLower.substring(1, nationalityStringLower.length());
        return nationalityStringCorrectFormat;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Nationality // instanceof handles nulls
                && this.value.equals(((Nationality) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }


    public boolean isPrivate() {
        return isPrivate;
    }
}
package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

/**
 * Represents a Person's religion in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidReligion(String)}
 */
public class Religion {

    public static final String EXAMPLE = "Buddhism";
    public static final String MESSAGE_RELIGION_CONSTRAINTS =
            "Person religion should be spaces or alphanumeric characters";
    public static final String RELIGION_VALIDATION_REGEX = "[\\p{Alnum} ]+";

    public final String value;
    private boolean isPrivate;

    /**
     * Validates given religion.
     *
     * @throws IllegalValueException if given religion address string is invalid.
     */
    public Religion(String religion, boolean isPrivate) throws IllegalValueException {
        this.isPrivate = isPrivate;
        String religionTrimmed = religion.trim();
        if (!isValidReligion(religionTrimmed)) {
            throw new IllegalValueException(MESSAGE_RELIGION_CONSTRAINTS);
        }
        this.value = religionTrimmed;
    }

    /**
     * Checks if a given string is a valid person religion.
     */
    public static boolean isValidReligion(String test) {
        return test.matches(RELIGION_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Religion // instanceof handles nulls
                && this.value.equals(((Religion) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }


    public boolean isPrivate() {
        return isPrivate;
    }
}

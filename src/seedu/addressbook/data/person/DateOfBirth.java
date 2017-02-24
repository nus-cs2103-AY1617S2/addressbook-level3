package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

/**
 * Represents a Person's DOB in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDob(String)}
 */
public class DateOfBirth {

    public static final String EXAMPLE = "DD/MM/YYYY";
    public static final String MESSAGE_DOB_CONSTRAINTS =
            "Date of Birth should only be numbers";
    public static final String DOB_VALIDATION_REGEX = "^(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)$";

    public final String value;
    private boolean isPrivate;

    /**
     * Validates given DOB.
     *
     * @throws IllegalValueException if given DOB string is invalid.
     */
    public DateOfBirth(String dob, boolean isPrivate) throws IllegalValueException {
        this.isPrivate = isPrivate;
        dob = dob.trim();
        if (!isValidDob(dob)) {
            throw new IllegalValueException(MESSAGE_DOB_CONSTRAINTS);
        }
        this.value = dob;
    }

    /**
     * Checks if a given string is a valid person email.
     */
    public static boolean isValidDob(String test) {
        return test.matches(DOB_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DateOfBirth // instanceof handles nulls
                && this.value.equals(((DateOfBirth) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }


    public boolean isPrivate() {
        return isPrivate;
    }
}
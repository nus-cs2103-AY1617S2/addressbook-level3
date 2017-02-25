package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

/**
 * Represents a Person's name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class Priority {

    public static final String EXAMPLE = "1";
    public static final String MESSAGE_NAME_CONSTRAINTS = "Priority level should be an integer between 1 and 3";
    public static final String TRIVIAL = "trivial";
    public static final String ORDINARY = "ordinary";
    public static final String IMPORTANT = "important";
    public static final int[] ACCEPTED_LEVEL =  new int[]{1, 2, 3};

    public int level;

    /**
     * Validates given name.
     *
     * @throws IllegalValueException if given name string is invalid.
     */
    public Priority(int level) throws IllegalValueException {
        if (!isValidPriority(level)) {
            throw new IllegalValueException(MESSAGE_NAME_CONSTRAINTS);
        }
        this.level = level;
    }

    /**
     * Returns true if a given string is a valid person name.
     */
    private boolean isValidPriority(int level) {
        return level == 1 || level == 2 || level == 3;
    }

    @Override
    public String toString() {
        switch (level){
        case 1: return TRIVIAL;
        case 2: return ORDINARY;
        case 3: return IMPORTANT;
        default: return null;
        }
    }

    public boolean equals(Priority other) {
        return this.level == other.level;
    }
}
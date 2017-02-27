package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

public class Priority {
    public static final String EXAMPLE = "High";
    public static final String MESSAGE_NAME_CONSTRAINTS = "Priority should be in alphabetic characters";
    public static final String NAME_VALIDATION_REGEX = "[\\p{Alpha} ]+";
    
    private String priority;
    
    public Priority(String prioritylevel) throws IllegalValueException {
        if(!isValidPriority(prioritylevel)) {
            throw new IllegalValueException(MESSAGE_NAME_CONSTRAINTS);
        }
        this.priority = prioritylevel;
    }
    
    /**
     * Returns true if a given string is a valid priority level.
     */
    public static boolean isValidPriority(String test) {
        return test.matches(NAME_VALIDATION_REGEX);
    }
    
    public String getPriority() {
        return priority;
    }
    
}

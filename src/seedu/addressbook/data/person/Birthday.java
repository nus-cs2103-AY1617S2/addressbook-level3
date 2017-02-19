package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;
import org.ocpsoft.prettytime.nlp.PrettyTimeParser;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Represents a Person's birthday in the address book.
 * Guarantees: immutable;
 */
public class Birthday {
    public final Date value;
    private boolean isPrivate;

    public static String EXAMPLE = "September 15th 1994";
    public static String MESSAGE_BIRTHDAY_CONSTRAINTS = "Birthday should be valid";

    /**
     * Validates given birthday
     *
     * @throws IllegalValueException if given birthday string is invalid.
     */
    public Birthday(String dateString, boolean isPrivate) throws IllegalValueException {
        this.isPrivate = isPrivate;
        Optional<Date> validDate = getValidDate(dateString);
        if (!validDate.isPresent()) {
            throw new IllegalValueException(MESSAGE_BIRTHDAY_CONSTRAINTS);
        }
        this.value = validDate.get();
    }

    /**
     * Return optional date for a particular string
     * @param dateString
     * @return optional containing date
     */
    public Optional<Date> getValidDate(String dateString) {
        List<Date> dates = new PrettyTimeParser().parse(dateString);

        if (dates.size() == 0) {
            return null;
        } else {
            return Optional.of(dates.get(0));
        }
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Birthday // instanceof handles nulls
                && this.value.equals(((Birthday) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }


    public boolean isPrivate() {
        return isPrivate;
    }
}
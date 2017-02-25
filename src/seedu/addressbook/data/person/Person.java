package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.tag.UniqueTagList;

import java.util.Objects;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated.
 */
public class Person implements ReadOnlyPerson {

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Priority priority;

    private final UniqueTagList tags;
    /**
     * Assumption: Every field must be present and not null.
     * @throws IllegalValueException 
     */
    public Person(Name name, Phone phone, Email email, Address address, UniqueTagList tags, Priority... priorities) throws IllegalValueException {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags = new UniqueTagList(tags); // protect internal tags from changes in the arg list
        this.priority = priorities.length == 0? new Priority(2): priorities[0];
    }

    /**
     * Copy constructor.
     * @throws IllegalValueException 
     */
    public Person(ReadOnlyPerson source) throws IllegalValueException {
        this(source.getName(), source.getPhone(), source.getEmail(), source.getAddress(), source.getTags(), source.getPriority());
    }

    @Override
    public Name getName() {
        return name;
    }

    @Override
    public Phone getPhone() {
        return phone;
    }

    @Override
    public Email getEmail() {
        return email;
    }

    @Override
    public Address getAddress() {
        return address;
    }
    
    @Override
    public Priority getPriority(){
        return priority;
    }

    public void setEmail(Email email) {
    	this.email = email;
    }

    public void setPhone(Phone phone) {
    	this.phone = phone;
    }

    @Override
    public UniqueTagList getTags() {
        return new UniqueTagList(tags);
    }

    /**
     * Replaces this person's tags with the tags in the argument tag list.
     */
    public void setTags(UniqueTagList replacement) {
        tags.setTags(replacement);
    }

    public void setPriority(int newPriorityLevel) throws IllegalValueException{
        priority = new Priority(newPriorityLevel);
    }
    
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ReadOnlyPerson // instanceof handles nulls
                && this.isSameStateAs((ReadOnlyPerson) other));
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags, priority);
    }

    @Override
    public String toString() {
        return getAsTextShowAll();
    }

}
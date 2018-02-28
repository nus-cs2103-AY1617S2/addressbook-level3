package seedu.addressbook.data.group;

/**
 * Represents a Group Name in the address book
 */
public class Name {
    private final String groupName;

    public Name(String name){
        this.groupName = name.trim();
    }

    public String getGroupName(){
        return groupName;
    }
}

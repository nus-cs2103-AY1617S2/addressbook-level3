package seedu.addressbook.data.group;

import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.UniquePersonList;

import java.util.List;

public class Group {
    private Name nameGroup;
    private UniquePersonList groupPersons;

    public Group(String name) {
        this.nameGroup = new Name(name);
        groupPersons = new UniquePersonList();
    }

    public void add(Person person) throws UniquePersonList.DuplicatePersonException {
        groupPersons.add(person);
    }

    public Name getNameGroup(){
        return nameGroup;
    }
}

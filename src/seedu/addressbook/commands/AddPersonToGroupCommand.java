package seedu.addressbook.commands;

import seedu.addressbook.data.group.Group;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.UniquePersonList;

import java.util.ArrayList;

public class AddPersonToGroupCommand extends Command{
    private ArrayList<Group> LIST_OF_GROUPS = addressBook.getLIST_OF_GROUPS();
    private Group toAddTo;
    public AddPersonToGroupCommand(String name,Person person) throws UniquePersonList.DuplicatePersonException {
        for(int i=0;i<LIST_OF_GROUPS.size(); i++){
            if(LIST_OF_GROUPS.get(i).getNameGroup().getGroupName().equals(name)){
                toAddTo = LIST_OF_GROUPS.get(i);
                if(addressBook.containsPerson(person.getName().toString())) {
                    toAddTo.add(person);
                }
            }
        }
    }
}

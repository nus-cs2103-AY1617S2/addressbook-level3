# Developer Guide

* [Setting Up](#setting-up)
* [Design](#design)
* [Testing](#testing)
* [Appendix A: User Stories](#appendix-a--user-stories)
* [Appendix B: Use Cases](#appendix-b--use-cases)
* [Appendix C: Non Functional Requirements](#appendix-c--non-functional-requirements)
* [Appendix D: Gloassary](#appendix-d--glossary)

## Setting up

#### Prerequisites

1. **JDK 8** or later
2. **Eclipse** IDE
3. **e(fx)clipse** plugin for Eclipse (Do the steps 2 onwards given in
   [this page](http://www.eclipse.org/efxclipse/install.html#for-the-ambitious))


#### Importing the project into Eclipse

0. Fork this repo, and clone the fork to your computer
1. Open Eclipse (Note: Ensure you have installed the **e(fx)clipse plugin** as given in the prerequisites above)
2. Click `File` > `Import`
3. Click `General` > `Existing Projects into Workspace` > `Next`
4. Click `Browse`, then locate the project's directory
5. Click `Finish`

## Design
<img src="images/mainClassDiagram.png"/>

## Testing

* In Eclipse, right-click on the `test/java` folder and choose `Run as` > `JUnit Test`

## Appendix A : User Stories

Priorities: High (must have) - `* * *`, Medium (nice to have)  - `* *`,  Low (unlikely to have) - `*`


Priority | As a ... | I want to ... | So that I can...
-------- | :-------- | :--------- | :-----------
`* * *` | new user | see usage instructions | refer to instructions when I forget how to use the App
`* * *` | user | add a new person |
`* * *` | user | delete a person | remove entries that I no longer need
`* * *` | user | find a person by name | locate details of persons without having to go through the entire list
`* * *` | user | find a person by email| locate details of persons without having to go through the entire list
`* * *` | user | find a person by phone | locate details of persons without having to go through the entire list
`* * *` | user | find a person by address | locate details of persons without having to go through the entire list
`* *` | user with many persons in the address book| group the persons by tags | list a group of persons from a certain group easily, e.g. colleagues
`* *` | user | hide [private contact details](#private-contact-detail) by default | minimize chance of someone else seeing them by accident
`*` | user with many persons in the address book | sort persons by name | locate a person easily
`*` | user | modify the information about a particular user | do not need to delete and add the person every time there is a change in the contact information

## Appendix B : Use Cases

(For all use cases below, the **System** is the `AddressBook` and the **Actor** is the `user`, unless specified otherwise)

#### Use case: Delete person

**MSS**

1. User requests to list persons
2. AddressBook shows a list of persons
3. User requests to delete a specific person in the list
4. AddressBook deletes the person <br>
Use case ends.

**Extensions**

2a. The list is empty

> Use case ends

3a. The given index is invalid

> 3a1. AddressBook shows an error message <br>
  Use case resumes at step 2

#### Use case: Rename tag

**MSS**

1. User requests to list tags
2. AddressBook shows a list of tags
3. User chooses a particular tag and enters
4. AddressBook asks user whether to show the persons of the tag or rename the tag (or any other possible action to be added in in the future)
5. User requests to rename the tag
6. AddressBook confirms with user 
7. User types yes/enter
8. AddressBook gets ready to read input
9. User types in new name of the tag and enters
10. AddressBook confirms with user
11. User types yes/enter
12. AddressBook update the tag with the new name
Use case ends.

**Extenions**

3a. User types in incorrect tag (non-existing)
>3a1. AddressBook shows an error message <br>
 Use case resumes at step 2

5a.User types in incorrect command
>5a1. AddressBook shows an error message <br>
 Use case resumes at step 4

9a.User types in an invalid tag name
>9a1. AddressBook shows an error message <br>
 Use case resumes at step 8

## Appendix C : Non Functional Requirements

1. Should work on any [mainstream OS](#mainstream-os) as long as it has Java 8 or higher installed.
2. Should be able to hold up to 1000 persons.
3. Should come with automated unit tests and open source code.
4. Should favor DOS style commands over Unix-style commands.
5. Should not crash more than 1 per ten thousand correctly executed actions. 
6. Should allow user to upgrade easily if and when an upgrade is available.
7. Should contain a login mechanism for security purposes.
8. Should contain a hierachy of authorization levels for different actions.

## Appendix D : Glossary

##### Mainstream OS

> Windows, Linux, Unix, OS-X

##### Private contact detail

> A contact detail that is not meant to be shared with others

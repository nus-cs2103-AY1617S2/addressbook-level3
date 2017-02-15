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
`* * *` | user | edit a person's details | have a updated address book
`* * *` | user | find a person by name | locate details of persons without having to go through the entire list
`* *` | user | sort address book by tag ascending or descending | search people of similar group (e.g. same secondary school, same CCA)
`* *` | user | undo command executed previously | amend the mistakes made in the previous command
`* *` | user | see the history of my address book | see what are the changes and command made previously 
`* *` | user | assign shortcut to execute commands | type lesser characters and execute my commands correctly
`* *` | user | hide [private contact details](#private-contact-detail) by default | minimize chance of someone else seeing them by accident
`*` | user with many persons in the address book | sort persons by name | locate a person easily
`*` | user | check a number | find out whose phone number does it belongs to


## Appendix B : Use Cases

(For all use cases below, the **System** is the `AddressBook` and the **Actor** is the `user`, unless specified otherwise)

#### Use case: Rename tag

**MSS**

1. User requests to edit tag
2. AddressBook shows a list of tags
3. User requests to rename a specific tag in the list
4. AddressBook prompts the user to type in the new tag
5. AddressBook prompts the user to confirm the changes
6. User reply to the prompt with a "yes" which confirms the changes
7. AddessBook rename the tag and update the contacts in AddressBook with the new tag
8. AddressBook shows the updated list of tags
Use case ends.

**Extensions**

2a. The list is empty

> Use case ends

3a. The given tag is invalid

> 3a1. AddressBook shows an error message
  Use case resumes at step 2

4a. The new input tag is invalid

> 4a1. AddressBook shows an error message
  Use case resumes at step 4

6a. The user's reply is invalid

> 6a1. AddressBook shows an error message
  Use case resumes at step 6

6b. The user's reply is "no"

> Use case ends

8a. The update has failed

> 8a1. AddressBook shows an error message
  Use case ends

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

## Appendix C : Non Functional Requirements

1. Should work on any [mainstream OS](#mainstream-os) as long as it has Java 8 or higher installed.
2. Should be able to hold up to 1000 persons.
3. Should come with automated unit tests and open source code.
4. Should favor DOS style commands over Unix-style commands.
5. Should be easy for user to use
6. Should be able to execute commands quickly
7. Should have some form of backup
8. Should allow user to exit the program with no data loss
9. Should have basic security features in place
10. Should be able to document the unhandled errors
11. Should come with the user guide and other documentations
12. Should have simple user interface

## Appendix D : Glossary

##### Mainstream OS

> Windows, Linux, Unix, OS-X

##### Private contact detail

> A contact detail that is not meant to be shared with others

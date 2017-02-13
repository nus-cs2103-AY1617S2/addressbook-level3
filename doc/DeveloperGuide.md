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
`* *` | user | hide [private contact details](#private-contact-detail) by default | minimize chance of someone else seeing them by accident
`* *` | user | add tags to people | categorize contacts according to relationships
`* *` | user | find a person by phone number | look up for who the phone number belongs to
`*` | user with many persons in the address book | sort persons by name | locate a person easily


## Appendix B : Use Cases

(For all use cases below, the **System** is the `AddressBook` and the **Actor** is the `user`, unless specified otherwise)

#### Use case: Add person

**MSS**

1. User requests to add a new person
2. AddressBook shows an input window asking for details, where name and contact number is compulsory, and address, email address, and tags are optional.
3. User type in the details and requests to save the person
4. AddressBook adds the contact to the list. <br>
Use case ends.

**Extensions**

2a. The list is empty

> Use case ends

3a. Some of the compulsory details are not given

> 3a1. AddressBook shows an error message <br> 
  Use case resumes at step 2

3b. The given name is invalid

> 3b1. AddressBook show an error message <br>
  Use case resumes at step 2

3c. The given phone number is invalid

> 3c1. AddressBook show an error message <br>
  Use case resumes at step 2
 
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
3. User requests to rename a tag
4. AddressBook opens up an input window for user to input the new name
5. User input the new name
6. AddressBook updates the tag's name.
Use case ends.

**Extensions**

2a. The list is emtpy

> Use case ends

3a. The given tag name is invalid

> 3a1. AddressBook show an error message <br>
  Use case resumes at step 4


## Appendix C : Non Functional Requirements

1. Should work on any [mainstream OS](#mainstream-os) as long as it has Java 8 or higher installed.
2. Should be able to hold up to 1000 persons.
3. Should come with automated unit tests and open source code.
4. Should favor DOS style commands over Unix-style commands.
5. Should be able to respond to each command within 3 seconds.
6. Should be easy to be understood for technology unsavvy people
7. Should include a comprehensive FAQ database for new users to look up when they face problems
8. Should include a tutorial animation for first-time user to run through the frequently used functions of AddressBook

## Appendix D : Glossary

##### Mainstream OS

> Windows, Linux, Unix, OS-X

##### Private contact detail

> A contact detail that is not meant to be shared with others

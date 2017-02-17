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
`* * *` | user | modify details of a person | save the hassle of having to delete and add the same persons
`* * *` | user | find a person by name | locate details of persons without having to go through the entire list
`* *` | user | hide [private contact details](#private-contact-detail) by default | minimize chance of someone else seeing them by accident
`* *` | user | add additional notes for a person | take note of other important details of persons
`*` | user with many persons in the address book | sort persons by name | locate a person easily
`*` | user with many persons in the address book | group persons by tag | locate a person easily based on tag


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
3. User requests to rename a specific tag in the list
4. AddressBook requests for new tag name
5. User types in new tag name
6. AddressBook requests for confirmation on change to be made
7. User confirms the change to be made
8. AddressBook renames the tag <br>
Use case ends.

**Extensions**

2a. The list is empty

> 2a1. AddressBook informs user that list is empty <br>
  Use case ends

3a. The given index is invalid

> 3a1. AddressBook informs user that index is invalid <br>
  Use case resumes at step 2
  
5a. The new tag name is already used

> 5a1. AddressBook informs user that new tag name is already used <br>
  Use case resumes at step 4

7a. The user retracts the request

> Use case ends

## Appendix C : Non Functional Requirements

1. Should work on any [mainstream OS](#mainstream-os) as long as it has Java 8 or higher installed.
2. Should be able to hold up to 1000 persons.
3. Should be able to hold up to 500 tags. 
4. Should come with automated unit tests and open source code.
5. Should favor DOS style commands over Unix-style commands.
6. Should conform with the Java coding standard. 
6. Should back up data once a week. 

## Appendix D : Glossary

##### Mainstream OS

> Windows, Linux, Unix, OS-X

##### Private contact detail

> A contact detail that is not meant to be shared with others

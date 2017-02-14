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
`* * *` | user | edit an existing person's contact details | update out-dated or incorrect information
`* * *` | user | delete a person | remove entries that I no longer need
`* * *` | user | find a person by name | locate details of persons without having to go through the entire list
`* *` | user | hide [private contact details](#private-contact-detail) by default | minimize chance of someone else seeing them by accident
`* *` | user | find a person by tags | categorize contacts into meaningful groups for easy location especially if I cannot remember the person's name 
`*` | user with many persons in the address book | sort persons by name | locate a person easily
`*` | user with many persons in the address book | mark a person as 'Favorites' | have quick access to a list of people that I contact regularly
`*` | user | add a person's birthday as part of the contact details | remember their birthdays 



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

1. User requests to list all tags in the Address Book. 
2. Address Book shows a list of all tags.
3. User requests to rename a specific tag and provides the new tag name.
4. Address Book displays a list of persons with that particular tag.
5. Address Book requests for confirmation.
6. User confirms rename operation.
7. Address Book renames tag.
Use case ends.

**Extensions**

2a. The list is empty
> Use case ends. 
    
2b. User requests to find a list of persons with a particular tag.
> 2b1. Address Book shows persons with the tag.
  Use case resumes from step 3.
    
3a. The tag specified by the User does not exist. 
> 3a1.Address Book shows an error message.
  Use case ends.
  
6a. User chooses to cancel the operation.
> 6a1. Address Book requests to confirm the cancellation.
  6a2. User confirms the cancellation.
  Use case ends.  
     
## Appendix C : Non Functional Requirements

1. Should work on any [mainstream OS](#mainstream-os) as long as it has Java 8 or higher installed.
2. Should be able to hold up to 1000 persons.
3. Should come with automated unit tests and open source code.
4. Should favor DOS style commands over Unix-style commands.
5. Should have proper documentation of user guide and glossary of terms.
6. Should respond to user commands relatively quickly (not more than 10 seconds).
7. Should be intuitive and easy to use even for users without any technical background.
8. Should have backup storage of contacts.
9. Should have customizable user interface that allows user to change font size and color.
10. Should be able to sync with phone to import, export and update existing contacts.  

## Appendix D : Glossary

##### Mainstream OS

> Windows, Linux, Unix, OS-X

##### Private contact detail

> A contact detail that is not meant to be shared with others

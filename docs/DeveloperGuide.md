---
  layout: default.md
  title: "Developer Guide"
  pageNav: 3
---

# ResiConnect Developer Guide

--------------------------------------------------------------------------------------------------------------------

## Table of Contents
1. [Acknowledgements](#acknowledgements)


2. [Setting up, getting started](#setting-up-getting-started)


3. [Design](#design)
   1. [Architecture](#architecture)
   2. [UI component](#ui-component)
   3. [Logic component](#ui-component)
   4. [Model component](#model-component)
   5. [Storage component](#storage-component)
   6. [Common classes](#common-classes)


4. [Implementation](#implementation)
    1. [[Proposed] Undo/redo feature](#proposed-undo-redo-feature)


5. [Documentation, logging, testing, configuration, dev-ops](#documentation-logging-testing-configuration-dev-ops)


6. [Appendix: Requirements](#appendix-requirements)
   1. [Product scope](#product-scope)
   2. [User stories](#user-stories)
   3. [Use cases](#use-cases)
   4. [Non-Functional Requirements](#non-functional-requirements)
   5. [Glossary](#glossary)


7. [Appendix: Instructions for manual testing](#appendix-instructions-for-manual-testing)
   1. [Launch and shutdown](#launch-and-shutdown)
   2. [Adding a student](#adding-a-student)
   3. [Listing all students](#listing-all-students)
   4. [Deleting a student](#deleting-a-student)
   5. [Searching for students](#searching-for-students)


8. [Appendix: Planned Enhancements](#appendix-planned-enhancements)
   1. [Edit Functionality](#edit-functionality)
   2. [Partial Searching](#partial-searching)
   3. [Maximum Number of residents in a room](#maximum-number-of-residents-in-a-room)



--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

This project is based on the AddressBook-Level3 project created by the [SE-EDU initiative](https://se-education.org)

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

### Architecture

<puml src="diagrams/ArchitectureDiagram.puml" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** (consisting of classes [`Main`](https://github.com/AY2425S2-CS2103T-T11-3/tp/blob/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/AY2425S2-CS2103T-T11-3/tp/blob/master/src/main/java/seedu/address/MainApp.java)) is in charge of the app launch and shut down.
* At app launch, it initializes the other components in the correct sequence, and connects them up with each other.
* At shut down, it shuts down the other components and invokes cleanup methods where necessary.

The bulk of the app's work is done by the following four components:

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete_stu 1`.

<puml src="diagrams/ArchitectureSequenceDiagram.puml" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<puml src="diagrams/ComponentManagers.puml" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/AY2425S2-CS2103T-T11-3/tp/blob/master/src/main/java/seedu/address/ui/Ui.java)

<puml src="diagrams/UiClassDiagram.puml" alt="Structure of the UI Component"/>

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `StudentListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Student` object residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/AY2425S2-CS2103T-T11-3/tp/blob/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<puml src="diagrams/LogicClassDiagram.puml" width="550"/>

The sequence diagram below illustrates the interactions within the `Logic` component, taking `execute("delete_stu 1")` API call as an example.

<puml src="diagrams/DeleteStudentSequenceDiagram.puml" alt="Interactions Inside the Logic Component for the `delete_stu 1` Command" />

<box type="info" seamless>

**Note:** The lifeline for `DeleteStudentCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline continues till the end of diagram.
</box>

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it is passed to an `AddressBookParser` object which in turn creates a parser that matches the command (e.g., `DeleteStudentCommandParser`) and uses it to parse the command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `DeleteStudentCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to delete a student).<br>
   Note that although this is shown as a single step in the diagram above (for simplicity), in the code it can take several interactions (between the command object and the `Model`) to achieve.
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<puml src="diagrams/ParserClasses.puml" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `AddressBookParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddStudentCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddStudentCommand`) which the `AddressBookParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddStudentCommandParser`, `DeleteStudentCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/AY2425S2-CS2103T-T11-3/tp/blob/master/src/main/java/seedu/address/model/Model.java)

<puml src="diagrams/UpdatedModelClassDiagram.puml" width="450" />


The `Model` component,

* stores the address book data i.e., all `Staff`, `Student`, `ExternalParty` and `Event` objects (which are contained in respective `UniqueStaffList`, `UniqueStudentList`, `UniqueExternalPartyList` and `UniqueEventList` objects).
* stores the currently 'selected' `Staff`, `Student`, `ExternalParty` or `Event` objects (e.g., results of a search query) as separate _filtered_ lists which is exposed to outsiders as unmodifiable `ObservableList<Staff>`, `ObservableList<Student>`, `ObservableList<ExternalParty>`, or `ObservableList<Event>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<box type="info" seamless>

**Note:** A more in-depth model of the `Person` class, and its subclasses `Staff`, `Student`, and `ExternalParty`, is given below.

<puml src="diagrams/PersonDiagram.puml" width="450" />

</box>


### Storage component

**API** : [`Storage.java`](https://github.com/AY2425S2-CS2103T-T11-3/tp/blob/master/src/main/java/seedu/address/storage/Storage.java)

<puml src="diagrams/StorageClassDiagram.puml" width="550" />

The `Storage` component,
* can save both address book data and user preference data in JSON format, and read them back into corresponding objects.
* inherits from both `AddressBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.address.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### \[Proposed\] Undo/redo feature

#### Proposed Implementation

The proposed undo/redo mechanism is facilitated by `VersionedAddressBook`. It extends `AddressBook` with an undo/redo history, stored internally as an `addressBookStateList` and `currentStatePointer`. Additionally, it implements the following operations:

* `VersionedAddressBook#commit()` — Saves the current address book state in its history.
* `VersionedAddressBook#undo()` — Restores the previous address book state from its history.
* `VersionedAddressBook#redo()` — Restores a previously undone address book state from its history.

These operations are exposed in the `Model` interface as `Model#commitAddressBook()`, `Model#undoAddressBook()` and `Model#redoAddressBook()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedAddressBook` will be initialized with the initial address book state, and the `currentStatePointer` pointing to that single address book state.

<puml src="diagrams/UndoRedoState0.puml" alt="UndoRedoState0" />

Step 2. The user executes `delete 5` command to delete the 5th person in the address book. The `delete` command calls `Model#commitAddressBook()`, causing the modified state of the address book after the `delete 5` command executes to be saved in the `addressBookStateList`, and the `currentStatePointer` is shifted to the newly inserted address book state.

<puml src="diagrams/UndoRedoState1.puml" alt="UndoRedoState1" />

Step 3. The user executes `add n/David …​` to add a new person. The `add` command also calls `Model#commitAddressBook()`, causing another modified address book state to be saved into the `addressBookStateList`.

<puml src="diagrams/UndoRedoState2.puml" alt="UndoRedoState2" />

<box type="info" seamless>

**Note:** If a command fails its execution, it will not call `Model#commitAddressBook()`, so the address book state will not be saved into the `addressBookStateList`.

</box>

Step 4. The user now decides that adding the person was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undoAddressBook()`, which will shift the `currentStatePointer` once to the left, pointing it to the previous address book state, and restores the address book to that state.

<puml src="diagrams/UndoRedoState3.puml" alt="UndoRedoState3" />


<box type="info" seamless>

**Note:** If the `currentStatePointer` is at index 0, pointing to the initial AddressBook state, then there are no previous AddressBook states to restore. The `undo` command uses `Model#canUndoAddressBook()` to check if this is the case. If so, it will return an error to the user rather
than attempting to perform the undo.

</box>

The following sequence diagram shows how an undo operation goes through the `Logic` component:

<puml src="diagrams/UndoSequenceDiagram-Logic.puml" alt="UndoSequenceDiagram-Logic" />

<box type="info" seamless>

**Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</box>

Similarly, how an undo operation goes through the `Model` component is shown below:

<puml src="diagrams/UndoSequenceDiagram-Model.puml" alt="UndoSequenceDiagram-Model" />

The `redo` command does the opposite — it calls `Model#redoAddressBook()`, which shifts the `currentStatePointer` once to the right, pointing to the previously undone state, and restores the address book to that state.

<box type="info" seamless>

**Note:** If the `currentStatePointer` is at index `addressBookStateList.size() - 1`, pointing to the latest address book state, then there are no undone AddressBook states to restore. The `redo` command uses `Model#canRedoAddressBook()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the redo.

</box>

Step 5. The user then decides to execute the command `list`. Commands that do not modify the address book, such as `list`, will usually not call `Model#commitAddressBook()`, `Model#undoAddressBook()` or `Model#redoAddressBook()`. Thus, the `addressBookStateList` remains unchanged.

<puml src="diagrams/UndoRedoState4.puml" alt="UndoRedoState4" />

Step 6. The user executes `clear`, which calls `Model#commitAddressBook()`. Since the `currentStatePointer` is not pointing at the end of the `addressBookStateList`, all address book states after the `currentStatePointer` will be purged. Reason: It no longer makes sense to redo the `add n/David …​` command. This is the behavior that most modern desktop applications follow.

<puml src="diagrams/UndoRedoState5.puml" alt="UndoRedoState5" />

The following activity diagram summarizes what happens when a user executes a new command:

<puml src="diagrams/CommitActivityDiagram.puml" width="250" />

#### Design considerations:

**Aspect: How undo & redo executes:**

* **Alternative 1 (current choice):** Saves the entire address book.
  * Pros: Easy to implement.
  * Cons: May have performance issues in terms of memory usage.

* **Alternative 2:** Individual command knows how to undo/redo by
  itself.
  * Pros: Will use less memory (e.g. for `delete_stu`, just save the student being deleted).
  * Cons: We must ensure that the implementation of each individual command are correct.


--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile**:

* is a Resident Assistant (RA) or a Resident Fellow (RF) in a residence in NUS
* has a need to manage a significant number of contacts of the residents(students), staff, and external vendors in a university hall
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**: ResiConnect helps a RA/RF manage contacts of the students of a hall or contacts of the students, staff and external vendors of hall events.
Having an address book to note down all the important information about the people they are in charge of. It would be helpful for coordinating hall events, emergency situation, etc.


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`


| Priority | As a …​ | I want to …​                                                   | So that I can…​                                                                               |
|----------|---------|----------------------------------------------------------------|-----------------------------------------------------------------------------------------------|
| `* * *`  | RA      | add a new resident contact to my address book                  | keep track of residents under my supervision                                                  |
| `* * *`  | RA/RF   | add new staff's/ external vendor's contact                     | keep track of staff or external vendor contact for event plannning or regular hall operations |
| `* * *`  | RA      | delete a person contact                                        | remove people who no longer belong to my hall or no longer needed                             |
| `* * *`  | RA      | view contacts                                                  | check the details of my contacts                                                              |
| `* *`    | RA      | update a person's particulars                                  | keep their information accurate when changes occur                                            |
| `* *`    | RA      | search for a resident by name, room number, or contact details | quickly find the person that I need                                                           |
| `* *`    | RA      | sort persons alphabetically or by hall block                   | organize my list efficiently                                                                  |
| `* * *`  | RF      | create an event                                                | keep track of event details                                                                   |
| `* * *`  | RF      | delete an event                                                | remove event that no longer needed                                                            |
| `* * *`  | RF      | view all the events                                            | check the details of all the events                                                           |
| `* * *`  | RF      | update details of an event                                     | ensure all information remains accurate and up to date                                        |
| `* * *`  | RF      | add people into an event                                       | keep track of who is involved in the event                                                    |
| `* * *`  | RF      | remove people in an event                                      | remove people who are not involved in the event                                               |
| `* * *`  | RF      | view all the people in an event                                | check the people that is involved in the event                                                |
| `* *`    | RF      | search for an event by name or time range                      | quickly find the event that I want                                                            |




### Use cases

(For all use cases below, the **System** is the `ResiConnect` and the **Actor** is the `user`, unless specified otherwise)

**UC01: Add a student/staff/external vendor**

**MSS**

1. User requests to add a student/staff/external vendor, along with his details.
2. ResiConnect saves the student/staff/external vendor and his details.
3. User requests to list student/staff/external vendor.
4. ResiConnect shows a list of student/staff/external vendor that includes the latest student added.

    Use case ends.

**Extensions**

* 1a. The compulsory fields given by the user was invalid.
    * 1a1. ResiConnect shows an error message.
    * 1a2. User enters updated details.
    Step 1a1-1a2 is repeated until the data entered is valid. Use case resumes from Step 2.

* 2a. The student/staff/external vendor already exists.
  Use case ends.

**UC02: Delete a student/staff/external vendor**

**MSS**

1.  User requests to list student/staff/external vendor.
2.  ResiConnect shows a list of student/staff/external vendor.
3.  User requests to delete a specific person in the student/staff/external vendor.
4.  ResiConnect deletes the person.

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.
    * 3a1. ResiConnect shows an error message.
    Step 3a1 is repeated until the data is valid. Use case repeat resumes from Step 2.

      Use case resumes at step 2.


**UC03: Find a student/staff/external vendor**

**MSS**

1. User requests to find if a student/staff/external vendor exists.
2. ResiConnect shows a list of students/staff/external vendors that match the search criteria.

  Use case ends.

**Extensions**

* 1a. The fields entered by the user was invalid
  * 1a1. ResiConnect shows an error message.

* 2a. The list is empty.

  Use case ends.

**UC04: Add an event**

**MSS**

1.  User requests to create a new event.
2.  ResiConnect saves the event details.
3.  User requests to add participants (student/staff/external party) to the event.
4.  ResiConnect saves the event details.

    Use case ends.

**Extensions**

* 1a. The compulsory fields given by the user was invalid.
    * 1a1. ResiConnect shows an error message.
    * 1a2. User enters updated details.
      Step 1a1-1a2 is repeated until the data entered is valid. Use case resumes from Step 2.

* 3a. The data format (student index/staff index/external party index) is incorrect.

    * 3a1. ResiConnect shows an error message.
    * 3a2. User enters updated details.

      Step 3a1-3a2 is repeated until the date entered is valid.

      Use case resumes from step 3.

**UC05: Delete an event**

**MSS**

1.  User requests to list all events.
2.  ResiConnect shows a list of events.
3.  RF request to delete an event.
4.  ResiConnect deletes the event and updates the list.

    Use case ends.

**Extensions**

* 2a. The list of events is empty.

  Use case ends.

* 3a. The selected event name is invalid.

    * 3a1. ResiConnect shows an error message.
    * 3a2. User enters updated valid event name.

      Step 3a1-3a2 is repeated until the date entered is valid.

      Use case resumes from step 4.

### Non-Functional Requirements

#### Data Requirements

1. Data Size: The system should handle up to `10,000` contacts and `500` events simultaneously without performance degradation.
2. Data Volatility: The system should allow for quick updates since contact information can change frequently.

#### Environment Requirements

1. Technical Environment: The system should be compatible with any mainstream OS with `Java 17 JDK+FX Azul distribution` installed.

#### Performance Requirements

1. Response Time: The system should respond to user inputs and complete the corresponding action within `2` seconds.

#### CLI-First Design

1. Primary Input Method: The system should prioritize `CLI` for all core functionalities.
2. One-Shot Commands: The system should support one-shot commands for users to perform tasks quickly.

#### Maintainability

1. Code Quality: The codebase should adhere to `Java coding standards` and include comprehensive documentation.
2. Modularity: The system should be modular, allowing for easy updates or additions of new features.

#### Testability

1. Unit Testing: At least `80%` code coverage should be achieved for all critical modules.
2. Integration Testing: End-to-end testing should be conducted to ensure seamless interaction between modules.

#### Quality Requirements

1. Usability: The system should be intuitive enough for new RAs and RFs to navigate without extensive training.
2. Error Handling: Clear error messages should be displayed to guide users in resolving issues.

### Glossary

* **RA**: Resident Assistants
* **RF**: Resident Fellows
* **Resident**: Members residing in the residence
* **Mainstream OS**: Windows, Linux, Unix, MacOS
* **Private contact detail**: A contact detail that is not meant to be shared with others


--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<box type="info" seamless>

**Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</box>

### Launch and shutdown

1. Initial launch

    1. Download the jar file and copy into an empty folder

    1. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

1. Saving window preferences

    1. Resize the window to an optimum size. Move the window to a different location. Close the window.

    1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

### Adding a student

1. Adding a student

    1. Prerequisites: None

    2. Test case: `add_stu name/John Doe matric/A0234567B phone/98765432 email/johnd@example.com a/311, Clementi Ave 2, #02-25 t/friend emergency/91234567 block/A level/5 room/3 designation/1`<br>
       Expected: Student will be added to the student list. Name, phone and email of the student will be shown in the status message. Timestamp in the status bar is updated.

    3. Test case: `add_stu name/Mary Jane matric/A0234568B phone/98765431 email/mary@example.com a/312, Clementi Ave 2, #02-25 t/friend emergency/91234567 block/A level/5 room/3 designation/1`<br>
       Expected: Student will be added to the student list. Name, phone and email of the student will be shown in the status message. Timestamp in the status bar is updated.

    4. Test case: `add_stu name/Harry Potter matric/A0234561B phone/98765412 email/harry@example.com a/313, Clementi Ave 2, #02-25 t/friend emergency/91234567 block/A level/5 room/3 designation/1`<br>
       Expected: Student will be added to the student list. Name, phone and email of the student will be shown in the status message. Timestamp in the status bar is updated.

    5. Test case: `add_stu name/John matric/A0234567B phone/99999999 email/john@example.com a/312, Clementi Ave 2, #02-25 t/friends emergency/91234568 block/B level/6 room/4 designation/2`
       Expected: No student is added. Error details shown in the status message. Status bar remains the same.

    6. Other incorrect add student commands to try: `add_stu`, `add_stu name/John Doe matric/A0234567B phone/98765432 email/johnd@example.com`<br>
       Expected: Similar to previous.

### Listing all students

1. Listing all students

    1. Prerequisites: None

    2. Test case: `list_stu`
       Expected: Student list will be shown. Message to inform user that students has been listed will be shown in the status message. Timestamp in the status bar is updated.

    3. Test case: `list_student`
       Expected: Student list will not be shown. Message to inform user that input is an unknown command will be shown in the status message. Timestamp in the status bar is updated.

### Deleting a student

1. Deleting a student while all students are being shown

    1. Prerequisites: List all students using the `list` command. Multiple students in the list.

    1. Test case: `delete_stu 1`<br>
       Expected: First contact is deleted from the student list. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.

    1. Test case: `delete_stu 0`<br>
       Expected: No student is deleted. Error details shown in the status message. Status bar remains the same.

    1. Other incorrect delete commands to try: `delete_stu`, `delete_stu x`, `...` (where x is larger than the list size)<br>
       Expected: Similar to previous.

### Searching for students

1. Searching for students

    1. Prerequisites: There must be students in the list

    2. Test case: `search_stu name/harry potter`<br>
       Expected: Students with names that match the search will show up in the list. Number of students that matched the search attributes and the search attributes will be shown in the status message. Timestamp in the status bar is updated.

    3. Test case: `search_stu name/harry`<br>
       Expected: No students will show up in the list. Message to inform user that no matching students with the search attributes shown in the status message. Timestamp in the status bar is updated.

    4. Test case: `search name/harry potter`<br>
       Expected: No students will show up in the list. Message to inform user that input is an unknown command will be shown in the status message. Timestamp in the status bar is updated.

    5. Other incorrect search student commands to try: `search_stu name/har`, `search_stu matric/A0`<br>
       Expected: Similar to previous.


--------------------------------------------------------------------------------------------------------------------

## **Appendix: Planned Enhancements**

### Edit Functionality

1. Add support for editing existing entries (students, staff, external parties or events). e.g. `edit_stu name/Alice email/Alice@example.com`.
2. Users will be able to update one or more fields of an existing person based on their index in their respective lists.
3. Only the specified fields will be updated, fields not included will remain unchanged.
4. Validation rules will still apply during editing. (e.g. for phone, email, matric)

### Partial Searching

1. Add support for partial and fuzzy searching. e.g. `John` will match `John Doe`, and `ball` will match `Basketball Training`.

### Maximum Number of residents in a room

1. Add support for enforcing a limit on the number of different staff and students that can belong to the same combination of block, level and room.

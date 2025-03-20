package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.event.Event;
import seedu.address.model.person.ExternalParty;
import seedu.address.model.person.Person;
import seedu.address.model.person.Staff;
import seedu.address.model.person.Student;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;
    Predicate<Staff> PREDICATE_SHOW_ALL_STAFF = unused -> true;
    Predicate<ExternalParty> PREDICATE_SHOW_ALL_EXTERNALPARTIES = unused -> true;
    Predicate<Event> PREDICATE_SHOW_ALL_EVENTS = unused -> true;
    Predicate<Student> PREDICATE_SHOW_ALL_STUDENTS = unused -> true;


    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Person person);

    /**
     * Returns true if a staff with the same identity as {@code staff} exists in the address book.
     */
    boolean hasStaff(Staff staff);

    /**
     * Returns true if an external party with the same identity as {@code externalParty} exists in the address book.
     */
    boolean hasExternalParty(ExternalParty externalParty);

    /**
     * Returns true if a student with the same identity as {@code student} exists in the address book.
     */
    boolean hasStudent(Student student);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Person target);

    /**
     * Deletes the given staff.
     * The staff must exist in the address book.
     */
    void deleteStaff(Staff target);

    /**
     * Deletes the given external party.
     * The external party must exist in the address book.
     */
    void deleteExternalParty(ExternalParty target);

    /**
     * Deletes the given student.
     * The student must exist in the address book.
     */
    void deleteStudent(Student student);


    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person person);

    /**
     * Adds the given staff.
     * {@code staff} must not already exist in the address book.
     */
    void addStaff(Staff staff);

    /**
     * Adds the given external party.
     * {@code externalParty} must not already exist in the address book.
     */
    void addExternalParty(ExternalParty externalParty);

    /**
     * Adds the given student.
     * {@code student} must not already exist in the address book.
     */
    void addStudent(Student student);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);

    /**
     * Replaces the given staff {@code target} with {@code editedStaff}.
     * {@code target} must exist in the address book.
     * The staff identity of {@code editedStaff} must not be the same as another existing staff in the address book.
     */
    void setStaff(Staff target, Staff editedStaff);

    /**
     * Replaces the given external party {@code target} with {@code editedExternalParty}.
     * {@code target} must exist in the address book.
     * The external party identity of {@code editedPerson} must not be the same as another existing external party
     * in the address book.
     */
    void setExternalParty(ExternalParty target, ExternalParty editedExternalParty);

    /**
     * Replaces the given student {@code target} with {@code editedStudent}.
     * {@code target} must exist in the address book.
     * The student identity of {@code editedStudent} must not be the same as another existing student in the address
     * book.
     */
    void setStudent(Student target, Student editedStudent);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);


    //=========== Event Management =============================================================
    /** Checks if the address book contains the given event. */
    boolean hasEvent(Event event);

    /** Adds a new event to the address book. */
    void addEvent(Event event);

    /** Deletes the given event. */
    void deleteEvent(Event target);

    /** Returns the list of events, filtered by the current criteria. */
    ObservableList<Event> getFilteredEventList();

    /** Updates the filter for displaying events. */
    void updateFilteredEventList(Predicate<Event> predicate);

    /** Returns an unmodifiable view of the list of events */
    ObservableList<Event> getEventList();

    /** Returns an unmodifiable view of the filtered staff list */
    ObservableList<Staff> getFilteredStaffList();

    /**
     * Updates the filter of the filtered staff list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredStaffList(Predicate<Staff> predicate);

    /** Returns an unmodifiable view of the filtered external party list */
    ObservableList<ExternalParty> getFilteredExternalPartyList();

    /**
     * Updates the filter of the filtered external party list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredExternalPartyList(Predicate<ExternalParty> predicate);

    /** Returns an unmodifiable view of the filtered student list */
    ObservableList<Student> getFilteredStudentList();

    /**
     * Updates the filter of the filtered student list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredStudentList(Predicate<Student> predicate);

    /**
     * Gets the ObjectProperty of list (person, staff, etc) that should be displayed now.
     */
    ObjectProperty<ListType> getListTypeProperty();


    /**
     * Gets the list type of the list that should be displayed now
     */
    ListType getListType();

    /**
     * Set the type of list (person, staff) to be displayed now
     */
    void setListType(ListType listType);

}

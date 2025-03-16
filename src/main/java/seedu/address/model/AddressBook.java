package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.ExternalParty;
import seedu.address.model.person.Person;
import seedu.address.model.person.Staff;
import seedu.address.model.person.UniqueExternalPartyList;
import seedu.address.model.event.Event;
import seedu.address.model.event.UniqueEventList;
import seedu.address.model.person.Person;
import seedu.address.model.person.Staff;
import seedu.address.model.person.Student;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.person.UniqueStaffList;
import seedu.address.model.person.UniqueStudentList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList persons;
    private final UniqueEventList events;
    private final UniqueStaffList staff;
    private final UniqueStudentList students;

    private final UniqueExternalPartyList externalParty;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
        events = new UniqueEventList();
        staff = new UniqueStaffList();
        externalParty = new UniqueExternalPartyList();
        students = new UniqueStudentList();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    /**
     * Replaces the contents of the staff list with {@code staff}.
     * {@code staff} must not contain duplicate staff.
     */
    public void setStaffs(List<Staff> staff) {
        this.staff.setStaffs(staff);
    }

    /**
     * Replaces the contents of the external party list with {@code externalParty}.
     * {@code externalParty} must not contain duplicate persons.
     */
    public void setExternalParties(List<ExternalParty> externalParty) {
        this.externalParty.setExternalParties(externalParty);
    }

    /**
     * Replaces the contents of the student list with {@code student}.
     * {@code students} must not contain duplicate students.
     */
    public void setStudents(List<Student> students) {
        this.students.setStudents(students);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
        setEvents(newData.getEventList());
        setStaffs(newData.getStaffList());
        setExternalParties(newData.getExternalPartyList());
        setStudents(newData.getStudentList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Returns true if a staff with the same identity as {@code staff} exists in the address book.
     */
    public boolean hasStaff(Staff staff) {
        requireNonNull(staff);
        return this.staff.contains(staff);
    }

    /**
     * Returns true if an external party with the same identity as {@code externalParty} exists in the address book.
     */
    public boolean hasExternalParty(ExternalParty externalParty) {
        requireNonNull(externalParty);
        return this.externalParty.contains(externalParty);
    }

    /**
     * Returns true if a student with the same identity as {@code student} exists in the address book.
     */
    public boolean hasStudent(Student student) {
        requireNonNull(student);
        return this.students.contains(student);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Adds a staff to the address book.
     * The staff must not already exist in the address book.
     */
    public void addStaff(Staff s) {
        this.staff.add(s);
    }

    /**
     * Adds an external party to the address book.
     * The external party must not already exist in the address book.
     */
    public void addExternalParty(ExternalParty e) {
        this.externalParty.add(e);
    }

    /**
     * Adds a student to the address book.
     * The student must not already exist in the address book.
     */
    public void addStudent(Student s) {
        this.students.add(s);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
    }

    /**
     * Replaces the given staff {@code target} in the list with {@code editedStaff}.
     * {@code target} must exist in the address book.
     * The staff identity of {@code editedStaff} must not be the same as another existing staff in the address book.
     */
    public void setStaff(Staff target, Staff editedStaff) {
        requireNonNull(editedStaff);
        this.staff.setStaff(target, editedStaff);
    }

    /**
     * Replaces the given external party {@code target} in the list with {@code editedStaff}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedExternalParty} must not be the same as another existing external party
     * in the address book.
     */
    public void setExternalParty(ExternalParty target, ExternalParty editedExternalParty) {
        requireNonNull(editedExternalParty);
        this.externalParty.setExternalParty(target, editedExternalParty);
    }

    /**
     * Replaces the given student {@code target} in the list with {@code editedStudent}.
     * {@code target} must exist in the address book.
     * The student identity of {@code editedStudent} must not be the same as another existing student in the address
     * book.
     */
    public void setStudent(Student target, Student editedStudent) {
        requireNonNull(editedStudent);
        this.students.setStudent(target, editedStudent);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }


    //// Event level operations
    public void setEvents(List<Event> events) {
        this.events.setEvents(events);
    }

    /**
     * Checks if the event list contains the given event.
     *
     * @param event The event to check.
     * @return True if the event exists in the list, false otherwise.
     * @throws NullPointerException If the event is null.
     */
    public boolean hasEvent(Event event) {
        requireNonNull(event);
        return events.contains(event);
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    public void setEvent(Event target, Event editedEvent) {
        requireNonNull(editedEvent);
        events.setEvent(target, editedEvent);
    }

    public void removeEvent(Event key) {
        events.remove(key);
    }

    @Override
    public ObservableList<Event> getEventList() {
        return events.asUnmodifiableObservableList();
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeStaff(Staff key) {
        this.staff.remove(key);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeExternalParty(ExternalParty key) {
        this.externalParty.remove(key);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeStudent(Student key) {
        this.students.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("persons", persons)
                .toString();
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Staff> getStaffList() {
        return this.staff.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<ExternalParty> getExternalPartyList() {
        return this.externalParty.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Student> getStudentList() {
        return this.students.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddressBook)) {
            return false;
        }

        AddressBook otherAddressBook = (AddressBook) other;

        return persons.equals(otherAddressBook.persons)
                && events.equals(otherAddressBook.events)
                && staff.equals(otherAddressBook.staff)
                && students.equals(otherAddressBook.students)
                && externalParty.equals(otherAddressBook.externalParty);

    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }



}

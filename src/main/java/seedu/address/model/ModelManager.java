package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.ExternalParty;
import seedu.address.model.event.Event;
import seedu.address.model.person.Person;
import seedu.address.model.person.Staff;
import seedu.address.model.person.Student;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final ObjectProperty<ListType> currentListTypeProperty = new SimpleObjectProperty<>(ListType.PERSON);
    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<ExternalParty> filteredExternalParty;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Event> filteredEvents;
    private final FilteredList<Staff> filteredStaff;
    private final FilteredList<Student> filteredStudents;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredExternalParty = new FilteredList<>(this.addressBook.getExternalPartyList());
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        filteredEvents = new FilteredList<>(this.addressBook.getEventList());
        filteredStaff = new FilteredList<>(this.addressBook.getStaffList());
        filteredStudents = new FilteredList<>(this.addressBook.getStudentList());
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return addressBook.hasPerson(person);
    }

    @Override
    public boolean hasStaff(Staff staff) {
        requireNonNull(staff);
        return addressBook.hasStaff(staff);
    }

    @Override
    public boolean hasExternalParty(ExternalParty externalParty) {
        requireNonNull(externalParty);
        return addressBook.hasExternalParty(externalParty);
    }

    @Override
    public boolean hasStudent(Student student) {
        requireNonNull(student);
        return addressBook.hasStudent(student);
    }

    @Override
    public void deletePerson(Person target) {
        addressBook.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        addressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        addressBook.setPerson(target, editedPerson);
    }

    @Override
    public void deleteStaff(Staff target) {
        addressBook.removeStaff(target);
    }

    @Override
    public void addStaff(Staff staff) {
        addressBook.addStaff(staff);
        updateFilteredStaffList(PREDICATE_SHOW_ALL_STAFF);
    }

    @Override
    public void setStaff(Staff target, Staff editedStaff) {
        requireAllNonNull(target, editedStaff);
        addressBook.setStaff(target, editedStaff);
    }

    @Override
    public void deleteExternalParty(ExternalParty target) {
        addressBook.removeExternalParty(target);
    }

    @Override
    public void addExternalParty(ExternalParty externalParty) {
        addressBook.addExternalParty(externalParty);
        updateFilteredExternalPartyList(PREDICATE_SHOW_ALL_EXTERNALPARTIES);
    }

    @Override
    public void setExternalParty(ExternalParty target, ExternalParty editedExternalParty) {
        requireAllNonNull(target, editedExternalParty);
        addressBook.setExternalParty(target, editedExternalParty);
    }

    @Override
    public void deleteStudent(Student target) {
        addressBook.removeStudent(target);
    }

    @Override
    public void addStudent(Student student) {
        addressBook.addStudent(student);
        updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
    }

    @Override
    public void setStudent(Student target, Student editedStudent) {
        requireAllNonNull(target, editedStudent);
        addressBook.setStudent(target, editedStudent);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
        setListType(ListType.PERSON);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModelManager)) {
            return false;
        }

        ModelManager otherModelManager = (ModelManager) other;
        return addressBook.equals(otherModelManager.addressBook)
                && userPrefs.equals(otherModelManager.userPrefs)
                && filteredPersons.equals(otherModelManager.filteredPersons);
    }



    //=========== Filtered Event list Assessors =======================================================================
    @Override
    public boolean hasEvent(Event event) {
        requireNonNull(event);
        return addressBook.hasEvent(event);
    }

    @Override
    public void addEvent(Event event) {
        addressBook.addEvent(event);
        updateFilteredEventList(e -> true);
    }

    @Override
    public void deleteEvent(Event target) {
        addressBook.removeEvent(target);
    }

    @Override
    public ObservableList<Event> getFilteredEventList() {
        return filteredEvents;
    }

    @Override
    public void updateFilteredEventList(Predicate<Event> predicate) {
        requireNonNull(predicate);
        filteredEvents.setPredicate(predicate);
        setListType(ListType.EVENT);
    }

    //=========== Filtered Staff List Accessors =============================================================
    @Override
    public ObservableList<Staff> getFilteredStaffList() {
        return filteredStaff;
    }

    @Override
    public void updateFilteredStaffList(Predicate<Staff> predicate) {
        requireNonNull(predicate);
        filteredStaff.setPredicate(predicate);
        setListType(ListType.STAFF);
    }

    //=========== Filtered External Party List Accessors =============================================================
    @Override
    public ObservableList<ExternalParty> getFilteredExternalPartyList() {
        return filteredExternalParty;
    }

    @Override
    public void updateFilteredExternalPartyList(Predicate<ExternalParty> predicate) {
        requireNonNull(predicate);
        filteredExternalParty.setPredicate(predicate);
    }

    //=========== Filtered Student List Accessors =============================================================
    @Override
    public ObservableList<Student> getFilteredStudentList() {
        return filteredStudents;
    }

    @Override
    public void updateFilteredStudentList(Predicate<Student> predicate) {
        requireNonNull(predicate);
        filteredStudents.setPredicate(predicate);
        setListType(ListType.STUDENT);
    }

    //=========== List Accessors ============================================================================
    @Override
    public ObjectProperty<ListType> getListTypeProperty() {
        return currentListTypeProperty;
    };

    @Override
    public ListType getListType() {
        return currentListTypeProperty.get();
    }

    @Override
    public void setListType(ListType listType) {
        currentListTypeProperty.set(listType);
    };

}

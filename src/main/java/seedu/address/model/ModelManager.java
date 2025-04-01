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
import seedu.address.commons.core.index.Index;
import seedu.address.model.event.Event;
import seedu.address.model.person.Email;
import seedu.address.model.person.ExternalParty;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Staff;
import seedu.address.model.person.Student;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final ObjectProperty<ListType> currentListTypeProperty = new SimpleObjectProperty<>(ListType.STUDENT);
    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<ExternalParty> filteredExternalParty;
    private final FilteredList<Event> filteredEvents;
    private final FilteredList<Staff> filteredStaff;
    private final FilteredList<Student> filteredStudents;
    private Event selectedEventDetail;
    private Index selectedEventIndex;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredExternalParty = new FilteredList<>(this.addressBook.getExternalPartyList());
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
    public boolean hasPersonWithPhoneOrEmail(Phone phone, Email email) {
        return this.addressBook.hasPersonWithPhoneOrEmail(phone, email);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
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
                && filteredStudents.equals(otherModelManager.filteredStudents)
                && filteredStaff.equals(otherModelManager.filteredStaff)
                && filteredExternalParty.equals(otherModelManager.filteredExternalParty)
                && filteredEvents.equals(otherModelManager.filteredEvents);
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

    @Override
    public ObservableList<Event> getEventList() {
        return addressBook.getEventList();
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
        setListType(ListType.EXTERNAL);
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
    }


    @Override
    public ListType getListType() {
        return currentListTypeProperty.get();
    }

    @Override
    public void setListType(ListType listType) {
        currentListTypeProperty.set(listType);
    }


    //=========== View Event Detail Accessors =============================================================
    @Override
    public void setSelectedEventDetail(Event event, Index eventIndex) {
        requireNonNull(event);
        requireNonNull(eventIndex);

        this.selectedEventDetail = event;
        this.selectedEventIndex = eventIndex;

        setListType(ListType.EVENT); // Temporarily switch to EVENT to Force a property update by resetting
        setListType(ListType.EVENTDETAIL);
    }

    @Override
    public Event getSelectedEventDetail() {
        return this.selectedEventDetail;
    }

    @Override
    public Index getSelectedEventIndex() {
        return this.selectedEventIndex;
    }

}


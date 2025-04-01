package seedu.address.logic.commands;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.model.ListType;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.event.Event;
import seedu.address.model.person.Email;
import seedu.address.model.person.ExternalParty;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Staff;
import seedu.address.model.person.Student;

/**
 * A default model stub that have all the methods failing.
 */
public class ModelStub implements Model {
    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public GuiSettings getGuiSettings() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Path getAddressBookFilePath() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setAddressBook(ReadOnlyAddressBook newData) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasPersonWithPhoneAndEmail(Phone phone, Email email) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasEvent(Event event) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addEvent(Event event) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteEvent(Event target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Event> getFilteredEventList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredEventList(Predicate<Event> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Event> getEventList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasStaff(Staff staff) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteStaff(Staff target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addStaff(Staff staff) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setStaff(Staff target, Staff editedStaff) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Staff> getFilteredStaffList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredStaffList(Predicate<Staff> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasExternalParty(ExternalParty externalParty) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteExternalParty(ExternalParty externalParty) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addExternalParty(ExternalParty externalParty) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setExternalParty(ExternalParty target, ExternalParty editedExternalParty) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<ExternalParty> getFilteredExternalPartyList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredExternalPartyList(Predicate<ExternalParty> predicate) {
        throw new AssertionError("This method should not be called.");
    }


    @Override
    public ObjectProperty<ListType> getListTypeProperty() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ListType getListType() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setListType(ListType listType) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasStudent(Student student) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteStudent(Student target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addStudent(Student student) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setStudent(Student target, Student editedStudent) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Student> getFilteredStudentList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredStudentList(Predicate<Student> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setSelectedEventDetail(Event event, Index eventIndex) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Index getSelectedEventIndex() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Event getSelectedEventDetail() {
        throw new AssertionError("This method should not be called.");
    }

}

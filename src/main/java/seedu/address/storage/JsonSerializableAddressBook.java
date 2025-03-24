package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
//import seedu.address.model.event.Event;
import seedu.address.model.person.ExternalParty;
import seedu.address.model.person.Staff;
import seedu.address.model.person.Student;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {
    public static final String MESSAGE_DUPLICATE_STAFF = "Staff list contains duplicate staff(s).";
    public static final String MESSAGE_DUPLICATE_STUDENT = "Students list contains duplicate student(s).";
    public static final String MESSAGE_DUPLICATE_EXTERNAL_PARTY =
            "External parties list contains duplicate external party(ies).";
    //    public static final String MESSAGE_DUPLICATE_EVENT = "Events list contains duplicate event(s).";

    private final List<JsonAdaptedStaff> staffs = new ArrayList<>();
    private final List<JsonAdaptedStudent> students = new ArrayList<>();
    private final List<JsonAdaptedExternalParty> externalParties = new ArrayList<>();
    //    private final List<JsonAdaptedEvent> events = new ArrayList<>();      // DO NOT DELETE. FOR V1.3

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("staffs") List<JsonAdaptedStaff> staffs,
                                       @JsonProperty("students") List<JsonAdaptedStudent> students,
                                       @JsonProperty("externalParties")
                                           List<JsonAdaptedExternalParty> externalParties) {
        this.staffs.addAll(staffs);
        this.students.addAll(students);
        this.externalParties.addAll(externalParties);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        staffs.addAll(source.getStaffList().stream().map(JsonAdaptedStaff::new).collect(Collectors.toList()));
        students.addAll(source.getStudentList().stream().map(JsonAdaptedStudent::new).collect(Collectors.toList()));
        externalParties.addAll(source.getExternalPartyList().stream().map(JsonAdaptedExternalParty::new)
                .collect(Collectors.toList()));
        //        events.addAll(source.getEventList().stream().map(JsonAdaptedEvent::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();

        for (JsonAdaptedStaff jsonAdaptedStaff : staffs) {
            Staff staff = jsonAdaptedStaff.toModelType();
            if (addressBook.hasStaff(staff)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_STAFF);
            }
            addressBook.addStaff(staff);
        }

        for (JsonAdaptedStudent jsonAdaptedStudent : students) {
            Student student = jsonAdaptedStudent.toModelType();
            if (addressBook.hasStudent(student)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_STUDENT);
            }
            addressBook.addStudent(student);
        }

        for (JsonAdaptedExternalParty jsonAdaptedExternalParty : externalParties) {
            ExternalParty externalParty = jsonAdaptedExternalParty.toModelType();
            if (addressBook.hasExternalParty(externalParty)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_EXTERNAL_PARTY);
            }
        }

        //        // Convert events back into the AddressBook
        //        for (JsonAdaptedEvent jsonAdaptedEvent : events) {
        //            Event event = jsonAdaptedEvent.toModelType();
        //            if (addressBook.hasEvent(event)) {
        //                throw new IllegalValueException(MESSAGE_DUPLICATE_EVENT);
        //            }
        //            addressBook.addEvent(event);
        //        }

        return addressBook;
    }

}

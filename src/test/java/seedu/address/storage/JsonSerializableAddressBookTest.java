package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.AddressBook;
import seedu.address.testutil.TypicalPersons;

public class JsonSerializableAddressBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableAddressBookTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalPersonsAddressBook.json");
    private static final Path TYPICAL_FILE = TEST_DATA_FOLDER.resolve("typicalAddressBook.json");

    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidPersonAddressBook.json");
    private static final Path INVALID_STUDENT_FILE = TEST_DATA_FOLDER.resolve("invalidStudentAddressBook.json");
    private static final Path INVALID_STAFF_FILE = TEST_DATA_FOLDER.resolve("invalidStaffAddressBook.json");
    private static final Path INVALID_EXTERNAL_PARTY_FILE = TEST_DATA_FOLDER.resolve("invalidExternalPartyAddressBook.json");

    private static final Path DUPLICATE_STUDENT_FILE = TEST_DATA_FOLDER.resolve("duplicateStudentAddressBook.json");
    private static final Path DUPLICATE_STAFF_FILE = TEST_DATA_FOLDER.resolve("duplicateStaffAddressBook.json");
    private static final Path DUPLICATE_EXTERNAL_PARTY_FILE = TEST_DATA_FOLDER.resolve("duplicateExternalPartyAddressBook.json");

//    @Test
//    public void toModelType_typicalPersonsFile_success() throws Exception {
//        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
//                JsonSerializableAddressBook.class).get();
//        AddressBook addressBookFromFile = dataFromFile.toModelType();
//        AddressBook typicalPersonsAddressBook = TypicalPersons.getTypicalAddressBook();
//        assertEquals(addressBookFromFile, typicalPersonsAddressBook);
//    }

    @Test
    public void toModelType_typicalFile_success() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_FILE,
                JsonSerializableAddressBook.class).get();
        AddressBook addressBookFromFile = dataFromFile.toModelType();
        AddressBook typicalPersonsAddressBook = TypicalPersons.getTypicalAddressBook();
        assertEquals(addressBookFromFile, typicalPersonsAddressBook);
    }

//    @Test
//    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
//        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
//                JsonSerializableAddressBook.class).get();
//        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
//    }

    @Test
    public void toModelType_invalidStudentFile_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(INVALID_STUDENT_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_invalidStaffFile_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(INVALID_STAFF_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_invalidExternalPartyFile_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(INVALID_EXTERNAL_PARTY_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateStudents_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_STUDENT_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableAddressBook.MESSAGE_DUPLICATE_STUDENT,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateStaff_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_STAFF_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableAddressBook.MESSAGE_DUPLICATE_STAFF,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateExternalParty_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_EXTERNAL_PARTY_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableAddressBook.MESSAGE_DUPLICATE_EXTERNAL_PARTY,
                dataFromFile::toModelType);
    }

}

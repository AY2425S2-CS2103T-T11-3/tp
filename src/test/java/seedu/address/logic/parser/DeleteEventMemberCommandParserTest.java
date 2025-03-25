package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX;
import static seedu.address.logic.Messages.MESSAGE_INVALID_EXTERNAL_PARTY_DISPLAYED_INDEX;
import static seedu.address.logic.Messages.MESSAGE_INVALID_STAFF_DISPLAYED_INDEX;
import static seedu.address.logic.Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX;
import static seedu.address.logic.Messages.MESSAGE_MISSING_EVENT_MEMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_EXTERNAL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_STAFF;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_STUDENT;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.event.DeleteEventMemberCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class DeleteEventMemberCommandParserTest {

    private final DeleteEventMemberCommandParser parser = new DeleteEventMemberCommandParser();

    @Test
    public void parse_validStudentIndex_success() throws Exception {
        String input = "1 " + PREFIX_EVENT_STUDENT + "2";
        DeleteEventMemberCommand expectedCommand = new DeleteEventMemberCommand(
                Index.fromOneBased(1),
                Optional.of(Index.fromOneBased(2)),
                Optional.empty(),
                Optional.empty());

        assertEquals(expectedCommand, parser.parse(input));
    }

    @Test
    public void parse_validStaffIndex_success() throws Exception {
        String input = "1 " + PREFIX_EVENT_STAFF + "3";
        DeleteEventMemberCommand expectedCommand = new DeleteEventMemberCommand(
                Index.fromOneBased(1),
                Optional.empty(),
                Optional.of(Index.fromOneBased(3)),
                Optional.empty());

        assertEquals(expectedCommand, parser.parse(input));
    }

    @Test
    public void parse_validExternalIndex_success() throws Exception {
        String input = "1 " + PREFIX_EVENT_EXTERNAL + "4";
        DeleteEventMemberCommand expectedCommand = new DeleteEventMemberCommand(
                Index.fromOneBased(1),
                Optional.empty(),
                Optional.empty(),
                Optional.of(Index.fromOneBased(4)));

        assertEquals(expectedCommand, parser.parse(input));
    }

    @Test
    public void parse_invalidMultipleMembers_failure() throws Exception {
        String input = "1 " + PREFIX_EVENT_STUDENT + "2 " + PREFIX_EVENT_STAFF + "3";

        assertThrows(ParseException.class, () -> parser.parse(input));
    }

    @Test
    public void parse_invalidEventIndex_throwsParseException() {
        String input = "a " + PREFIX_EVENT_STUDENT + "2";

        ParseException thrown = assertThrows(ParseException.class, () -> parser.parse(input));
        assertEquals(MESSAGE_INVALID_EVENT_DISPLAYED_INDEX + "\n"
                + DeleteEventMemberCommand.MESSAGE_USAGE, thrown.getMessage());
    }

    @Test
    public void parse_invalidStudentIndex_throwsParseException() {
        String input = "1 " + PREFIX_EVENT_STUDENT + "a";

        ParseException thrown = assertThrows(ParseException.class, () -> parser.parse(input));
        assertEquals(MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX + "\n"
                + DeleteEventMemberCommand.MESSAGE_USAGE, thrown.getMessage());
    }

    @Test
    public void parse_invalidStaffIndex_throwsParseException() {
        String input = "1 " + PREFIX_EVENT_STAFF + "x";

        ParseException thrown = assertThrows(ParseException.class, () -> parser.parse(input));
        assertEquals(MESSAGE_INVALID_STAFF_DISPLAYED_INDEX + "\n"
                + DeleteEventMemberCommand.MESSAGE_USAGE, thrown.getMessage());
    }

    @Test
    public void parse_invalidExternalIndex_throwsParseException() {
        String input = "1 " + PREFIX_EVENT_EXTERNAL + "y";

        ParseException thrown = assertThrows(ParseException.class, () -> parser.parse(input));
        assertEquals(MESSAGE_INVALID_EXTERNAL_PARTY_DISPLAYED_INDEX + "\n"
                + DeleteEventMemberCommand.MESSAGE_USAGE, thrown.getMessage());
    }

    @Test
    public void parse_missingEventIndex_throwsParseException() {
        String input = PREFIX_EVENT_STUDENT + "2";

        ParseException thrown = assertThrows(ParseException.class, () -> parser.parse(input));
        assertEquals(MESSAGE_INVALID_EVENT_DISPLAYED_INDEX + "\n"
                + DeleteEventMemberCommand.MESSAGE_USAGE, thrown.getMessage());
    }

    @Test
    public void parse_missingMemberIndex_throwsParseException() {
        String input = "1"; // No student, staff, or external party index provided.

        ParseException thrown = assertThrows(ParseException.class, () -> parser.parse(input));
        assertEquals(MESSAGE_MISSING_EVENT_MEMBER + "\n"
                + DeleteEventMemberCommand.MESSAGE_USAGE, thrown.getMessage());
    }

    @Test
    public void parse_extraSpaces_validParsing() throws Exception {
        String input = "   1   " + PREFIX_EVENT_STUDENT + "   2   ";
        DeleteEventMemberCommand expectedCommand = new DeleteEventMemberCommand(
                Index.fromOneBased(1),
                Optional.of(Index.fromOneBased(2)),
                Optional.empty(),
                Optional.empty());

        assertEquals(expectedCommand, parser.parse(input));
    }
}

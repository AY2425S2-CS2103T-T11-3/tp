package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SearchStudentCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class SearchStudentCommandParserTest {
    private SearchStudentCommandParser parser = new SearchStudentCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SearchStudentCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsSearchStudentCommand() throws ParseException {
        Map<Prefix, String> expectedCriteria = new HashMap<>();
        expectedCriteria.put(PREFIX_NAME, "Alice");
        expectedCriteria.put(PREFIX_PHONE, "12345678");
        SearchStudentCommand expectedCommand = new SearchStudentCommand(expectedCriteria);
        assertParseSuccess(parser, " " + PREFIX_NAME + "Alice " + PREFIX_PHONE + "12345678", expectedCommand);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "Alice Bob", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SearchStudentCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_multiplePrefixes_returnsCorrectSearchCommand() throws ParseException {
        Map<Prefix, String> expectedCriteria = new HashMap<>();
        expectedCriteria.put(PREFIX_NAME, "Alice");
        expectedCriteria.put(PREFIX_EMAIL, "alice@example.com");
        expectedCriteria.put(PREFIX_ROOM, "101");
        SearchStudentCommand expectedCommand = new SearchStudentCommand(expectedCriteria);
        assertParseSuccess(parser, " " + PREFIX_NAME + "Alice " + PREFIX_EMAIL + "alice@example.com "
                + PREFIX_ROOM + "101", expectedCommand);
    }
}

package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SearchExternalPartyCommand;
import seedu.address.logic.parser.exceptions.ParseException;
public class SearchExternalPartyCommandParserTest {
    private SearchExternalPartyCommandParser parser = new SearchExternalPartyCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SearchExternalPartyCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsSearchExternalPartyCommand() throws ParseException {
        Map<Prefix, String> expectedCriteria = new HashMap<>();
        expectedCriteria.put(PREFIX_NAME, "Alice");
        expectedCriteria.put(PREFIX_PHONE, "12345678");
        SearchExternalPartyCommand expectedCommand = new SearchExternalPartyCommand(expectedCriteria);
        assertParseSuccess(parser, " " + PREFIX_NAME + "Alice " + PREFIX_PHONE + "12345678", expectedCommand);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "Alice Bob", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SearchExternalPartyCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_multiplePrefixes_returnsCorrectSearchCommand() throws ParseException {
        Map<Prefix, String> expectedCriteria = new HashMap<>();
        expectedCriteria.put(PREFIX_NAME, "Alice");
        expectedCriteria.put(PREFIX_EMAIL, "alice@example.com");
        SearchExternalPartyCommand expectedCommand = new SearchExternalPartyCommand(expectedCriteria);
        assertParseSuccess(parser, " " + PREFIX_NAME + "Alice " + PREFIX_EMAIL + "alice@example.com "
                , expectedCommand);
    }
}

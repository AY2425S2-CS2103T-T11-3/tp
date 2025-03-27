package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_START_TIME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.event.SearchEventCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventEndTime;
import seedu.address.model.event.EventMatchesPredicate;
import seedu.address.model.event.EventName;
import seedu.address.model.event.EventStartTime;

public class SearchEventCommandParserTest {

    private final SearchEventCommandParser parser = new SearchEventCommandParser();

    @Test
    public void parse_optionalFieldsMissing_success() {
        String userInput = "name/Dance";
        EventMatchesPredicate predicate = new EventMatchesPredicate("Dance", null, null);
        SearchEventCommand expectedCommand = new SearchEventCommand(predicate);
        assertParseFailure(parser, userInput, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SearchEventCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_missingAllPrefixed_failure() {
        String userInput = "random text without prefix";
        assertParseFailure(parser, userInput,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SearchEventCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_noParameters_failure() {
        String userInput = "";
        assertParseFailure(parser, userInput,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SearchEventCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validParameters_success() throws ParseException {
        String userInput = " "
                + PREFIX_EVENT_NAME + "Dance "
                + PREFIX_EVENT_START_TIME + "2025-02-15 18:00 "
                + PREFIX_EVENT_END_TIME + "2025-02-15 21:00";

        Event expectedEvent = new Event(
                new EventName("Dance"),
                new EventStartTime("2025-02-15 18:00"),
                new EventEndTime("2025-02-15 21:00"));

        EventMatchesPredicate predicate = new EventMatchesPredicate("Dance", new EventStartTime("2025-02-15 18:00"),
                new EventEndTime("2025-02-15 21:00"));
        SearchEventCommand expectedCommand = new SearchEventCommand(predicate);
        assertEquals(expectedCommand, parser.parse(userInput));
    }

    @Test
    public void parse_invalidStartTime_failure() {
        String userInput = " "
                + PREFIX_EVENT_NAME + "Dance "
                + PREFIX_EVENT_START_TIME + "2025-02-15 18:0 "
                + PREFIX_EVENT_END_TIME + "2025-02-15 21:00";
        assertParseFailure(parser, userInput, EventStartTime.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidEndTime_failure() {
        String userInput = " "
                + PREFIX_EVENT_NAME + "Dance "
                + PREFIX_EVENT_START_TIME + "2025-02-15 18:00 "
                + PREFIX_EVENT_END_TIME + "2025-02-15 21:0";
        assertParseFailure(parser, userInput, EventEndTime.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_emptyParamsEventName_failure() {
        String userInput = " "
                + PREFIX_EVENT_NAME + " "
                + PREFIX_EVENT_START_TIME + "2025-02-15 18:00 "
                + PREFIX_EVENT_END_TIME + "2025-02-15 21:00";
        assertParseFailure(parser, userInput, SearchEventCommandParser.EMPTY_FIELD_AFTER_PREFIX);
    }

    @Test
    public void parse_emptyParamsEventStartTime_failure() {
        String userInput = " "
                + PREFIX_EVENT_NAME + "Dance "
                + PREFIX_EVENT_START_TIME + " "
                + PREFIX_EVENT_END_TIME + "2025-02-15 21:00";
        assertParseFailure(parser, userInput, SearchEventCommandParser.EMPTY_FIELD_AFTER_PREFIX);
    }

    @Test
    public void parse_emptyParamsEventEndTime_failure() {
        String userInput = " "
                + PREFIX_EVENT_END_TIME + " ";
        assertParseFailure(parser, userInput, SearchEventCommandParser.EMPTY_FIELD_AFTER_PREFIX);
    }
}


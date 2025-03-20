package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.event.SearchEventCommand;
import seedu.address.model.event.EventEndTime;
import seedu.address.model.event.EventMatchesPredicate;
import seedu.address.model.event.EventStartTime;

public class SearchEventCommandParserTest {

    private SearchEventCommandParser parser = new SearchEventCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        String userInput = "name/Dance from/2025-06-15 18:00 to/2025-06-15 21:00";
        EventMatchesPredicate predicate = new EventMatchesPredicate("Dance",
                new EventStartTime("2025-06-15 18:00"), new EventEndTime("2025-06-15 21:00"));
        SearchEventCommand expectedCommand = new SearchEventCommand(predicate);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        String userInput = "name/Dance";
        EventMatchesPredicate predicate = new EventMatchesPredicate("Dance", null, null);
        SearchEventCommand expectedCommand = new SearchEventCommand(predicate);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_noFieldsPresent_failure() {
        String userInput = "";
        assertParseFailure(parser, userInput, String.format(SearchEventCommand.MESSAGE_USAGE,
                SearchEventCommand.COMMAND_WORD));
    }

    @Test
    public void parse_invalidDateFormat_failure() {
        String userInput = "name/Dance from/invalid-date to/2025-06-15 21:00";
        assertParseFailure(parser, userInput, EventStartTime.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidTimeRange_failure() {
        String userInput = "name/Dance from/2025-06-15 21:00 to/2025-06-15 18:00";
        assertParseFailure(parser, userInput, EventEndTime.MESSAGE_CONSTRAINTS);
    }
}

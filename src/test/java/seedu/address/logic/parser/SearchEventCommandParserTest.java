package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.event.SearchEventCommand;
import seedu.address.model.event.EventMatchesPredicate;


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
}


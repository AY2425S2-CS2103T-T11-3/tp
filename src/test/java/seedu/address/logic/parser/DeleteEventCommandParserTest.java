package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.event.DeleteEventCommand.MESSAGE_USAGE;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.event.DeleteEventCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class DeleteEventCommandParserTest {

    private final DeleteEventCommandParser parser = new DeleteEventCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteEventCommand() throws Exception {
        DeleteEventCommand command = parser.parse("1");
        assertEquals(new DeleteEventCommand(Index.fromOneBased(1)), command);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertThrows(ParseException.class,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE), () -> parser.parse("a"));
    }

    @Test
    public void parse_emptyArgs_throwsParseException() {
        assertThrows(ParseException.class,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE), () -> parser.parse(" "));
    }

    @Test
    public void parse_negativeIndex_throwsParseException() {
        assertThrows(ParseException.class,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE), () -> parser.parse("-1"));
    }

    @Test
    public void parse_zeroIndex_throwsParseException() {
        assertThrows(ParseException.class,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE), () -> parser.parse("0"));
    }
}

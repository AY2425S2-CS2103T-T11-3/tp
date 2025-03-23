package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.event.ViewEventCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class ViewEventCommandParserTest {

    private final ViewEventCommandParser parser = new ViewEventCommandParser();

    @Test
    public void parse_validArgs_returnsViewEventCommand() throws Exception {
        ViewEventCommand command = parser.parse("1");
        assertEquals(new ViewEventCommand(Index.fromOneBased(1)), command);
    }

    @Test
    public void parse_validArgsWithSpaces_returnsViewEventCommand() throws Exception {
        ViewEventCommand command = parser.parse("   2   ");
        assertEquals(new ViewEventCommand(Index.fromOneBased(2)), command);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse("abc"));
        assertThrows(ParseException.class, () -> parser.parse("1abc"));
        assertThrows(ParseException.class, () -> parser.parse("")); // empty input
        assertThrows(ParseException.class, () -> parser.parse("  ")); // only spaces
    }
}

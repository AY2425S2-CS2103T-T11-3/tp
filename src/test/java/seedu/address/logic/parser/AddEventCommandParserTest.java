package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_INVALID_STARTTIME_AFTER_ENDTIME;
import static seedu.address.logic.commands.event.AddEventCommand.MESSAGE_USAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_START_TIME;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.event.AddEventCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventEndTime;
import seedu.address.model.event.EventName;
import seedu.address.model.event.EventStartTime;

public class AddEventCommandParserTest {

    private final AddEventCommandParser parser = new AddEventCommandParser();

    @Test
    public void parse_validInput_success() throws Exception {
        String userInput = " "
                + PREFIX_EVENT_NAME + "Concert "
                + PREFIX_EVENT_START_TIME + "2025-07-01 18:00 "
                + PREFIX_EVENT_END_TIME + "2025-07-01 22:00";

        Event expectedEvent = new Event(
                new EventName("Concert"),
                new EventStartTime("2025-07-01 18:00"),
                new EventEndTime("2025-07-01 22:00"));

        AddEventCommand expectedCommand = new AddEventCommand(expectedEvent);
        assertEquals(expectedCommand, parser.parse(userInput));
    }

    @Test
    public void parse_missingFields_failure() {
        // missing start time
        String userInput = " "
                + PREFIX_EVENT_NAME + "Concert "
                + PREFIX_EVENT_END_TIME + "2025-07-01 22:00";

        assertThrows(ParseException.class,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE), () -> parser.parse(userInput));
    }

    @Test
    public void parse_invalidTimeOrder_failure() {
        String userInput = " "
                + PREFIX_EVENT_NAME + "Concert "
                + PREFIX_EVENT_START_TIME + "2025-07-01 23:00 "
                + PREFIX_EVENT_END_TIME + "2025-07-01 22:00";

        assertThrows(ParseException.class,
                MESSAGE_INVALID_STARTTIME_AFTER_ENDTIME, () -> parser.parse(userInput));
    }

    @Test
    public void parse_duplicateFields_failure() {
        String userInput = " "
                + PREFIX_EVENT_NAME + "Concert "
                + PREFIX_EVENT_NAME + "Another Concert "
                + PREFIX_EVENT_START_TIME + "2025-07-01 18:00 "
                + PREFIX_EVENT_END_TIME + "2025-07-01 22:00";

        assertThrows(ParseException.class, () -> parser.parse(userInput));
    }
}

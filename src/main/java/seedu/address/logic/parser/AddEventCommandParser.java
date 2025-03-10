package seedu.address.logic.parser;

import seedu.address.logic.commands.eventCommands.AddEventCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventEndTime;
import seedu.address.model.event.EventName;
import seedu.address.model.event.EventStartTime;

import java.util.stream.Stream;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_INVALID_STARTTIME_AFTER_ENDTIME;
import static seedu.address.logic.parser.CliSyntax.*;


public class AddEventCommandParser implements Parser<AddEventCommand> {

    public AddEventCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_EVENT_NAME, PREFIX_EVENT_START_TIME, PREFIX_EVENT_END_TIME);

        if (!arePrefixesPresent(argMultimap, PREFIX_EVENT_NAME, PREFIX_EVENT_START_TIME, PREFIX_EVENT_END_TIME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddEventCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_EVENT_NAME);
        EventName eventName = ParserUtil.parseEventName(argMultimap.getValue(PREFIX_EVENT_NAME).get());
        EventStartTime eventStartTime = ParserUtil.parseEventStartTime(argMultimap.getValue(PREFIX_EVENT_START_TIME).get());
        EventEndTime eventEndTime = ParserUtil.parseEventEndTime(argMultimap.getValue(PREFIX_EVENT_END_TIME).get());

        // Ensure start time is before end time
        if (!eventStartTime.isBefore(eventEndTime)) {
            throw new ParseException(MESSAGE_INVALID_STARTTIME_AFTER_ENDTIME);
        }
        Event event = new Event(eventName, eventStartTime, eventEndTime);

        return new AddEventCommand(event);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }


}

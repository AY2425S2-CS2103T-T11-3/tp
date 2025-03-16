package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_START_TIME;

import java.util.Optional;

import seedu.address.logic.commands.event.SearchEventCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.EventEndTime;
import seedu.address.model.event.EventStartTime;

/**
 * Parses input arguments and creates a SearchEventCommand object.
 */
public class SearchEventCommandParser implements Parser<SearchEventCommand> {

    @Override
    public SearchEventCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_EVENT_NAME,
                PREFIX_EVENT_START_TIME, PREFIX_EVENT_END_TIME);

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SearchEventCommand.MESSAGE_USAGE));
        }

        Optional<String> eventName = argMultimap.getValue(CliSyntax.PREFIX_EVENT_NAME);
        Optional<String> startTimeStr = argMultimap.getValue(CliSyntax.PREFIX_EVENT_START_TIME);
        Optional<String> endTimeStr = argMultimap.getValue(CliSyntax.PREFIX_EVENT_END_TIME);

        if (eventName.isEmpty() && startTimeStr.isEmpty() && endTimeStr.isEmpty()) {
            throw new ParseException(SearchEventCommand.MESSAGE_NO_PARAMETERS);
        }

        EventStartTime startTime = startTimeStr.map(EventStartTime::new).orElse(null);
        EventEndTime endTime = endTimeStr.map(EventEndTime::new).orElse(null);

        if (startTime != null && endTime != null && endTime.endTime.isBefore(startTime.startTime)) {
            throw new ParseException(SearchEventCommand.MESSAGE_INVALID_START_END_TIME);
        }

        return new SearchEventCommand(eventName.orElse(null), startTime, endTime);
    }
}

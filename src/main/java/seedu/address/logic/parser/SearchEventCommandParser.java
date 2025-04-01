package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_EMPTY_FIELD_AFTER_PREFIX;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_START_TIME;

import java.util.Optional;

import seedu.address.logic.commands.event.SearchEventCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.EventEndTime;
import seedu.address.model.event.EventMatchesPredicate;
import seedu.address.model.event.EventStartTime;

/**
 * Parses input arguments and creates a new SearchEventCommand object.
 */
public class SearchEventCommandParser implements Parser<SearchEventCommand> {

    @Override
    public SearchEventCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_EVENT_NAME,
                PREFIX_EVENT_START_TIME, PREFIX_EVENT_END_TIME);

        if (!argMultimap.getValue(PREFIX_EVENT_NAME).isPresent()
                && !argMultimap.getValue(PREFIX_EVENT_START_TIME).isPresent()
                && !argMultimap.getValue(PREFIX_EVENT_END_TIME).isPresent()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SearchEventCommand.MESSAGE_USAGE));
        }

        Optional<String> eventName = argMultimap.getValue(PREFIX_EVENT_NAME);
        Optional<String> startTimeStr = argMultimap.getValue(PREFIX_EVENT_START_TIME);
        Optional<String> endTimeStr = argMultimap.getValue(PREFIX_EVENT_END_TIME);
        if (eventName.isPresent() && eventName.get().trim().isEmpty()) {
            throw new ParseException(MESSAGE_EMPTY_FIELD_AFTER_PREFIX);
        }

        EventStartTime startTime = null;
        EventEndTime endTime = null;
        if (startTimeStr.isPresent()) {
            if (startTimeStr.get().trim().isEmpty()) {
                throw new ParseException(MESSAGE_EMPTY_FIELD_AFTER_PREFIX);
            } else if (!EventStartTime.isValidStartTime(startTimeStr.get())) {
                throw new ParseException(EventStartTime.MESSAGE_CONSTRAINTS);
            }
            startTime = new EventStartTime(startTimeStr.get());
        }

        if (endTimeStr.isPresent()) {
            if (endTimeStr.get().trim().isEmpty()) {
                throw new ParseException(MESSAGE_EMPTY_FIELD_AFTER_PREFIX);
            } else if (!EventEndTime.isValidEndTime(endTimeStr.get())) {
                throw new ParseException(EventEndTime.MESSAGE_CONSTRAINTS);
            }
            endTime = new EventEndTime(endTimeStr.get());
        }

        EventMatchesPredicate predicate = new EventMatchesPredicate(eventName.orElse(null), startTime, endTime);
        return new SearchEventCommand(predicate);
    }
}

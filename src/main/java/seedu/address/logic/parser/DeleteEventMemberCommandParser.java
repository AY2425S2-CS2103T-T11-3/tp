package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_EXTERNAL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_STAFF;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_STUDENT;

import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.event.DeleteEventMemberCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteEventMemberCommand object.
 */
public class DeleteEventMemberCommandParser implements Parser<DeleteEventMemberCommand> {

    @Override
    public DeleteEventMemberCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_EVENT_STUDENT, PREFIX_EVENT_STAFF,
                PREFIX_EVENT_EXTERNAL);

        Index eventIndex;
        Optional<Index> studentIndex = Optional.empty();
        Optional<Index> staffIndex = Optional.empty();
        Optional<Index> externalIndex = Optional.empty();

        try {
            eventIndex = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteEventMemberCommand.MESSAGE_USAGE), e);
        }

        try {
            if (argMultimap.getValue(PREFIX_EVENT_STUDENT).isPresent()) {
                studentIndex = Optional.of(ParserUtil.parseIndex(argMultimap.getValue(PREFIX_EVENT_STUDENT).get()));
            }
        } catch (ParseException e) {
            throw new ParseException("Invalid student index format!", e);
        }

        try {
            if (argMultimap.getValue(PREFIX_EVENT_STAFF).isPresent()) {
                staffIndex = Optional.of(ParserUtil.parseIndex(argMultimap.getValue(PREFIX_EVENT_STAFF).get()));
            }
        } catch (ParseException e) {
            throw new ParseException("Invalid staff index format!", e);
        }

        try {
            if (argMultimap.getValue(PREFIX_EVENT_EXTERNAL).isPresent()) {
                externalIndex = Optional.of(ParserUtil.parseIndex(argMultimap.getValue(PREFIX_EVENT_EXTERNAL).get()));
            }
        } catch (ParseException e) {
            throw new ParseException("Invalid external index format!", e);
        }

        return new DeleteEventMemberCommand(eventIndex, studentIndex, staffIndex, externalIndex);
    }
}

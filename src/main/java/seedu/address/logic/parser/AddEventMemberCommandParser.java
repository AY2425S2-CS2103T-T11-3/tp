package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX;
import static seedu.address.logic.Messages.MESSAGE_INVALID_EXTERNAL_PARTY_DISPLAYED_INDEX;
import static seedu.address.logic.Messages.MESSAGE_INVALID_STAFF_DISPLAYED_INDEX;
import static seedu.address.logic.Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX;
import static seedu.address.logic.Messages.MESSAGE_MISSING_EVENT_MEMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_EXTERNAL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_STAFF;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_STUDENT;

import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.event.AddEventMemberCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new {@code AddEventMemberCommand} object.
 */
public class AddEventMemberCommandParser implements Parser<AddEventMemberCommand> {

    @Override
    public AddEventMemberCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_EVENT_STUDENT, PREFIX_EVENT_STAFF,
                PREFIX_EVENT_EXTERNAL);


        Index eventIndex;
        Optional<Index> studentIndex = Optional.empty();
        Optional<Index> staffIndex = Optional.empty();
        Optional<Index> externalIndex = Optional.empty();

        try {
            eventIndex = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException e) {
            throw new ParseException(MESSAGE_INVALID_EVENT_DISPLAYED_INDEX + "\n"
                    + AddEventMemberCommand.MESSAGE_USAGE, e);
        }

        try {
            if (argMultimap.getValue(PREFIX_EVENT_STUDENT).isPresent()) {
                studentIndex = Optional.of(ParserUtil.parseIndex(argMultimap.getValue(PREFIX_EVENT_STUDENT).get()));
            }
        } catch (ParseException e) {
            throw new ParseException(MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX + "\n"
                    + AddEventMemberCommand.MESSAGE_USAGE, e);
        }

        try {
            if (argMultimap.getValue(PREFIX_EVENT_STAFF).isPresent()) {
                staffIndex = Optional.of(ParserUtil.parseIndex(argMultimap.getValue(PREFIX_EVENT_STAFF).get()));
            }
        } catch (ParseException e) {
            throw new ParseException(MESSAGE_INVALID_STAFF_DISPLAYED_INDEX + "\n"
                    + AddEventMemberCommand.MESSAGE_USAGE, e);
        }

        try {
            if (argMultimap.getValue(PREFIX_EVENT_EXTERNAL).isPresent()) {
                externalIndex = Optional.of(ParserUtil.parseIndex(argMultimap.getValue(PREFIX_EVENT_EXTERNAL).get()));
            }
        } catch (ParseException e) {
            throw new ParseException(MESSAGE_INVALID_EXTERNAL_PARTY_DISPLAYED_INDEX + "\n"
                    + AddEventMemberCommand.MESSAGE_USAGE, e);
        }

        // Ensure at least one member is specified
        if (studentIndex.isEmpty() && staffIndex.isEmpty() && externalIndex.isEmpty()) {
            throw new ParseException(MESSAGE_MISSING_EVENT_MEMBER + "\n" + AddEventMemberCommand.MESSAGE_USAGE);
        }

        return new AddEventMemberCommand(eventIndex, studentIndex, staffIndex, externalIndex);

    }
}

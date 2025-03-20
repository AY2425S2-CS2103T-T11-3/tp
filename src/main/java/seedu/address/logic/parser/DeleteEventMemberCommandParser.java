package seedu.address.logic.parser;


import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_STUDENT;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.event.DeleteEventMemberCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteEventMemberCommand object.
 */
public class DeleteEventMemberCommandParser implements Parser<DeleteEventMemberCommand> {

    @Override
    public DeleteEventMemberCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_EVENT_STUDENT);

        if (!argMultimap.getPreamble().isEmpty() && argMultimap.getValue(PREFIX_EVENT_STUDENT).isPresent()) {
            Index eventIndex = ParserUtil.parseIndex(argMultimap.getPreamble());
            Index studentIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_EVENT_STUDENT).get());

            return new DeleteEventMemberCommand(eventIndex, studentIndex);
        } else {
            throw new ParseException(String.format("Invalid command format!\n%1$s", DeleteEventMemberCommand.MESSAGE_USAGE));
        }
    }
}

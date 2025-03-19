package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteExternalPartyCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteExternalPartyCommand object
 */
public class DeleteExternalPartyCommandParser implements Parser<DeleteExternalPartyCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteExternalPartyCommand
     * and returns a DeleteExternalPartyCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteExternalPartyCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteExternalPartyCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteExternalPartyCommand.MESSAGE_USAGE), pe);
        }
    }
}

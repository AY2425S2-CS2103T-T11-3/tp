package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteExternalCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteExternalCommand object
 */
public class DeleteExternalCommandParser implements Parser<DeleteExternalCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteExternalCommand
     * and returns a DeleteExternalCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteExternalCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteExternalCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteExternalCommand.MESSAGE_USAGE), pe);
        }
    }
}
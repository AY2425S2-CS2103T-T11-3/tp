package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.event.ViewEventCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ViewEventCommand object.
 */
public class ViewEventCommandParser implements Parser<ViewEventCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ViewEventCommand
     * and returns a ViewEventCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public ViewEventCommand parse(String args) throws ParseException {
        try {
            Index eventIndex = ParserUtil.parseIndex(args.trim());
            return new ViewEventCommand(eventIndex);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format("Invalid command format!\n%1$s", ViewEventCommand.MESSAGE_USAGE), pe);
        }
    }
}

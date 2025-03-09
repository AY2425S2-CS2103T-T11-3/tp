package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;


import seedu.address.logic.commands.AddExternalCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;

/**
 * Parses input arguments and creates a new {@code AddExternalCommand} object
 */
public class AddExternalCommandParser implements Parser<AddExternalCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code AddExternalCommand}
     * and returns an {@code AddExternalCommand} object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public AddExternalCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_DESCRIPTION);

        if (argMultimap.getValue(PREFIX_NAME).isEmpty()
                || argMultimap.getValue(PREFIX_PHONE).isEmpty()
                || argMultimap.getValue(PREFIX_EMAIL).isEmpty()
                || argMultimap.getValue(PREFIX_DESCRIPTION).isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddExternalCommand.MESSAGE_USAGE));
        }

        Name name;
        Phone phone;
        Email email;
        String description;

        try {
            name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
            phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
            email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
            description = argMultimap.getValue(PREFIX_DESCRIPTION).get();
        } catch (IllegalArgumentException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddExternalCommand.MESSAGE_USAGE), e);
        }

        return new AddExternalCommand(name, email, phone, description);
    }
}

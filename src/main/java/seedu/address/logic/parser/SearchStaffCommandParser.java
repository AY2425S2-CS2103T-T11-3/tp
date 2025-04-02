package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BLOCK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESIGNATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMERGENCY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LEVEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.SearchStaffCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new SearchStaffCommand object
 */
public class SearchStaffCommandParser implements Parser<SearchStaffCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SearchStaffCommand
     * and returns a SearchStaffCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public SearchStaffCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(
                args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG,
                PREFIX_EMERGENCY, PREFIX_BLOCK, PREFIX_LEVEL, PREFIX_ROOM, PREFIX_DESIGNATION);

        // Check if at least one prefix is provided
        if (!isAnyPrefixPresent(argMultimap, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG,
                PREFIX_EMERGENCY, PREFIX_BLOCK, PREFIX_LEVEL, PREFIX_ROOM, PREFIX_DESIGNATION)) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SearchStaffCommand.MESSAGE_USAGE));
        }

        // Create a map of prefixes to their corresponding values
        Map<Prefix, String> searchCriteria = argMultimap.getPrefixValueMap().entrySet().stream()
                .filter(entry -> entry.getKey() != null && !entry.getValue().isBlank())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        return new SearchStaffCommand(searchCriteria);
    }

    /**
     * Returns true if at least one of the prefixes is present in the {@code ArgumentMultimap}.
     */
    private static boolean isAnyPrefixPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}

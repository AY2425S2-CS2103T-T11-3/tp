package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_EMPTY_FIELD_AFTER_PREFIX;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.SearchExternalPartyCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new SearchExternalPartyCommand object
 */
public class SearchExternalPartyCommandParser implements Parser<SearchExternalPartyCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the SearchExternalPartyCommand
     * and returns a SearchExternalPartyCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public SearchExternalPartyCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(
                args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_DESCRIPTION);

        // Check if at least one prefix is provided
        if (!isAnyPrefixPresent(argMultimap, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_DESCRIPTION)) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SearchExternalPartyCommand.MESSAGE_USAGE));
        }

        // Check for empty values after a prefix
        for (Prefix prefix : new Prefix[]{PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_DESCRIPTION}) {
            Optional<String> value = argMultimap.getValue(prefix);
            if (value.isPresent() && value.get().trim().isEmpty()) {
                throw new ParseException(MESSAGE_EMPTY_FIELD_AFTER_PREFIX);
            }
        }

        // Create a map of prefixes to their corresponding values
        Map<Prefix, String> searchCriteria = argMultimap.getPrefixValueMap().entrySet().stream()
                .filter(entry -> entry.getKey() != null && !entry.getValue().isBlank())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        return new SearchExternalPartyCommand(searchCriteria);
    }

    /**
     * Returns true if at least one of the prefixes is present in the {@code ArgumentMultimap}.
     */
    private static boolean isAnyPrefixPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}

package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_EXTERNAL_PARTY_LISTED_OVERVIEW;
import static seedu.address.logic.Messages.MESSAGE_NO_EXTERNAL_PARTY_FOUND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.Map;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.Model;
import seedu.address.model.person.ExternalPartyMatchesAttributesPredicate;

/**
 * Searches for all external parties in address book whose attributes match any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class SearchExternalPartyCommand extends Command {
    public static final String COMMAND_WORD = "search_ext";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Searches for all external parties whose "
            + "attributes match the specified keywords (case-insensitive) and displays them as a list "
            + "with index numbers.\n"
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_DESCRIPTION + "DESCRIPTION\n"
            + "At least one of the parameters must be provided.\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_PHONE + "91234567";

    private final ExternalPartyMatchesAttributesPredicate predicate;

    public SearchExternalPartyCommand(Map<Prefix, String> searchCriteria) {
        this.predicate = new ExternalPartyMatchesAttributesPredicate(searchCriteria);
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredExternalPartyList(predicate);
        int filteredListSize = model.getFilteredExternalPartyList().size();
        if (filteredListSize == 0) {
            return new CommandResult(String.format(MESSAGE_NO_EXTERNAL_PARTY_FOUND, predicate));
        } else {
            return new CommandResult(
                    String.format(MESSAGE_EXTERNAL_PARTY_LISTED_OVERVIEW, filteredListSize, predicate));
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SearchExternalPartyCommand)) {
            return false;
        }

        SearchExternalPartyCommand otherSearchExternalPartyCommand = (SearchExternalPartyCommand) other;
        return predicate.equals(otherSearchExternalPartyCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}

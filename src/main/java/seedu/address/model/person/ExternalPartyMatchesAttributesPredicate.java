package seedu.address.model.person;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import seedu.address.logic.parser.Prefix;

/**
 * Tests that a {@code ExternalParty}'s attributes match the specified criteria.
 */
public class ExternalPartyMatchesAttributesPredicate implements Predicate<ExternalParty> {
    private final Map<Prefix, String> searchCriteria;

    public ExternalPartyMatchesAttributesPredicate(Map<Prefix, String> searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    @Override
    public boolean test(ExternalParty externalParty) {
        return searchCriteria.entrySet().stream()
                .allMatch(entry -> {
                    Prefix prefix = entry.getKey();
                    String value = entry.getValue();
                    if (prefix.equals(PREFIX_NAME)) {
                        return externalParty.getName().fullName.equalsIgnoreCase(value);
                    } else if (prefix.equals(PREFIX_PHONE)) {
                        return externalParty.getPhone().value.equalsIgnoreCase(value);
                    } else if (prefix.equals(PREFIX_EMAIL)) {
                        return externalParty.getEmail().value.equalsIgnoreCase(value);
                    } else if (prefix.equals(PREFIX_DESCRIPTION)) {
                        return externalParty.getDescription().value.equalsIgnoreCase(value);
                    } else {
                        return false;
                    }
                });
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ExternalPartyMatchesAttributesPredicate)) {
            return false;
        }

        ExternalPartyMatchesAttributesPredicate otherExternalPartyMatchesAttributesPredicate =
                (ExternalPartyMatchesAttributesPredicate) other;
        return searchCriteria.equals(otherExternalPartyMatchesAttributesPredicate.searchCriteria);
    }

    @Override
    public String toString() {
        return searchCriteria.entrySet().stream()
                .map(entry -> entry.getKey().getPrefix() + entry.getValue())
                .collect(Collectors.joining(", "));
    }
}

package seedu.address.model.person;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BLOCK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESIGNATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMERGENCY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LEVEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM;

import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import seedu.address.logic.parser.Prefix;

/**
 * Tests that a {@code Staff}'s attributes match the specified criteria.
 */
public class StaffMatchesAttributesPredicate implements Predicate<Staff> {
    private final Map<Prefix, String> searchCriteria;

    public StaffMatchesAttributesPredicate(Map<Prefix, String> searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    @Override
    public boolean test(Staff staff) {
        return searchCriteria.entrySet().stream()
                .allMatch(entry -> {
                    Prefix prefix = entry.getKey();
                    String value = entry.getValue();
                    if (prefix.equals(PREFIX_NAME)) {
                        return staff.getName().fullName.equalsIgnoreCase(value);
                    } else if (prefix.equals(PREFIX_PHONE)) {
                        return staff.getPhone().value.equalsIgnoreCase(value);
                    } else if (prefix.equals(PREFIX_EMAIL)) {
                        return staff.getEmail().value.equalsIgnoreCase(value);
                    } else if (prefix.equals(PREFIX_ADDRESS)) {
                        return staff.getAddress().value.equalsIgnoreCase(value);
                    } else if (prefix.equals(PREFIX_EMERGENCY)) {
                        return staff.getEmergency().value.equalsIgnoreCase(value);
                    } else if (prefix.equals(PREFIX_BLOCK)) {
                        return staff.getBlock().value.equalsIgnoreCase(value);
                    } else if (prefix.equals(PREFIX_LEVEL)) {
                        try {
                            int level = Integer.parseInt(value);
                            return staff.getLevel().value == level;
                        } catch (NumberFormatException e) {
                            return false;
                        }
                    } else if (prefix.equals(PREFIX_ROOM)) {
                        try {
                            int room = Integer.parseInt(value);
                            return staff.getRoom().value == room;
                        } catch (NumberFormatException e) {
                            return false;
                        }
                    } else if (prefix.equals(PREFIX_DESIGNATION)) {
                        return staff.getStaffDesignation().toString().equalsIgnoreCase(value);
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
        if (!(other instanceof StaffMatchesAttributesPredicate)) {
            return false;
        }

        StaffMatchesAttributesPredicate otherStaffMatchesAttributesPredicate = (StaffMatchesAttributesPredicate) other;
        return searchCriteria.equals(otherStaffMatchesAttributesPredicate.searchCriteria);
    }

    @Override
    public String toString() {
        return searchCriteria.entrySet().stream()
                .map(entry -> entry.getKey().getPrefix() + entry.getValue())
                .collect(Collectors.joining(", "));
    }
}

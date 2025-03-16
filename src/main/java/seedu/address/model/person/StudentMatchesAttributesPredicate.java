package seedu.address.model.person;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BLOCK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESIGNATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMERGENCY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LEVEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MATRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM;

import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import seedu.address.logic.parser.Prefix;

/**
 * Tests that a {@code Student}'s attributes match the specified criteria.
 */
public class StudentMatchesAttributesPredicate implements Predicate<Student> {
    private final Map<Prefix, String> searchCriteria;

    public StudentMatchesAttributesPredicate(Map<Prefix, String> searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    @Override
    public boolean test(Student student) {
        return searchCriteria.entrySet().stream()
                .allMatch(entry -> {
                    Prefix prefix = entry.getKey();
                    String value = entry.getValue();
                    if (prefix.equals(PREFIX_NAME)) {
                        return student.getName().fullName.equalsIgnoreCase(value);
                    } else if (prefix.equals(PREFIX_MATRIC)) {
                        return student.getMatric().value.equalsIgnoreCase(value);
                    } else if (prefix.equals(PREFIX_PHONE)) {
                        return student.getPhone().value.equalsIgnoreCase(value);
                    } else if (prefix.equals(PREFIX_EMAIL)) {
                        return student.getEmail().value.equalsIgnoreCase(value);
                    } else if (prefix.equals(PREFIX_ADDRESS)) {
                        return student.getAddress().value.equalsIgnoreCase(value);
                    } else if (prefix.equals(PREFIX_EMERGENCY)) {
                        return student.getEmergency().value.equalsIgnoreCase(value);
                    } else if (prefix.equals(PREFIX_BLOCK)) {
                        return student.getBlock().value.equalsIgnoreCase(value);
                    } else if (prefix.equals(PREFIX_LEVEL)) {
                        try {
                            int level = Integer.parseInt(value);
                            return student.getLevel().value == level;
                        } catch (NumberFormatException e) {
                            return false;
                        }
                    } else if (prefix.equals(PREFIX_ROOM)) {
                        try {
                            int room = Integer.parseInt(value);
                            return student.getRoom().value == room;
                        } catch (NumberFormatException e) {
                            return false;
                        }
                    } else if (prefix.equals(PREFIX_DESIGNATION)) {
                        return student.getStudentDesignation().toString().equalsIgnoreCase(value);
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
        if (!(other instanceof StudentMatchesAttributesPredicate)) {
            return false;
        }

        StudentMatchesAttributesPredicate otherStudentMatchesAttributesPredicate = (StudentMatchesAttributesPredicate) other;
        return searchCriteria.equals(otherStudentMatchesAttributesPredicate.searchCriteria);
    }

    @Override
    public String toString() {
        return searchCriteria.entrySet().stream()
                .map(entry -> entry.getKey().getPrefix() + entry.getValue())
                .collect(Collectors.joining(", "));
    }
}

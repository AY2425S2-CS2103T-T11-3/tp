package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the role of a Student, in ResiConnect, in their NUS residence
 */
public class StudentDesignation {
    enum Role {
        RESIDENT, BLOCK_HEAD, JCRC_MEMBER;
    }

    public static final String MESSAGE_CONSTRAINTS =
            "Designation should only be an integer from 0 to 2, and it should not be blank. "
                    + "0 to 2 represents Resident, Block Head and JCRC Member respectively";

    public static final String VALIDATION_REGEX = "[0-2]";

    public final StudentDesignation.Role value;

    /**
     * Constructs a {@code StudentDesignation}.
     *
     * @param studentDesignation A valid designation.
     */
    public StudentDesignation(String studentDesignation) {
        requireNonNull(studentDesignation);
        checkArgument(isValidStudentDesignation(studentDesignation), MESSAGE_CONSTRAINTS);
        value = StudentDesignation.Role.values()[Integer.parseInt(studentDesignation)];
    }

    /**
     * Returns true if a given string is a valid student designation.
     */
    public static boolean isValidStudentDesignation(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public int getOrdinalDesignation() {
        return this.value.ordinal();
    }

    @Override
    public String toString() {
        return value.name();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StudentDesignation)) {
            return false;
        }

        StudentDesignation otherStudentDesignation = (StudentDesignation) other;
        return value.equals(otherStudentDesignation.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}

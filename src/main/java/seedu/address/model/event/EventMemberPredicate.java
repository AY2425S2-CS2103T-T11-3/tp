package seedu.address.model.event;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.person.Person;
import seedu.address.model.person.Student;
import seedu.address.model.person.Staff;
import seedu.address.model.person.ExternalParty;
import seedu.address.model.person.StudentMatchesAttributesPredicate;
import seedu.address.model.person.StaffMatchesAttributesPredicate;
import seedu.address.model.person.ExternalPartyMatchesAttributesPredicate;

import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * Tests that a {@code Event}'s {@code Person} contains the member matching the searching criteria.
 */
public class EventMemberPredicate implements Predicate<Person> {
    private final String memberType;
    private final Map<Prefix, String> searchCriteria;

    /**
     * Creates an EventMemberPredicate to test if a {@code Person} matches the searching criteria.
     *
     * @param memberType the type of the member to search for
     * @param searchCriteria the searching criteria
     */
    public EventMemberPredicate(String memberType, Map<Prefix, String> searchCriteria) {
        this.memberType = memberType;
        this.searchCriteria = searchCriteria;
    }

    @Override
    public boolean test(Person person) {
        switch (memberType.toLowerCase()) {
        case "stu":
            if (person instanceof Student) {
                StudentMatchesAttributesPredicate studentPredicate =
                        new StudentMatchesAttributesPredicate(searchCriteria);
                return studentPredicate.test((Student) person);
            }
            break;
        case "staff":
            if (person instanceof Staff) {
                StaffMatchesAttributesPredicate staffPredicate =
                        new StaffMatchesAttributesPredicate(searchCriteria);
                return staffPredicate.test((Staff) person);
            }
            break;
        case "ext":
            if (person instanceof ExternalParty) {
                ExternalPartyMatchesAttributesPredicate extPredicate =
                        new ExternalPartyMatchesAttributesPredicate(searchCriteria);
                return extPredicate.test((ExternalParty) person);
            }
            break;
        default:
            break;
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof EventMemberPredicate)) {
            return false;
        }
        EventMemberPredicate otherPredicate = (EventMemberPredicate) other;
        return Objects.equals(memberType, otherPredicate.memberType)
                && Objects.equals(searchCriteria, otherPredicate.searchCriteria);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberType, searchCriteria);
    }
}

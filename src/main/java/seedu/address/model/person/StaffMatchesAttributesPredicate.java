package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;
public class StaffMatchesAttributesPredicate implements Predicate<Staff> {
    private final List<String> keywords;

    public StaffMatchesAttributesPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Staff staff) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(staff.getName().fullName, keyword));
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
        return keywords.equals(otherStaffMatchesAttributesPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}

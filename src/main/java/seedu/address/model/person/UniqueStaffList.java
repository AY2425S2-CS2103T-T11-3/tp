package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;

/**
 * A list of staff that enforces uniqueness between its elements and does not allow nulls.
 * A staff is considered unique by comparing using {@code Person#isSamePerson(Person)}. As such, adding and updating of
 * staff uses Person#isSamePerson(Person) for equality so as to ensure that the staff being added or updated is
 * unique in terms of identity in the UniqueStaffList. However, the removal of a staff uses Person#equals(Object) so
 * as to ensure that the person with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Person#isSamePerson(Person)
 */
public class UniqueStaffList implements Iterable<Staff> {

    private final ObservableList<Staff> internalList = FXCollections.observableArrayList();
    private final ObservableList<Staff> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent staff as the given argument.
     */
    public boolean contains(Staff toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSamePerson);
    }

    /**
     * Adds a staff to the list.
     * The staff must not already exist in the list.
     */
    public void add(Staff toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePersonException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the staff {@code target} in the list with {@code editedStaff}.
     * {@code target} must exist in the list.
     * The staff identity of {@code editedStaff} must not be the same as another existing staff in the list.
     */
    public void setStaff(Staff target, Staff editedStaff) {
        requireAllNonNull(target, editedStaff);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PersonNotFoundException();
        }

        if (!target.isSamePerson(editedStaff) && contains(editedStaff)) {
            throw new DuplicatePersonException();
        }

        internalList.set(index, editedStaff);
    }

    /**
     * Removes the equivalent staff from the list.
     * The staff must exist in the list.
     */
    public void remove(Staff toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new PersonNotFoundException();
        }
    }

    public void setStaffs(UniqueStaffList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code staff}.
     * {@code staff} must not contain duplicate persons.
     */
    public void setStaffs(List<Staff> staff) {
        requireAllNonNull(staff);
        if (!staffAreUnique(staff)) {
            throw new DuplicatePersonException();
        }

        internalList.setAll(staff);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Staff> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Staff> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UniqueStaffList)) {
            return false;
        }

        UniqueStaffList otherUniqueStaffList = (UniqueStaffList) other;

        // Check if both lists have the same size
        if (this.internalList.size() != otherUniqueStaffList.internalList.size()) {
            return false; // If they have different sizes, they are not equal
        }

        // Check if each staff in the first list is the same as the corresponding staff in the second list
        for (int i = 0; i < this.internalList.size(); i++) {
            if (!this.internalList.get(i).equals(otherUniqueStaffList.internalList.get(i))) {
                return false; // If any staff does not match, return false
            }
        }

        // If all checks pass, return true (the lists have the same contents)
        return true;
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    @Override
    public String toString() {
        return internalList.toString();
    }

    /**
     * Returns true if {@code staff} contains only unique staff.
     */
    private boolean staffAreUnique(List<Staff> staff) {
        for (int i = 0; i < staff.size() - 1; i++) {
            for (int j = i + 1; j < staff.size(); j++) {
                if (staff.get(i).isSamePerson(staff.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

}

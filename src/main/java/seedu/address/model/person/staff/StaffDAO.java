package main.java.seedu.address.model.person.staff;

import seedu.address.model.person.Staff;
import java.util.List;

public interface StaffDAO {
    void createStaff(Staff staff);
    Staff getStaffById(int id);
    List<Staff> getAllStaff();
    void updateStaff(Staff staff);
    void deleteStaff(int id);
}

package main.java.seedu.address.model.person.staff;

import seedu.address.model.person.Staff;
import seedu.address.model.dao.StaffDAO;

import java.util.List;

public class StaffService {
    private final StaffDAO staffDAO;

    public StaffService(StaffDAO staffDAO) {
        this.staffDAO = staffDAO;
    }

    public void addStaff(Staff staff) {
        // Validation logic can be added here
        staffDAO.createStaff(staff);
    }

    public Staff getStaffById(int id) {
        return staffDAO.getStaffById(id);
    }

    public List<Staff> getAllStaff() {
        return staffDAO.getAllStaff();
    }

    public void updateStaff(Staff staff) {
        staffDAO.updateStaff(staff);
    }

    public void deleteStaff(int id) {
        staffDAO.deleteStaff(id);
    }
}

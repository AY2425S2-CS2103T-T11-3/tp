package main.java.seedu.address.model.person.staff;

import seedu.address.model.person.Staff;
import seedu.address.model.service.StaffService;

public class StaffController {
    private final StaffService staffService;

    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    public void handleCreateStaff(Staff staff) {
        staffService.addStaff(staff);
    }

    public void handleViewStaff(int id) {
        Staff staff = staffService.getStaffById(id);
        System.out.println(staff);
    }

    public void handleDeleteStaff(int id) {
        staffService.deleteStaff(id);
    }
}

package main.java.seedu.address.model.person.staff;

import seedu.address.model.person.Staff;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StaffDAOImpl implements StaffDAO {
    private final Connection connection;

    public StaffDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void createStaff(Staff staff) {
        String sql = "INSERT INTO Staff (name, phone, email, address, emergency, block, level, room, designation) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, staff.getName().toString());
            stmt.setString(2, staff.getPhone().toString());
            stmt.setString(3, staff.getEmail().toString());
            stmt.setString(4, staff.getAddress().toString());
            stmt.setString(5, staff.getEmergency().toString());
            stmt.setString(6, staff.getBlock().toString());
            stmt.setString(7, staff.getLevel().toString());
            stmt.setString(8, staff.getRoom().toString());
            stmt.setString(9, staff.getDesignation().toString());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Consider better exception handling
        }
    }

    @Override
    public Staff getStaffById(int id) {
        String sql = "SELECT * FROM Staff WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                // Map ResultSet to a Staff object
                return new Staff(
                    // Example: Parse ResultSet here
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Staff> getAllStaff() {
        List<Staff> staffList = new ArrayList<>();
        String sql = "SELECT * FROM Staff";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                // Map ResultSet to Staff and add to the list
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return staffList;
    }

    @Override
    public void updateStaff(Staff staff) {
        String sql = "UPDATE Staff SET phone = ?, email = ?, address = ?, emergency = ?, block = ?, level = ?, room = ?, designation = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, staff.getPhone().toString());
            stmt.setString(2, staff.getEmail().toString());
            stmt.setString(3, staff.getAddress().toString());
            stmt.setString(4, staff.getEmergency().toString());
            stmt.setString(5, staff.getBlock().toString());
            stmt.setString(6, staff.getLevel().toString());
            stmt.setString(7, staff.getRoom().toString());
            stmt.setString(8, staff.getDesignation().toString());
            stmt.setInt(9, staff.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteStaff(int id) {
        String sql = "DELETE FROM Staff WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

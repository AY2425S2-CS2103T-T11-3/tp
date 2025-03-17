package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Staff;

/**
 * A UI component that displays information of a {@code Staff}.
 */
public class StaffCard extends UiPart<Region> {

    private static final String FXML = "StaffListCard.fxml";

    public final Staff staff;

    @FXML
    private final Label staffLabel = new Label("Staff");
    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private FlowPane tags;
    @FXML
    private Label emergency;
    @FXML
    private Label block;
    @FXML
    private Label level;
    @FXML
    private Label room;
    @FXML
    private Label designation;

    /**
     * Creates a {@code StaffCard} with the given {@code Staff} and index to display.
     */
    public StaffCard(Staff staff, int displayedIndex) {
        super(FXML);
        this.staff = staff;

        id.setText(displayedIndex + ". ");
        name.setText(staff.getName().fullName);
        phone.setText(staff.getPhone().value);
        address.setText(staff.getAddress().value);
        email.setText(staff.getEmail().value);
        staff.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        emergency.setText(staff.getEmergency().value);
        block.setText("Block " + staff.getBlock().value);
        level.setText("Level " + staff.getLevel().value);
        room.setText("Room " + staff.getRoom().value);
        designation.setText(staff.getStaffDesignation().toString());
    }
}

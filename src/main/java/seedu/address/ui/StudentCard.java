package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Student;

/**
 * A UI component that displays information of a {@code Student}.
 */
public class StudentCard extends UiPart<Region> {

    private static final String FXML = "StudentListCard.fxml";

    public final Student student;

    @javafx.fxml.FXML
    private final Label studentLabel = new Label("Student");
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
    private Label matric;
    @FXML
    private Label emergency;
    @FXML
    private Label block;
    @FXML
    private Label level;
    @FXML
    private Label room;
    @FXML
    private Label studentDesignation;

    /**
     * Creates a {@code StudentCard} with the given {@code Student} and index to display.
     */
    public StudentCard(Student student, int displayedIndex) {
        super(FXML);
        this.student = student;

        id.setText(displayedIndex + ". ");
        name.setText(student.getName().fullName);
        phone.setText(student.getPhone().value);
        address.setText(student.getAddress().value);
        email.setText(student.getEmail().value);
        student.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        matric.setText(student.getMatric().value);
        emergency.setText(student.getEmergency().value);
        block.setText(student.getBlock().value);
        level.setText(String.valueOf(student.getLevel().value));
        room.setText(String.valueOf(student.getRoom().value));
        studentDesignation.setText(student.getStudentDesignation().toString());
    }
}

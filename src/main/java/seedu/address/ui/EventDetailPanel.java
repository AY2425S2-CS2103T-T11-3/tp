package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import seedu.address.model.event.Event;

public class EventDetailsPanel extends UiPart<VBox> {

    private static final String FXML = "EventDetailsPanel.fxml";

    @FXML
    private VBox eventDetailsContainer;

    @FXML
    private Label eventTitleLabel;

    @FXML
    private Label eventDetailsLabel;

    public EventDetailsPanel() {
        super(FXML);
    }

    /**
     * Updates the event details panel with the selected event's data.
     */
    public void setEventDetails(Event event) {
        if (event == null) {
            eventTitleLabel.setText("No Event Selected");
            eventDetailsLabel.setText("");
        } else {
            eventTitleLabel.setText("Event: " + event.getEventName().fullEventName);
            eventDetailsLabel.setText(String.format(
                    "Start Time: %s\nEnd Time: %s\nParticipants: %d",
                    event.getEventStartTime(), event.getEventEndTime(), event.getStudents().size()
            ));
        }
    }
}

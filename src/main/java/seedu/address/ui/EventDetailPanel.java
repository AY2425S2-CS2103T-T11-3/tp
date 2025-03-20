package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.commons.core.index.Index;
import seedu.address.model.event.Event;

/**
 * Panel for displaying event details and student list.
 */
public class EventDetailPanel extends UiPart<Region> {
    private static final String FXML = "EventDetailPanel.fxml";

    @FXML
    private VBox eventDetailPlaceholder;

    @FXML
    private VBox studentListPlaceholder;

    @FXML
    private VBox staffListPlaceholder;

    @FXML
    private VBox externalListPlaceholder;

    private final EventCard eventCard;
    private StudentListPanel studentListPanel;
    private StaffListPanel staffListPanel;
    private ExternalPartyListPanel externalPartyListPanel;

    /**
     * Creates an EventDetailPanel and displays the given event details.
     *
     * @param event The event to display.
     * @param eventIndex The index of the event from the list.
     */
    public EventDetailPanel(Event event, Index eventIndex) {
        super(FXML);

        // Use EventCard for displaying event details
        eventCard = new EventCard(event, eventIndex.getOneBased());

        // Add it to the UI placeholder
        eventDetailPlaceholder.getChildren().add(eventCard.getRoot());

        // Create and display StudentListPanel
        studentListPanel = new StudentListPanel(event.getStudents());
        studentListPlaceholder.getChildren().add(studentListPanel.getRoot());

        // Display Staff List
        staffListPanel = new StaffListPanel(event.getStaff());
        staffListPlaceholder.getChildren().add(staffListPanel.getRoot());

        // Display External Party List
        //externalPartyListPanel = new ExternalPartyListPanel(event.getExternalParties());
        //externalListPlaceholder.getChildren().add(externalPartyListPanel.getRoot());


    }


}

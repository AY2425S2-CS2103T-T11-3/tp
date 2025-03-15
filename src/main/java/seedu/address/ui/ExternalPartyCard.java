package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.ExternalParty;

/**
 * A UI component that displays information of a {@code ExternalParty}.
 */
public class ExternalPartyCard extends UiPart<Region> {

    private static final String FXML = "ExternalParty.fxml";

    public final ExternalParty externalParty;

    @FXML
    private final Label externalPartyLabel = new Label("ExternalParty");
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
    private Label description;

    /**
     * Creates a {@code ExternalParty} with the given {@code ExternalParty} and index to display.
     */
    public ExternalPartyCard(ExternalParty externalParty, int displayedIndex) {
        super(FXML);
        this.externalParty = externalParty;

        id.setText(displayedIndex + ". ");
        name.setText(externalParty.getName().fullName);
        phone.setText(externalParty.getPhone().value);
        email.setText(externalParty.getEmail().value);
        description.setText(externalParty.getDescription().value);
    }

}

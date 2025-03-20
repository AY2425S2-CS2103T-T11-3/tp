package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.ExternalParty;

/**
 * Panel containing the list of ExternalParty
 */
public class ExternalPartyListPanel extends UiPart<Region> {
    private static final String FXML = "ExternalPartyListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ExternalPartyListPanel.class);

    @FXML
    private ListView<ExternalParty> externalPartyListView;

    /**
     * Creates a {@code ExternalPartyListPanel} with the given {@code ObservableList}.
     */
    public ExternalPartyListPanel(ObservableList<ExternalParty> externalPartyList) {
        super(FXML);
        externalPartyListView.setItems(externalPartyList);
        externalPartyListView.setCellFactory(listView -> new ExternalPartyListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code ExternalParty} using a {@code ExternalPartyCard}
     */
    class ExternalPartyListViewCell extends ListCell<ExternalParty> {
        @Override
        protected void updateItem(ExternalParty externalParty, boolean empty) {
            super.updateItem(externalParty, empty);

            if (empty || externalParty == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ExternalPartyCard(externalParty, getIndex() + 1).getRoot());
            }
        }
    }
}

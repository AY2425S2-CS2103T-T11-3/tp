package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_EXTERNAL_PARTY_DISPLAYED_INDEX;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.ExternalParty;

/**
 * Deletes an external party identified using it's displayed index from the address book.
 */
public class DeleteExternalPartyCommand extends Command {
    public static final String COMMAND_WORD = "delete_ext";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the external party identified by the index number used in the displayed external party list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_EXTERNAL_PARTY_SUCCESS = "Deleted External Party: %1$s";

    private final Index targetIndex;

    public DeleteExternalPartyCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<ExternalParty> lastShownList = model.getFilteredExternalPartyList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(MESSAGE_INVALID_EXTERNAL_PARTY_DISPLAYED_INDEX);
        }

        ExternalParty externalPartyToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteExternalParty(externalPartyToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_EXTERNAL_PARTY_SUCCESS,
                Messages.format(externalPartyToDelete)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteExternalPartyCommand)) {
            return false;
        }

        DeleteExternalPartyCommand otherDeleteExternalPartyCommand = (DeleteExternalPartyCommand) other;
        return targetIndex.equals(otherDeleteExternalPartyCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}

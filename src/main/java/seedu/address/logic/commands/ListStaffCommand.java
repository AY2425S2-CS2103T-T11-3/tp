package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STAFF;

import seedu.address.model.Model;

public class ListStaffCommand extends Command {

    public static final String COMMAND_WORD = "list_staff";

    public static final String MESSAGE_SUCCESS = "Listed all staff";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredStaffList(PREDICATE_SHOW_ALL_STAFF);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

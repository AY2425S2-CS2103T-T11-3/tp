package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_NO_STUDENT_FOUND;
import static seedu.address.logic.Messages.MESSAGE_STUDENT_LISTED_OVERVIEW;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BLOCK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESIGNATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMERGENCY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LEVEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MATRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Map;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.Model;
import seedu.address.model.person.StudentMatchesAttributesPredicate;

/**
 * Searches for all students in address book whose attributes match any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class SearchStudentCommand extends Command {
    public static final String COMMAND_WORD = "search_stu";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Searches for all students whose attributes match "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_MATRIC + "MATRIC "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + PREFIX_TAG + "TAG "
            + PREFIX_EMERGENCY + "EMERGENCY CONTACT "
            + PREFIX_BLOCK + "BLOCK "
            + PREFIX_LEVEL + "LEVEL "
            + PREFIX_ROOM + "ROOM "
            + PREFIX_DESIGNATION + "DESIGNATION\n"
            + "At least one of the parameters must be provided.\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_PHONE + "91234567";

    private final StudentMatchesAttributesPredicate predicate;

    public SearchStudentCommand(Map<Prefix, String> searchCriteria) {
        this.predicate = new StudentMatchesAttributesPredicate(searchCriteria);
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredStudentList(predicate);
        int filteredListSize = model.getFilteredStudentList().size();
        if (filteredListSize == 0) {
            return new CommandResult(String.format(MESSAGE_NO_STUDENT_FOUND, predicate));
        } else {
            return new CommandResult(
                    String.format(MESSAGE_STUDENT_LISTED_OVERVIEW, filteredListSize));
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SearchStudentCommand)) {
            return false;
        }

        SearchStudentCommand otherSearchStudentCommand = (SearchStudentCommand) other;
        return predicate.equals(otherSearchStudentCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}

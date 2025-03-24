package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddExternalCommand;
import seedu.address.logic.commands.AddStaffCommand;
import seedu.address.logic.commands.AddStudentCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteExternalPartyCommand;
import seedu.address.logic.commands.DeleteStaffCommand;
import seedu.address.logic.commands.DeleteStudentCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.ListExternalPartyCommand;
import seedu.address.logic.commands.ListStaffCommand;
import seedu.address.logic.commands.ListStudentCommand;
import seedu.address.logic.commands.SearchExternalPartyCommand;
import seedu.address.logic.commands.SearchStaffCommand;
import seedu.address.logic.commands.SearchStudentCommand;
import seedu.address.logic.commands.event.AddEventCommand;
import seedu.address.logic.commands.event.AddEventMemberCommand;
import seedu.address.logic.commands.event.DeleteEventCommand;
import seedu.address.logic.commands.event.DeleteEventMemberCommand;
import seedu.address.logic.commands.event.ListEventCommand;
import seedu.address.logic.commands.event.SearchEventCommand;
import seedu.address.logic.commands.event.ViewEventCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class AddressBookParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    private static final Logger logger = LogsCenter.getLogger(AddressBookParser.class);

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        // Note to developers: Change the log level in config.json to enable lower level (i.e., FINE, FINER and lower)
        // log messages such as the one below.
        // Lower level log messages are used sparingly to minimize noise in the code.
        logger.fine("Command word: " + commandWord + "; Arguments: " + arguments);

        switch (commandWord) {

        case AddStaffCommand.COMMAND_WORD:
            return new AddStaffCommandParser().parse(arguments);

        case AddStudentCommand.COMMAND_WORD:
            return new AddStudentCommandParser().parse(arguments);

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case AddEventCommand.COMMAND_WORD:
            return new AddEventCommandParser().parse(arguments);

        case DeleteEventCommand.COMMAND_WORD:
            return new DeleteEventCommandParser().parse(arguments);

        case ListEventCommand.COMMAND_WORD:
            return new ListEventCommand();

        case AddExternalCommand.COMMAND_WORD:
            return new AddExternalCommandParser().parse(arguments);

        case ListStaffCommand.COMMAND_WORD:
            return new ListStaffCommand();

        case ListExternalPartyCommand.COMMAND_WORD:
            return new ListExternalPartyCommand();

        case ListStudentCommand.COMMAND_WORD:
            return new ListStudentCommand();

        case SearchEventCommand.COMMAND_WORD:
            return new SearchEventCommandParser().parse(arguments);

        case SearchStaffCommand.COMMAND_WORD:
            return new SearchStaffCommandParser().parse(arguments);

        case SearchStudentCommand.COMMAND_WORD:
            return new SearchStudentCommandParser().parse(arguments);

        case SearchExternalPartyCommand.COMMAND_WORD:
            return new SearchExternalPartyCommandParser().parse(arguments);

        case DeleteStudentCommand.COMMAND_WORD:
            return new DeleteStudentCommandParser().parse(arguments);

        case DeleteStaffCommand.COMMAND_WORD:
            return new DeleteStaffCommandParser().parse(arguments);

        case DeleteExternalPartyCommand.COMMAND_WORD:
            return new DeleteExternalPartyCommandParser().parse(arguments);

        case ViewEventCommand.COMMAND_WORD:
            return new ViewEventCommandParser().parse(arguments);

        case AddEventMemberCommand.COMMAND_WORD:
            return new AddEventMemberCommandParser().parse(arguments);

        case DeleteEventMemberCommand.COMMAND_WORD:
            return new DeleteEventMemberCommandParser().parse(arguments);

        default:
            logger.finer("This user input caused a ParseException: " + userInput);
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}

package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
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

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddStudentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Block;
import seedu.address.model.person.Email;
import seedu.address.model.person.Level;
import seedu.address.model.person.Matric;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Room;
import seedu.address.model.person.Student;
import seedu.address.model.person.StudentDesignation;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddStudentCommand object
 */
public class AddStudentCommandParser implements Parser<AddStudentCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddStudentCommand
     * and returns an AddStudentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public AddStudentCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_MATRIC, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                        PREFIX_TAG, PREFIX_EMERGENCY, PREFIX_BLOCK, PREFIX_LEVEL, PREFIX_ROOM, PREFIX_DESIGNATION);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_MATRIC, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                PREFIX_EMERGENCY, PREFIX_BLOCK, PREFIX_LEVEL, PREFIX_ROOM)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddStudentCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_MATRIC, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                PREFIX_EMERGENCY, PREFIX_BLOCK, PREFIX_LEVEL, PREFIX_ROOM, PREFIX_DESIGNATION);
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Matric matric = ParserUtil.parseMatric(argMultimap.getValue(PREFIX_MATRIC).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        Phone emergency = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_EMERGENCY).get());
        Block block = ParserUtil.parseBlock(argMultimap.getValue(PREFIX_BLOCK).get());
        Level level = ParserUtil.parseLevel(argMultimap.getValue(PREFIX_LEVEL).get());
        Room room = ParserUtil.parseRoom(argMultimap.getValue(PREFIX_ROOM).get());

        StudentDesignation studentDesignation;
        if (arePrefixesPresent(argMultimap, PREFIX_DESIGNATION)) {
            studentDesignation = ParserUtil.parseStudentDesignation(argMultimap.getValue(PREFIX_DESIGNATION).get());
        } else {
            studentDesignation = ParserUtil.parseStudentDesignation("0");
        }

        Student student = new Student(name, matric, phone, email, address, tagList, emergency, block, level, room,
                studentDesignation);

        return new AddStudentCommand(student);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}

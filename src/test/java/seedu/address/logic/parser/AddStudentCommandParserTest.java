package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MATRIC_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.MATRIC_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddStudentCommand;
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
import seedu.address.testutil.StudentBuilder;

public class AddStudentCommandParserTest {
    private AddStudentCommandParser parser = new AddStudentCommandParser();

    @Test
    public void parse_allFieldsPresentExceptDesignation_success() {
        Student expectedStudent = new StudentBuilder().build();

        assertParseSuccess(parser,
                " name/Amy Bee matric/A0234567B phone/85355255 email/amy@gmail.com a/123, "
                        + "Jurong West Ave 6, #08-111 emergency/91234567 block/A level/7 room/5",
                new AddStudentCommand(expectedStudent));
    }

    @Test
    public void parse_allFieldsPresentWithDesignation_success() {
        Student expectedStudent = new StudentBuilder().build();

        assertParseSuccess(parser,
                " name/Amy Bee matric/A0234567B phone/85355255 email/amy@gmail.com a/123, "
                        + "Jurong West Ave 6, #08-111 emergency/91234567 block/A level/7 room/5 "
                        + "designation/0",
                new AddStudentCommand(expectedStudent));
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + " matric/A0234567B phone/85355255 "
                + "email/amy@gmail.com a/123, "
                + "Jurong West Ave 6, #08-111 emergency/91234567 block/A level/7 room/5 "
                + "designation/0", Name.MESSAGE_CONSTRAINTS);

        // invalid matric
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_MATRIC_DESC + " phone/85355255 "
                + "email/amy@gmail.com a/123, "
                + "Jurong West Ave 6, #08-111 emergency/91234567 block/A level/7 room/5 "
                + "designation/0", Matric.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + MATRIC_DESC_BOB + INVALID_PHONE_DESC
                + " email/amy@gmail.com a/123, "
                + "Jurong West Ave 6, #08-111 emergency/91234567 block/A level/7 room/5 "
                + "designation/0", Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + MATRIC_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC
                + " a/123, "
                + "Jurong West Ave 6, #08-111 emergency/91234567 block/A level/7 room/5 "
                + "designation/0", Email.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_BOB + MATRIC_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + INVALID_ADDRESS_DESC
                + " emergency/91234567 block/A level/7 room/5 "
                + "designation/0", Address.MESSAGE_CONSTRAINTS);

        // invalid emergency
        assertParseFailure(parser, NAME_DESC_BOB + MATRIC_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB
                + " emergency/911a block/A level/7 room/5 designation/0", Phone.MESSAGE_CONSTRAINTS);

        // invalid block
        assertParseFailure(parser, NAME_DESC_BOB + MATRIC_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB
                + " emergency/91234567 block/? level/7 room/5 designation/0", Block.MESSAGE_CONSTRAINTS);

        // invalid level
        assertParseFailure(parser, NAME_DESC_BOB + MATRIC_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB
                + " mergency/91234567 block/A  level/1000 room/5 designation/0", Level.MESSAGE_CONSTRAINTS);

        // invalid room
        assertParseFailure(parser, NAME_DESC_BOB + MATRIC_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB
                + " emergency/91234567 block/A level/10 room/ABC designation/0", Room.MESSAGE_CONSTRAINTS);

        // invalid designation
        assertParseFailure(parser, NAME_DESC_BOB + MATRIC_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB
                + " emergency/91234567 block/A level/10 room/2 designation/5", StudentDesignation.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + MATRIC_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + INVALID_ADDRESS_DESC
                        + " emergency/91234567 block/A level/7 room/5 designation/0",
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddStudentCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser,
                " matric/A0234567B phone/85355255 email/amy@gmail.com a/123, "
                        + "Jurong West Ave 6, #08-111 emergency/91234567 block/A level/7 room/5",
                expectedMessage);

        // missing matric prefix
        assertParseFailure(parser,
                " name/Amy Bee phone/85355255 email/amy@gmail.com a/123, "
                        + "Jurong West Ave 6, #08-111 emergency/91234567 block/A level/7 room/5",
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser,
                " name/Amy Bee matric/A0234567B email/amy@gmail.com a/123, "
                        + "Jurong West Ave 6, #08-111 emergency/91234567 block/A level/7 room/5",
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser,
                " name/Amy Bee matric/A0234567B phone/85355255 a/123, "
                        + "Jurong West Ave 6, #08-111 emergency/91234567 block/A level/7 room/5",
                expectedMessage);

        // missing address prefix
        assertParseFailure(parser,
                " name/Amy Bee matric/A0234567B phone/85355255 email/amy@gmail.com "
                        + "emergency/91234567 block/A level/7 room/5",
                expectedMessage);

        // missing emergency prefix
        assertParseFailure(parser,
                " name/Amy Bee matric/A0234567B phone/85355255 email/amy@gmail.com a/123, "
                        + "Jurong West Ave 6, #08-111 block/A level/7 room/5",
                expectedMessage);

        // missing block prefix
        assertParseFailure(parser,
                " name/Amy Bee matric/A0234567B phone/85355255 email/amy@gmail.com a/123, "
                        + "Jurong West Ave 6, #08-111 emergency/91234567 level/7 room/5",
                expectedMessage);

        // missing level prefix
        assertParseFailure(parser,
                " name/Amy Bee matric/A0234567B phone/85355255 email/amy@gmail.com a/123, "
                        + "Jurong West Ave 6, #08-111 emergency/91234567 block/A room/5",
                expectedMessage);

        // missing room prefix
        assertParseFailure(parser,
                " name/Amy Bee matric/A0234567B phone/85355255 email/amy@gmail.com a/123, "
                        + "Jurong West Ave 6, #08-111 emergency/91234567 block/A level/7",
                expectedMessage);
    }
}

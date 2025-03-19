package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddExternalCommand;
import seedu.address.logic.commands.AddStaffCommand;
import seedu.address.logic.commands.AddStudentCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteExternalPartyCommand;
import seedu.address.logic.commands.DeleteStaffCommand;
import seedu.address.logic.commands.DeleteStudentCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
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
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.ExternalParty;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.Staff;
import seedu.address.model.person.Student;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.ExternalPartyBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PersonUtil;
import seedu.address.testutil.StaffBuilder;
import seedu.address.testutil.StudentBuilder;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_add_staff() throws Exception {
        Staff staff = new StaffBuilder().build();
        AddStaffCommand command = (AddStaffCommand) parser.parseCommand(
                AddStaffCommand.COMMAND_WORD
                        + " name/Amy Bee phone/85355255 email/amy@gmail.com a/123, "
                        + "Jurong West Ave 6, #08-111 emergency/91234567 block/A level/7 room/5");
        assertEquals(new AddStaffCommand(staff), command);
    }

    @Test
    public void parseCommand_add_student() throws Exception {
        Student student = new StudentBuilder().build();
        AddStudentCommand command = (AddStudentCommand) parser.parseCommand(
                AddStudentCommand.COMMAND_WORD
                        + " name/Amy Bee matric/A0234567B phone/85355255 email/amy@gmail.com a/123, "
                        + "Jurong West Ave 6, #08-111 emergency/91234567 block/A level/7 room/5");
        assertEquals(new AddStudentCommand(student), command);
    }

    @Test
    public void parseCommand_search_staff() throws Exception {
        Map<Prefix, String> searchCriteria = Map.of(
                CliSyntax.PREFIX_NAME, "Amy Bee",
                CliSyntax.PREFIX_PHONE, "85355255",
                CliSyntax.PREFIX_EMAIL, "amy@gmail.com",
                CliSyntax.PREFIX_ADDRESS, "123, Jurong West Ave 6, #08-111",
                CliSyntax.PREFIX_EMERGENCY, "91234567",
                CliSyntax.PREFIX_BLOCK, "A",
                CliSyntax.PREFIX_LEVEL, "7",
                CliSyntax.PREFIX_ROOM, "5"
        );
        SearchStaffCommand expectedCommand = new SearchStaffCommand(searchCriteria);
        SearchStaffCommand command = (SearchStaffCommand) parser.parseCommand(
                SearchStaffCommand.COMMAND_WORD
                        + " name/Amy Bee phone/85355255 email/amy@gmail.com a/123, "
                        + "Jurong West Ave 6, #08-111 emergency/91234567 block/A level/7 room/5");
        assertEquals(expectedCommand, command);
    }

    @Test
    public void parseCommand_search_student() throws Exception {
        Map<Prefix, String> searchCriteria = Map.of(
                CliSyntax.PREFIX_NAME, "Amy Bee",
                CliSyntax.PREFIX_MATRIC, "A0234567B",
                CliSyntax.PREFIX_PHONE, "85355255",
                CliSyntax.PREFIX_EMAIL, "amy@gmail.com",
                CliSyntax.PREFIX_ADDRESS, "123, Jurong West Ave 6, #08-111",
                CliSyntax.PREFIX_EMERGENCY, "91234567",
                CliSyntax.PREFIX_BLOCK, "A",
                CliSyntax.PREFIX_LEVEL, "7",
                CliSyntax.PREFIX_ROOM, "5"
        );
        SearchStudentCommand expectedCommand = new SearchStudentCommand(searchCriteria);
        SearchStudentCommand command = (SearchStudentCommand) parser.parseCommand(
                SearchStudentCommand.COMMAND_WORD
                        + " name/Amy Bee matric/A0234567B phone/85355255 email/amy@gmail.com a/123, "
                        + "Jurong West Ave 6, #08-111 emergency/91234567 block/A level/7 room/5");
        assertEquals(expectedCommand, command);
    }

    @Test
    public void parseCommand_search_externalParty() throws Exception {
        Map<Prefix, String> searchCriteria = Map.of(
                CliSyntax.PREFIX_NAME, "Amy Bee",
                CliSyntax.PREFIX_PHONE, "85355255",
                CliSyntax.PREFIX_EMAIL, "amy@gmail.com",
                CliSyntax.PREFIX_DESCRIPTION, "Vendor for beverages"
        );
        SearchExternalPartyCommand expectedCommand = new SearchExternalPartyCommand(searchCriteria);
        SearchExternalPartyCommand command = (SearchExternalPartyCommand) parser.parseCommand(
                SearchExternalPartyCommand.COMMAND_WORD
                        + " name/Amy Bee phone/85355255 email/amy@gmail.com desc/Vendor for beverages");
        assertEquals(expectedCommand, command);
    }

    @Test
    public void parseCommand_add() throws Exception {
        Person person = new PersonBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(PersonUtil.getAddCommand(person));
        assertEquals(new AddCommand(person), command);
    }

    @Test
    public void parseCommand_addExternal() throws Exception {
        ExternalParty externalParty = new ExternalPartyBuilder().build();

        AddExternalCommand command = (AddExternalCommand) parser.parseCommand(
                AddExternalCommand.COMMAND_WORD
                        + " name/Amy Bee phone/85355255 email/amy@gmail.com desc/External supplier for food.");
        assertEquals(new AddExternalCommand(externalParty), command);
    }


    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_deleteStudent() throws Exception {
        DeleteStudentCommand command = (DeleteStudentCommand) parser.parseCommand(
                DeleteStudentCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteStudentCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_deleteExternalParty() throws Exception {
        DeleteExternalPartyCommand command = (DeleteExternalPartyCommand) parser.parseCommand(
                DeleteExternalPartyCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteExternalPartyCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_deleteStaff() throws Exception {
        DeleteStaffCommand command = (DeleteStaffCommand) parser.parseCommand(
                DeleteStaffCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteStaffCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Person person = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_PERSON, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + String.join(" ", keywords));
        assertEquals(new FindCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
    }

    @Test
    public void parseCommand_listStaff() throws Exception {
        assertTrue(parser.parseCommand(ListStaffCommand.COMMAND_WORD) instanceof ListStaffCommand);
    }

    @Test
    public void parseCommand_listExternalParty() throws Exception {
        assertTrue(parser.parseCommand(ListExternalPartyCommand.COMMAND_WORD) instanceof ListExternalPartyCommand);
    }

    @Test
    public void parseCommand_listStudent() throws Exception {
        assertTrue(parser.parseCommand(ListStudentCommand.COMMAND_WORD) instanceof ListStudentCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}

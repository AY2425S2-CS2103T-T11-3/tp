package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_SEARCHING_CRITERIA;
import static seedu.address.logic.Messages.MESSAGE_MISSING_FIELD_AFTER_PREFIX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_MEMTYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.event.SearchEventMemberCommand;
import seedu.address.model.event.EventMemberPredicate;


public class SearchEventMemberCommandParserTest {

    private final SearchEventMemberCommandParser parser = new SearchEventMemberCommandParser();

    @Test
    public void parse_validArgs_returnsSearchEventMemberCommand() throws Exception {
        Map<Prefix, String> searchCriteria = new HashMap<>();
        searchCriteria.put(PREFIX_NAME, "John Doe");
        EventMemberPredicate predicate = new EventMemberPredicate("stu", searchCriteria);
        SearchEventMemberCommand expectedCommand = new SearchEventMemberCommand(Index.fromOneBased(1), predicate);

        assertParseSuccess(parser, "1 " + PREFIX_EVENT_MEMTYPE + "stu name/John Doe", expectedCommand);
    }

    @Test
    public void parse_invalidIndex_throwsParseException() {
        assertParseFailure(parser, "a stu n/John Doe", String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                SearchEventMemberCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_missingMemberType_throwsParseException() {
        assertParseFailure(parser, "1 n/John Doe", String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                SearchEventMemberCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_missingPrefix_throwsParseException() {
        assertParseFailure(parser, "1 stu John Doe", String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                SearchEventMemberCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidPrefix_throwsParseException() {
        assertParseFailure(parser, "1 stu x/John Doe", String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                SearchEventMemberCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyArgs_throwsParseException() {
        assertParseFailure(parser, "", String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                SearchEventMemberCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_missingArgs_throwsParseException() {
        assertParseFailure(parser, "1 " + PREFIX_EVENT_MEMTYPE + "stu " + PREFIX_NAME,
                MESSAGE_MISSING_FIELD_AFTER_PREFIX);
    }

    @Test
    public void parse_descriptionInStuSearch_throwsParseException() {
        assertParseFailure(parser, "1 " + PREFIX_EVENT_MEMTYPE + "stu name/John Doe desc/John Doe",
                String.format(MESSAGE_INVALID_SEARCHING_CRITERIA, SearchEventMemberCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_matricInStaffSearch_throwsParseException() {
        assertParseFailure(parser, "1 " + PREFIX_EVENT_MEMTYPE + "staff name/John Doe matric/A0255778L",
                String.format(MESSAGE_INVALID_SEARCHING_CRITERIA, SearchEventMemberCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_matricInExtSearch_throwsParseException() {
        assertParseFailure(parser, "1 " + PREFIX_EVENT_MEMTYPE + "ext name/John Doe matric/A0255778L",
                String.format(MESSAGE_INVALID_SEARCHING_CRITERIA, SearchEventMemberCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_missingCriteriaStu_throwsParseException() {
        assertParseFailure(parser, "1 " + PREFIX_EVENT_MEMTYPE + "stu",
                String.format(Messages.MESSAGE_MISSING_SEARCHING_CRITERIA, SearchEventMemberCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_missingCriteriaStaff_throwsParseException() {
        assertParseFailure(parser, "1 " + PREFIX_EVENT_MEMTYPE + "staff",
                String.format(Messages.MESSAGE_MISSING_SEARCHING_CRITERIA, SearchEventMemberCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_missingCriteriaExt_throwsParseException() {
        assertParseFailure(parser, "1 memtype/ext", String.format(Messages.MESSAGE_MISSING_SEARCHING_CRITERIA,
                SearchEventMemberCommand.MESSAGE_USAGE));
    }
}

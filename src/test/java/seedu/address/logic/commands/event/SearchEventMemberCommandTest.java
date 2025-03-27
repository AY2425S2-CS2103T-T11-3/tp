package seedu.address.logic.commands.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.commands.event.SearchEventMemberCommand.MESSAGE_NO_MATCH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventEndTime;
import seedu.address.model.event.EventMemberPredicate;
import seedu.address.model.event.EventName;
import seedu.address.model.event.EventStartTime;
import seedu.address.model.person.ExternalParty;
import seedu.address.model.person.Staff;
import seedu.address.model.person.Student;
import seedu.address.testutil.ExternalPartyBuilder;
import seedu.address.testutil.StaffBuilder;
import seedu.address.testutil.StudentBuilder;

public class SearchEventMemberCommandTest {
    private static final Index INDEX_FIRST_EVENT = Index.fromOneBased(1);

    private Model model;
    private Student student;
    private Staff staff;
    private ExternalParty externalParty;
    private Event sampleEvent;

    @BeforeEach
    public void setUp() {
        model = new ModelManager();
        sampleEvent = new Event(
                new EventName("Team Meeting"),
                new EventStartTime("2025-04-01 10:00"),
                new EventEndTime("2025-04-01 11:00"));
        sampleEvent.addExternalParty(externalParty = new ExternalPartyBuilder().build());
        sampleEvent.addStaff(staff = new StaffBuilder().build());
        sampleEvent.addStudent(student = new StudentBuilder().build());

        model.addEvent(sampleEvent);
    }

    @Test
    public void execute_validStudentSearch_success() throws Exception {
        Map<Prefix, String> searchCriteria = new HashMap<>();
        searchCriteria.put(PREFIX_NAME, "Amy Bee");
        EventMemberPredicate predicate = new EventMemberPredicate("stu", searchCriteria);
        SearchEventMemberCommand command = new SearchEventMemberCommand(INDEX_FIRST_EVENT, predicate);

        CommandResult result = command.execute(model);
        assertEquals(String.format(SearchEventMemberCommand.MESSAGE_SUCCESS, 1), result.getFeedbackToUser());
    }

    @Test
    public void execute_validStaffSearch_success() throws Exception {
        Map<Prefix, String> searchCriteria = new HashMap<>();
        searchCriteria.put(PREFIX_NAME, "Amy Bee");
        EventMemberPredicate predicate = new EventMemberPredicate("staff", searchCriteria);
        SearchEventMemberCommand command = new SearchEventMemberCommand(INDEX_FIRST_EVENT, predicate);

        CommandResult result = command.execute(model);
        assertEquals(String.format(SearchEventMemberCommand.MESSAGE_SUCCESS, 1), result.getFeedbackToUser());
    }

    @Test
    public void execute_validExternalPartySearch_success() throws Exception {
        Map<Prefix, String> searchCriteria = new HashMap<>();
        searchCriteria.put(PREFIX_NAME, "Amy Bee");
        EventMemberPredicate predicate = new EventMemberPredicate("ext", searchCriteria);
        SearchEventMemberCommand command = new SearchEventMemberCommand(INDEX_FIRST_EVENT, predicate);

        CommandResult result = command.execute(model);
        assertEquals(String.format(SearchEventMemberCommand.MESSAGE_SUCCESS, 1), result.getFeedbackToUser());
    }

    @Test
    public void execute_noMatchingMembers_throwsCommandException() throws CommandException {
        Map<Prefix, String> searchCriteria = new HashMap<>();
        searchCriteria.put(PREFIX_NAME, "Nonexistent");
        EventMemberPredicate predicate = new EventMemberPredicate("stu", searchCriteria);
        SearchEventMemberCommand command = new SearchEventMemberCommand(INDEX_FIRST_EVENT, predicate);

        assertEquals(String.format(MESSAGE_NO_MATCH), command.execute(model).getFeedbackToUser());
    }

    @Test
    public void execute_invalidEventIndex_throwsCommandException() {
        Map<Prefix, String> searchCriteria = new HashMap<>();
        searchCriteria.put(PREFIX_NAME, "Amy Bee");
        EventMemberPredicate predicate = new EventMemberPredicate("stu", searchCriteria);
        SearchEventMemberCommand command = new SearchEventMemberCommand(Index.fromOneBased(2), predicate);

        assertThrows(CommandException.class, () -> command.execute(model),
                Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
    }

    @Test
    public void toStringTest() {
        Map<Prefix, String> searchCriteria = new HashMap<>();
        searchCriteria.put(PREFIX_NAME, "Amy Bee");
        EventMemberPredicate predicate = new EventMemberPredicate("stu", searchCriteria);
        SearchEventMemberCommand command = new SearchEventMemberCommand(INDEX_FIRST_EVENT, predicate);
        String expected = command.toString();
        assertEquals(expected, command.toString());
    }
}

package seedu.address.logic.parser;


import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADD_EXTERNAL;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddExternalCommand;
import seedu.address.model.person.Email;
import seedu.address.model.person.ExternalParty;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.testutil.ExternalPartyBuilder;

public class AddExternalCommandParserTest {
    private AddExternalCommandParser parser = new AddExternalCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        ExternalParty expectedExternalParty = new ExternalPartyBuilder().build();

        assertParseSuccess(parser,
                "add_ext name/Amy Bee phone/85355255 email/amy@gmail.com desc/External supplier for food.",
                new AddExternalCommand(expectedExternalParty));
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, VALID_ADD_EXTERNAL + INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + DESCRIPTION_DESC_BOB, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser,
                "add_ext name/Bob Choo phone/911a email/bob@example.com desc/External supplier for food.",
                Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, VALID_ADD_EXTERNAL + NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC
                        + DESCRIPTION_DESC_BOB, Email.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, VALID_ADD_EXTERNAL + INVALID_NAME_DESC + INVALID_PHONE_DESC
                        + EMAIL_DESC_BOB + DESCRIPTION_DESC_BOB, Name.MESSAGE_CONSTRAINTS);
    }
}

package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_INVALID_EVENT_MEMBER_TYPE;
import static seedu.address.logic.Messages.MESSAGE_INVALID_SEARCHING_CRITERIA;
import static seedu.address.logic.Messages.MESSAGE_MISSING_EVENT_MEMBER_TYPE;
import static seedu.address.logic.Messages.MESSAGE_MISSING_FIELD_AFTER_PREFIX;
import static seedu.address.logic.Messages.MESSAGE_MISSING_SEARCHING_CRITERIA;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BLOCK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESIGNATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMERGENCY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_MEMTYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LEVEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MATRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.event.SearchEventMemberCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.EventMemberPredicate;

/**
 * Parses input arguments and creates a new SearchEventMemberCommand object
 */
public class SearchEventMemberCommandParser implements Parser<SearchEventMemberCommand> {
    @Override
    public SearchEventMemberCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_EVENT_MEMTYPE, PREFIX_NAME,
                PREFIX_MATRIC, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_EMERGENCY, PREFIX_BLOCK,
                PREFIX_LEVEL, PREFIX_ROOM, PREFIX_DESIGNATION, PREFIX_DESCRIPTION);
        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    SearchEventMemberCommand.MESSAGE_USAGE), pe);
        }
        if (!argMultimap.getValue(PREFIX_EVENT_MEMTYPE).isPresent()) {
            throw new ParseException(String.format(MESSAGE_MISSING_EVENT_MEMBER_TYPE,
                    SearchEventMemberCommand.MESSAGE_USAGE));
        }

        // Check for empty values after a prefix
        Prefix[] prefixes = {
            PREFIX_NAME, PREFIX_MATRIC, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_EMERGENCY, PREFIX_BLOCK,
            PREFIX_LEVEL, PREFIX_ROOM, PREFIX_DESIGNATION, PREFIX_DESCRIPTION
        };
        for (Prefix prefix : prefixes) {
            Optional<String> value = argMultimap.getValue(prefix);
            if (value.isPresent() && value.get().trim().isEmpty()) {
                throw new ParseException(MESSAGE_MISSING_FIELD_AFTER_PREFIX);
            }
        }

        String memberType = argMultimap.getValue(PREFIX_EVENT_MEMTYPE).get();

        // Validate member type
        if (!memberType.equals("stu") && !memberType.equals("staff") && !memberType.equals("ext")) {
            throw new ParseException(String.format(MESSAGE_INVALID_EVENT_MEMBER_TYPE,
                    SearchEventMemberCommand.MESSAGE_USAGE));
        }

        // Validate search criteria based on member type
        if (memberType.equals("stu")) {
            validateStudentCriteria(argMultimap);
        } else if (memberType.equals("staff")) {
            validateStaffCriteria(argMultimap);
        } else {
            validateExternalPartyCriteria(argMultimap);
        }

        // Create search criteria map excluding EVENT_MEMTYPE
        Map<Prefix, String> searchCriteria = new HashMap<>();
        argMultimap.getPrefixValueMap().forEach((prefix, values) -> {
            if (prefix != null && !values.isEmpty() && !prefix.equals(PREFIX_EVENT_MEMTYPE)) {
                if (!prefix.toString().equals("")) {
                    searchCriteria.put(prefix, values);
                }
            }
        });

        // Create EventMemberPredicate with member type and search criteria
        EventMemberPredicate predicate = new EventMemberPredicate(memberType, searchCriteria);

        return new SearchEventMemberCommand(index, predicate);
    }

    private void validateStudentCriteria(ArgumentMultimap argMultimap) throws ParseException {
        if (!isAnyPrefixPresent(argMultimap, PREFIX_NAME, PREFIX_MATRIC, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                PREFIX_EMERGENCY, PREFIX_BLOCK, PREFIX_LEVEL, PREFIX_ROOM, PREFIX_DESIGNATION)) {
            throw new ParseException(
                    String.format(MESSAGE_MISSING_SEARCHING_CRITERIA, SearchEventMemberCommand.MESSAGE_USAGE));
        } else if (isAnyPrefixPresent(argMultimap, PREFIX_DESCRIPTION)) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_SEARCHING_CRITERIA, SearchEventMemberCommand.MESSAGE_USAGE));
        }
    }

    private void validateStaffCriteria(ArgumentMultimap argMultimap) throws ParseException {
        if (!isAnyPrefixPresent(argMultimap, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                PREFIX_EMERGENCY, PREFIX_BLOCK, PREFIX_LEVEL, PREFIX_ROOM, PREFIX_DESIGNATION)) {
            throw new ParseException(
                    String.format(MESSAGE_MISSING_SEARCHING_CRITERIA, SearchEventMemberCommand.MESSAGE_USAGE));
        } else if (isAnyPrefixPresent(argMultimap, PREFIX_MATRIC, PREFIX_DESCRIPTION)) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_SEARCHING_CRITERIA, SearchEventMemberCommand.MESSAGE_USAGE));
        }
    }

    private void validateExternalPartyCriteria(ArgumentMultimap argMultimap) throws ParseException {
        if (!isAnyPrefixPresent(argMultimap, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_DESCRIPTION)) {
            throw new ParseException(
                    String.format(MESSAGE_MISSING_SEARCHING_CRITERIA, SearchEventMemberCommand.MESSAGE_USAGE));
        } else if (isAnyPrefixPresent(argMultimap, PREFIX_MATRIC, PREFIX_ADDRESS, PREFIX_EMERGENCY,
                PREFIX_BLOCK, PREFIX_LEVEL, PREFIX_ROOM, PREFIX_DESIGNATION)) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_SEARCHING_CRITERIA, SearchEventMemberCommand.MESSAGE_USAGE));
        }
    }

    private static boolean isAnyPrefixPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}

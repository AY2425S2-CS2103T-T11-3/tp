package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("name/");
    public static final Prefix PREFIX_PHONE = new Prefix("phone/");
    public static final Prefix PREFIX_EMAIL = new Prefix("email/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");

    /* Prefix definitions for event */
    public static final Prefix PREFIX_EVENT_NAME = new Prefix("name/");
    public static final Prefix PREFIX_EVENT_START_TIME = new Prefix("from/");
    public static final Prefix PREFIX_EVENT_END_TIME = new Prefix("to/");
    public static final Prefix PREFIX_EVENT_STUDENT = new Prefix("stu/");
    public static final Prefix PREFIX_EVENT_STAFF = new Prefix("staff/");
    public static final Prefix PREFIX_EVENT_EXTERNAL = new Prefix("ext/");
    public static final Prefix PREFIX_EVENT_MEMTYPE = new Prefix("memtype/");

    public static final Prefix PREFIX_DESCRIPTION = new Prefix("desc/");
    public static final Prefix PREFIX_MATRIC = new Prefix("matric/");
    public static final Prefix PREFIX_EMERGENCY = new Prefix("emergency/");
    public static final Prefix PREFIX_BLOCK = new Prefix("block/");
    public static final Prefix PREFIX_LEVEL = new Prefix("level/");
    public static final Prefix PREFIX_ROOM = new Prefix("room/");
    public static final Prefix PREFIX_DESIGNATION = new Prefix("designation/");

}

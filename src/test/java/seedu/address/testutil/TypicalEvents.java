package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.event.Event;

/**
 * A utility class containing a list of {@code Event} objects to be used in tests.
 */
public class TypicalEvents {

    public static final Event MEETING = new EventBuilder()
            .withName("Team Meeting")
            .withStartTime("2025-06-15 14:00")
            .withEndTime("2025-06-15 16:00")
            .build();

    public static final Event CONCERT = new EventBuilder()
            .withName("Music Concert")
            .withStartTime("2025-07-01 18:00")
            .withEndTime("2025-07-01 22:00")
            .build();

    public static final Event WORKSHOP = new EventBuilder()
            .withName("Tech Workshop")
            .withStartTime("2025-07-10 09:00")
            .withEndTime("2025-07-10 12:00")
            .build();

    private TypicalEvents() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical events.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Event event : getTypicalEvents()) {
            ab.addEvent(event);
        }
        return ab;
    }

    /**
     * Returns a list of typical events.
     */
    public static List<Event> getTypicalEvents() {
        return new ArrayList<>(Arrays.asList(MEETING, CONCERT, WORKSHOP));
    }
}

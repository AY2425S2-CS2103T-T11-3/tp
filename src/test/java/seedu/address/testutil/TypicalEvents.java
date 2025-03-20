package seedu.address.testutil;

import seedu.address.model.AddressBook;
import seedu.address.model.event.Event;

/**
 * A utility class containing a list of {@code Event} objects to be used in tests.
 */
public class TypicalEvents {
    public static final Event DANCE_EVENT = new EventBuilder().withEventName("Dance Club Rehearsal")
            .withEventStartTime("2025-06-15 18:00").withEventEndTime("2025-06-15 21:00").build();

    public static final Event BASKETBALL_EVENT = new EventBuilder().withEventName("Basketball Club Training")
            .withEventStartTime("2025-06-16 18:00").withEventEndTime("2025-06-16 21:00").build();

    private TypicalEvents() {}

    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Event event : getTypicalEvents()) {
            ab.addEvent(event);
        }
        return ab;
    }

    public static Event[] getTypicalEvents() {
        return new Event[] {DANCE_EVENT, BASKETBALL_EVENT};
    }

    public static AddressBook getEmptyAddressBook() {
        return new AddressBook();
    }
}

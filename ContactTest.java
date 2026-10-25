import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class ContactTest {
    @Test
    void constructorStoresFieldsAndAcceptsMaximumLengths() {
        Contact contact = new Contact("1234567890", "1234567890", "abcdefghij", "0123456789",
                "123456789012345678901234567890");
        assertEquals("1234567890", contact.getContactId());
        assertEquals("1234567890", contact.getFirstName());
        assertEquals("abcdefghij", contact.getLastName());
        assertEquals("0123456789", contact.getPhone());
        assertEquals("123456789012345678901234567890", contact.getAddress());
    }

    @Test
    void constructorRejectsInvalidIds() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact(null, "Ada", "Lovelace", "0123456789", "London"));
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("12345678901", "Ada", "Lovelace", "0123456789", "London"));
    }

    @Test
    void constructorRejectsInvalidNames() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("1", null, "Lovelace", "0123456789", "London"));
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("1", "12345678901", "Lovelace", "0123456789", "London"));
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("1", "Ada", null, "0123456789", "London"));
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("1", "Ada", "12345678901", "0123456789", "London"));
    }

    @Test
    void constructorRejectsInvalidPhones() {
        for (String phone : new String[] { null, "123456789", "12345678901", "12345abcde", "123-456-789" }) {
            assertThrows(IllegalArgumentException.class,
                    () -> new Contact("1", "Ada", "Lovelace", phone, "London"));
        }
    }

    @Test
    void constructorRejectsInvalidAddresses() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("1", "Ada", "Lovelace", "0123456789", null));
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("1", "Ada", "Lovelace", "0123456789", "1234567890123456789012345678901"));
    }

    @Test
    void settersUpdateOnlyAllowedFields() {
        Contact contact = new Contact("1", "Ada", "Lovelace", "0123456789", "London");
        contact.setFirstName("Grace");
        contact.setLastName("Hopper");
        contact.setPhone("9876543210");
        contact.setAddress("New York");
        assertEquals("1", contact.getContactId());
        assertEquals("Grace", contact.getFirstName());
        assertEquals("Hopper", contact.getLastName());
        assertEquals("9876543210", contact.getPhone());
        assertEquals("New York", contact.getAddress());
    }

    @Test
    void settersRejectInvalidValuesWithoutChangingStoredValues() {
        Contact contact = new Contact("1", "Ada", "Lovelace", "0123456789", "London");
        assertThrows(IllegalArgumentException.class, () -> contact.setFirstName(null));
        assertThrows(IllegalArgumentException.class, () -> contact.setFirstName("12345678901"));
        assertThrows(IllegalArgumentException.class, () -> contact.setLastName(null));
        assertThrows(IllegalArgumentException.class, () -> contact.setLastName("12345678901"));
        assertThrows(IllegalArgumentException.class, () -> contact.setPhone("123456789a"));
        assertThrows(IllegalArgumentException.class, () -> contact.setAddress(null));
        assertThrows(IllegalArgumentException.class,
                () -> contact.setAddress("1234567890123456789012345678901"));
        assertEquals("Ada", contact.getFirstName());
        assertEquals("Lovelace", contact.getLastName());
        assertEquals("0123456789", contact.getPhone());
        assertEquals("London", contact.getAddress());
    }
}

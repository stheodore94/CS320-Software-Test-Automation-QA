import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class ContactServiceTest {
    private Contact newContact(String id) {
        return new Contact(id, "Ada", "Lovelace", "0123456789", "London");
    }

    @Test
    void addsContactsAndFindsThemById() {
        ContactService service = new ContactService();
        Contact first = newContact("1");
        Contact second = newContact("2");
        service.addContact(first);
        service.addContact(second);
        assertSame(first, service.getContact("1"));
        assertSame(second, service.getContact("2"));
    }

    @Test
    void rejectsNullAndDuplicateContacts() {
        ContactService service = new ContactService();
        service.addContact(newContact("1"));
        assertThrows(IllegalArgumentException.class, () -> service.addContact(null));
        assertThrows(IllegalArgumentException.class, () -> service.addContact(newContact("1")));
        assertEquals("Ada", service.getContact("1").getFirstName());
    }

    @Test
    void deletesOnlyTheRequestedContact() {
        ContactService service = new ContactService();
        service.addContact(newContact("1"));
        service.addContact(newContact("2"));
        service.deleteContact("1");
        assertThrows(IllegalArgumentException.class, () -> service.getContact("1"));
        assertEquals("2", service.getContact("2").getContactId());
        assertThrows(IllegalArgumentException.class, () -> service.deleteContact("1"));
    }

    @Test
    void updatesEachAllowedFieldWithoutChangingId() {
        ContactService service = new ContactService();
        service.addContact(newContact("1"));
        service.updateFirstName("1", "Grace");
        service.updateLastName("1", "Hopper");
        service.updatePhone("1", "9876543210");
        service.updateAddress("1", "New York");
        Contact updated = service.getContact("1");
        assertEquals("1", updated.getContactId());
        assertEquals("Grace", updated.getFirstName());
        assertEquals("Hopper", updated.getLastName());
        assertEquals("9876543210", updated.getPhone());
        assertEquals("New York", updated.getAddress());
    }

    @Test
    void updatesRejectInvalidValuesAndUnknownIds() {
        ContactService service = new ContactService();
        service.addContact(newContact("1"));
        assertThrows(IllegalArgumentException.class, () -> service.updateFirstName("1", null));
        assertThrows(IllegalArgumentException.class, () -> service.updateLastName("1", "12345678901"));
        assertThrows(IllegalArgumentException.class, () -> service.updatePhone("1", "123"));
        assertThrows(IllegalArgumentException.class,
                () -> service.updateAddress("1", "1234567890123456789012345678901"));
        assertThrows(IllegalArgumentException.class, () -> service.updateFirstName("missing", "Grace"));
        assertThrows(IllegalArgumentException.class, () -> service.updateLastName("missing", "Hopper"));
        assertThrows(IllegalArgumentException.class, () -> service.updatePhone("missing", "9876543210"));
        assertThrows(IllegalArgumentException.class, () -> service.updateAddress("missing", "New York"));
        assertEquals("Ada", service.getContact("1").getFirstName());
    }
}

public class Contact {
    private final String contactId;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;

    public Contact(String contactId, String firstName, String lastName, String phone, String address) {
        validateLength(contactId, 10, "Contact ID");
        validateLength(firstName, 10, "First name");
        validateLength(lastName, 10, "Last name");
        validatePhone(phone);
        validateLength(address, 30, "Address");

        this.contactId = contactId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
    }

    private static void validateLength(String value, int maximum, String field) {
        if (value == null || value.length() > maximum) {
            throw new IllegalArgumentException(field + " is required and must be at most " + maximum + " characters.");
        }
    }

    private static void validatePhone(String value) {
        if (value == null || !value.matches("[0-9]{10}")) {
            throw new IllegalArgumentException("Phone must contain exactly 10 digits.");
        }
    }

    public String getContactId() {
        return contactId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public void setFirstName(String firstName) {
        validateLength(firstName, 10, "First name");
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        validateLength(lastName, 10, "Last name");
        this.lastName = lastName;
    }

    public void setPhone(String phone) {
        validatePhone(phone);
        this.phone = phone;
    }

    public void setAddress(String address) {
        validateLength(address, 30, "Address");
        this.address = address;
    }
}

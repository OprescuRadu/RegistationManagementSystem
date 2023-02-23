import java.util.Objects;

public class Guest {
    private String lastName;
    private String firstName;
    private String email;
    private String phoneNumber;

    public Guest(String lastName, String firstName, String email, String phoneNumber) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean hasSameName(String lastName, String firstName) {
        return this.lastName.equalsIgnoreCase(lastName) && this.firstName.equalsIgnoreCase(firstName);
    }

    @Override
    public String toString() {
        return "[LastName = '" + lastName + '\'' +
                ", FirstName = '" + firstName + '\'' +
                ", Email = '" + email + '\'' +
                ", PhoneNumber = '" + phoneNumber + "']";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Guest guest)) {
            return false;
        }
        /*  2 guests are considered equal if:
            - LastName & FirstName are the same,
            - If they have the same Email or
            - If they have the same Phone Number */
        return phoneNumber.equals(guest.phoneNumber) || Objects.equals(lastName.toLowerCase(), guest.lastName.toLowerCase())
                && Objects.equals(firstName.toLowerCase(), guest.firstName.toLowerCase()) || Objects.equals(email, guest.email);
    }

    public boolean partialSearch(String str) {
        str = str.toLowerCase();
        return this.firstName.toLowerCase().contains(str) ||
                this.lastName.toLowerCase().contains(str) ||
                this.email.toLowerCase().contains(str) ||
                this.phoneNumber.contains(str);
    }
}

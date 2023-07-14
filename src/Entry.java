class Entry {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String emailAddress;

    public Entry(String firstName, String lastName, String phoneNumber, String emailAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        setPhoneNumber(phoneNumber);
        this.emailAddress = emailAddress;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

    public String getEmailAddress() {
        return emailAddress;
    }

    @Override
    public String toString() {
        // Format the phone number as (xxx) xxx-xxxx
        String formattedPhoneNumber = phoneNumber.replaceAll("(\\d{3})(\\d{3})(\\d{4})", "($1) $2-$3");

        return "************\n" +
                "First Name: " + firstName + "\n" +
                "Last Name: " + lastName + "\n" +
                "Phone Number: " + formattedPhoneNumber + "\n" +
                "Email: " + emailAddress + "\n" +
                "************";
    }
}
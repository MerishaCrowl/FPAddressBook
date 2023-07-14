import java.io.*;
import java.util.ArrayList;

class AddressBook {
    private final ArrayList<Entry> entries;

    public AddressBook() {
        this.entries = new ArrayList<>();
    }

    public void addEntry(Entry entry) {
        entries.add(entry);
    }

    public void removeEntry(String email) {
        Entry entryToRemove = null;
        for (Entry entry : entries) {
            if (entry.getEmailAddress().equals(email)) {
                entryToRemove = entry;
                break;
            }
        }
        if (entryToRemove != null) {
            entries.remove(entryToRemove);
            System.out.println("Deleted the following entry:\n" + entryToRemove);
        } else {
            System.out.println("Entry not found!");
        }
    }

    public void searchEntries(int searchType, String query) {
        ArrayList<Entry> results = new ArrayList<>();

        for (Entry entry : entries) {
            String searchData = switch (searchType) {
                case 1 -> entry.getFirstName();
                case 2 -> entry.getLastName();
                case 3 -> entry.getPhoneNumber();
                case 4 -> entry.getEmailAddress();
                default -> null;
            };

            if (searchData != null && searchData.contains(query)) {
                results.add(entry);
            }
        }

        if (results.isEmpty()) {
            System.out.println("No results found!");
        } else {
            for (Entry entry : results) {
                System.out.println(entry);
            }
        }
    }

    public void printAddressBook() {
        if (entries.isEmpty()) {
            System.out.println("Address Book is empty!");
        } else {
            for (Entry entry : entries) {
                System.out.println(entry);
            }
        }
    }

    public void deleteAddressBook() {
        entries.clear();
        System.out.println("Address book cleared!");
    }

    public boolean isEmailExist(String emailAddress) {
        for (Entry entry : entries) {
            if (entry.getEmailAddress().equalsIgnoreCase(emailAddress)) {
                return true; // Found a matching email address
            }
        }
        return false; // Email address not found
    }

    public void saveToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Entry entry : entries) {
                writer.write(entry.toString());
                writer.newLine();
            }
            System.out.println("Address book saved to file: " + filename);
        } catch (IOException e) {
            System.out.println("Failed to save address book to file: " + filename);
        }
    }

    public void loadFromFile(String filename) {
        entries.clear(); // Clear existing entries before loading from file

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String firstName = "";
                String lastName = "";
                String phoneNumber = "";
                String emailAddress = "";

                if (line.startsWith("First Name: ")) {
                    firstName = line.substring("First Name: ".length());
                    lastName = reader.readLine().substring("Last Name: ".length());
                    phoneNumber = reader.readLine().substring("Phone Number: ".length());
                    emailAddress = reader.readLine().substring("Email: ".length());
                    reader.readLine(); //Skip the separator line

                    Entry entry = new Entry(firstName, lastName, phoneNumber, emailAddress);
                    entries.add(entry);
                }
            }
            System.out.println("Address book loaded from file: " + filename);
        } catch (IOException e) {
            System.out.println("Failed to load address book from file: " + filename);
        }
    }
}

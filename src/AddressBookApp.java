import java.util.Scanner;


public class AddressBookApp {
    //Create the opening user menu
    private static final int ADD_ENTRY = 1;
    private static final int REMOVE_ENTRY = 2;
    private static final int SEARCH_ENTRY = 3;
    private static final int PRINT_ADDRESS_BOOK = 4;
    private static final int DELETE_BOOK = 5;
    private static final int QUIT = 6;

    private static Scanner scanner;
    private static AddressBook addressBook;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        addressBook = new AddressBook();

        loadAddressBookFromFile(); // Load the address book from file at the beginning

        boolean quit = false;
        while (!quit) {
            printMenu();
            int choice = getUserChoice();

            switch (choice) {
                case ADD_ENTRY -> addEntry();
                case REMOVE_ENTRY -> removeEntry();
                case SEARCH_ENTRY -> searchEntry();
                case PRINT_ADDRESS_BOOK -> printAddressBook();
                case DELETE_BOOK -> deleteBook();
                case QUIT -> quit = true;
                default -> System.out.println("Invalid input! Please enter a number from 1 to 6.");
            }
        }
        saveAddressBookToFile(); // Save the address book to file before quitting
        scanner.close();
    }

    private static void loadAddressBookFromFile() {
        String filePath = "C:\\Users\\meris\\IdeaProjects\\FPAddressBook\\address_book.txt";
        addressBook.loadFromFile("address_book.txt");
        System.out.println("Address book loaded from file.");
    }

    private static void saveAddressBookToFile() {
        String filePath = "C:\\Users\\meris\\IdeaProjects\\FPAddressBook\\address_book.txt";
        addressBook.saveToFile("address_book.txt");
        System.out.println("Address book saved to file.");
    }

    private static void printMenu() {
        System.out.println("1) Add an entry");
        System.out.println("2) Remove an entry");
        System.out.println("3) Search for a specific entry");
        System.out.println("4) Print Address Book");
        System.out.println("5) Delete Book");
        System.out.println("6) Quit");
        System.out.print("Please choose what you'd like to do with the database: ");
    }

    private static int getUserChoice() {
        int choice = -1;
        while (choice == -1) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number from 1 to 6.");
            }
        }
        return choice;
    }

    private static void addEntry() {
        System.out.print("First Name: ");
        String firstName = scanner.nextLine();

        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();

        System.out.print("Phone Number: ");
        String phoneNumber = scanner.nextLine();

        System.out.print("Email Address: ");
        String emailAddress = scanner.nextLine();

        // Check if the email address already exists in the address book
        if (addressBook.isEmailExist(emailAddress)) {
            System.out.println("Email address already exists. Entry not added.");
        } else {
            Entry newEntry = new Entry(firstName, lastName, phoneNumber, emailAddress);
            addressBook.addEntry(newEntry);
            System.out.println("Added new entry!");
        }
    }

    private static void removeEntry() {
        System.out.print("Enter an entry's email to remove: ");
        String email = scanner.nextLine();
        addressBook.removeEntry(email);
    }

    private static void searchEntry() {
        System.out.println("1) First Name");
        System.out.println("2) Last Name");
        System.out.println("3) Phone Number");
        System.out.println("4) Email Address");
        System.out.print("Choose a search type: ");
        int searchType = getUserChoice();

        System.out.print("Enter your search: ");
        String query = scanner.nextLine();

        addressBook.searchEntries(searchType, query);
    }

    private static void printAddressBook() {
        addressBook.printAddressBook();
    }

    private static void deleteBook() {
        addressBook.deleteAddressBook();
    }
}
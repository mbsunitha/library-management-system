import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Book {
    // ... (unchanged)

    @Override
    public String toString() {
        return "Title: " + getTitle() +
                "\nAuthor: " + getAuthor() +
                "\nGenre: " + getGenre() +
                "\nAvailability: " + (isAvailable() ? "Available" : "Not Available") +
                "\n-----------------------------";
    }
}

class Patron {
    private String name;
    private String contactInfo;
    private List<Book> borrowingHistory;

    public Patron(String name, String contactInfo) {
        this.name = name;
        this.contactInfo = contactInfo;
        this.borrowingHistory = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public List<Book> getBorrowingHistory() {
        return borrowingHistory;
    }

    public void borrowBook(Book book) {
        borrowingHistory.add(book);
    }

    public void returnBook(Book book) {
        borrowingHistory.remove(book);
    }

    @Override
    public String toString() {
        return "Name: " + getName() +
                "\nContact Info: " + getContactInfo() +
                "\nBorrowing History: " + getBorrowingHistory() +
                "\n-----------------------------";
    }
}

class PatronManager {
    private List<Patron> patrons;

    public PatronManager() {
        this.patrons = new ArrayList<>();
    }

    public void addPatron(Patron patron) {
        patrons.add(patron);
    }

    public void displayPatrons() {
        System.out.println("Patron Records:");
        for (Patron patron : patrons) {
            System.out.println(patron);
        }
    }
}

public class LibraryManagementSystemWithPatrons {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BookManager bookManager = new BookManager();
        PatronManager patronManager = new PatronManager();

        // Adding sample books to the library
        bookManager.addBook(new Book("The Great Gatsby", "F. Scott Fitzgerald", "Fiction"));
        bookManager.addBook(new Book("To Kill a Mockingbird", "Harper Lee", "Fiction"));
        bookManager.addBook(new Book("1984", "George Orwell", "Dystopian"));

        // Adding sample patrons to the library
        patronManager.addPatron(new Patron("John Doe", "john.doe@example.com"));
        patronManager.addPatron(new Patron("Jane Smith", "jane.smith@example.com"));

        int choice;
        do {
            System.out.println("Library Management System Menu:");
            System.out.println("1. Display Books");
            System.out.println("2. Display Patrons");
            System.out.println("3. Check Out a Book");
            System.out.println("4. Return a Book");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    bookManager.displayBooks();
                    break;
                case 2:
                    patronManager.displayPatrons();
                    break;
                case 3:
                    // ... (unchanged)
                    break;
                case 4:
                    // ... (unchanged)
                    break;
                case 0:
                    System.out.println("Exiting the Library Management System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }

        } while (choice != 0);

        scanner.close();
    }
}

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LibraryManagementSystemMySQL {

    // ... (previous code)

    private static final String SELECT_AVAILABLE_BOOKS = "SELECT * FROM books WHERE available = TRUE";
    private static final String SELECT_BORROWING_HISTORY = "SELECT patrons.name AS patron_name, books.title AS book_title " +
            "FROM patrons JOIN borrowing_history ON patrons.id = borrowing_history.patron_id " +
            "JOIN books ON borrowing_history.book_id = books.id";
    private static final String SELECT_FINES = "SELECT patrons.name AS patron_name, books.title AS book_title, " +
            "books.due_date AS due_date, books.calculateFine() AS fine " +
            "FROM patrons JOIN borrowing_history ON patrons.id = borrowing_history.patron_id " +
            "JOIN books ON borrowing_history.book_id = books.id " +
            "WHERE books.due_date < NOW() AND books.available = FALSE";

    // ... (previous code)

    private static void generateAvailabilityReport(Connection connection) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(SELECT_AVAILABLE_BOOKS);
             ResultSet resultSet = statement.executeQuery()) {
            System.out.println("Available Books in the library:");
            while (resultSet.next()) {
                System.out.println("Title: " + resultSet.getString("title"));
                System.out.println("Author: " + resultSet.getString("author"));
                System.out.println("Genre: " + resultSet.getString("genre"));
                System.out.println();
            }
        }
    }

    private static void generateBorrowingHistoryReport(Connection connection) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(SELECT_BORROWING_HISTORY);
             ResultSet resultSet = statement.executeQuery()) {
            System.out.println("Borrowing History:");
            while (resultSet.next()) {
                System.out.println("Patron Name: " + resultSet.getString("patron_name"));
                System.out.println("Book Title: " + resultSet.getString("book_title"));
                System.out.println();
            }
        }
    }

    private static void generateFinesReport(Connection connection) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(SELECT_FINES);
             ResultSet resultSet = statement.executeQuery()) {
            System.out.println("Fines Report:");
            while (resultSet.next()) {
                System.out.println("Patron Name: " + resultSet.getString("patron_name"));
                System.out.println("Book Title: " + resultSet.getString("book_title"));
                System.out.println("Due Date: " + resultSet.getString("due_date"));
                System.out.println("Fine: $" + resultSet.getDouble("fine"));
                System.out.println();
            }
        }
    }

    // ... (previous code)
}

package persistence;

import java.sql.SQLException;
import java.util.List;

import domain.model.Book;

public interface BookPersistenceService {
    void saveBook(Book book) throws SQLException;

    void deleteBook(int id) throws SQLException;

    void updateBook(Book book) throws SQLException;

    Book getBookById(int id) throws SQLException;

    List<Book> getAllBooksFromDb() throws SQLException;

    List<Book> findBooksByPublisher(String publisherName) throws SQLException;

    double avgPriceBook() throws SQLException;

    double totalAmountTextBook() throws SQLException;

    double totalAmountReferenceBook() throws SQLException;
}

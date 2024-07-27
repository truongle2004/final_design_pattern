package domain;

import java.sql.SQLException;
import java.util.List;

import domain.model.Book;

public interface BookService {
    void addBook(Book book) throws SQLException;

    void removeBook(int id) throws SQLException;

    void editBook(Book book) throws SQLException;

    Book findBook(int id) throws SQLException;

    List<Book> getAllBooks() throws SQLException;

    List<Book> findBookByPublisher(String publisher) throws SQLException;

    double AvgPriceBook() throws SQLException;

    double getTotalAmountTextBook() throws SQLException;

    double getTotalAmountReferenceBook() throws SQLException;

}
package domain;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.model.Book;
import observer.Publisher;
import persistence.BookPersistenceServiceImpl;

public class BookServiceImpl extends Publisher implements BookService {
    private final BookPersistenceServiceImpl persistenceService;

    public BookServiceImpl(BookPersistenceServiceImpl persistenceService) {
        this.persistenceService = persistenceService;
    }

    @Override
    public List<Book> getAllBooks() throws SQLException {
        return persistenceService.getAllBooksFromDb();
    }

    @Override
    public void addBook(Book book) throws SQLException {
        persistenceService.saveBook(book);
        notifySubscribers();
    }

    @Override
    public void removeBook(int id) throws SQLException {
        persistenceService.deleteStudent(id);
        notifySubscribers();
    }

    @Override
    public void editBook(Book book) throws SQLException {
        persistenceService.updateBook(book);
        notifySubscribers();
    }

    @Override
    public Book findBook(int id) throws SQLException {
        return persistenceService.getBookById(id);
    }

    @Override
    public List<Book> findBookByPublisher(String publisher) throws SQLException {
        return persistenceService.findBooksByPublisher(publisher);
    }

    @Override
    public double AvgPriceBook() throws SQLException {
        return persistenceService.avgPriceBook();
    }

    @Override
    public double getTotalAmountTextBook() throws SQLException {
        return persistenceService.totalAmountTextBook();
    }

    @Override
    public double getTotalAmountReferenceBook() throws SQLException {
        return persistenceService.totalAmountReferenceBook();
    }
}

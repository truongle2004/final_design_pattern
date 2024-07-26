package domain;

import java.sql.SQLException;
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
    }

    @Override
    public Book findBook(int id) throws SQLException {
        return persistenceService.getBookById(id);
    }

    @Override
    public List<Book> getAllBooks() throws SQLException {
        return persistenceService.getAllBooksFromDb();
    }

    // @Override
    // public List<Student> searchStudentsByName(String name) {
    // List<Student> allStudents = persistenceService.getAllStudents();
    // List<Student> matchingStudents = new ArrayList<>();
    // for (Student student : allStudents) {
    // if (student.getName().contains(name)) {
    // matchingStudents.add(student);
    // }
    // }
    // return matchingStudents;
    // }

    // @Override
    // public List<Student> searchFullTextStudentsByName(String query) {
    // List<Student> allStudents = persistenceService.getAllStudents();
    // List<Student> matchingStudents = new ArrayList<>();

    // for (Student student : allStudents) {
    // if (containsIgnoreCase(student.getName(), query)) {
    // matchingStudents.add(student);
    // }
    // }

    // return matchingStudents;
    // }

}

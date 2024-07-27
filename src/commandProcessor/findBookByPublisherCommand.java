package commandProcessor;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.BookService;
import domain.model.Book;

public class findBookByPublisherCommand extends Command {
    private String publisher;
    private List<Book> books = new ArrayList<Book>();

    public findBookByPublisherCommand(String publisher, BookService bookService) {
        this.bookService = bookService;
        this.publisher = publisher;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public List<Book> getBooks() {
        return books;
    }

    @Override
    public void execute() throws SQLException {
        setBooks(bookService.findBookByPublisher(publisher));
    }

}

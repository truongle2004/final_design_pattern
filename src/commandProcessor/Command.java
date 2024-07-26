package commandProcessor;

import java.sql.SQLException;

import domain.BookService;
import domain.model.Book;

public abstract class Command {
    protected BookService bookService;
    protected Book book;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public abstract void execute() throws SQLException;
}

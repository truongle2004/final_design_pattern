package commandProcessor;

import java.sql.SQLException;

import domain.BookService;
import domain.model.Book;

public abstract class Command {
    protected BookService bookService;

    public Command(BookService bookService) {
        this.bookService = bookService;
    }

    public abstract void execute() throws SQLException;
}

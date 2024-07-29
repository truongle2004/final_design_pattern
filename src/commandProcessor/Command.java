package commandProcessor;

import java.sql.SQLException;

import domain.BookService;

public abstract class Command {
    protected BookService bookService;

    public Command(BookService bookService) {
        this.bookService = bookService;
    }

    public abstract void execute() throws SQLException;
}

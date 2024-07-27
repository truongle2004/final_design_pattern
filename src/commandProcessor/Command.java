package commandProcessor;

import java.sql.SQLException;

import domain.BookService;
import domain.model.Book;

public abstract class Command {
    protected BookService bookService;
    protected Book book;


    public abstract void execute() throws SQLException;
}

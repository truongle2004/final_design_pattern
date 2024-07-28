package commandProcessor;

import java.sql.SQLException;

import domain.BookService;
import domain.model.Book;

public class addBookCommand extends Command {
    private Book book;

    public addBookCommand(Book book, BookService bookService) {
        super(bookService);
    }

    @Override
    public void execute() throws SQLException {
        bookService.addBook(book);
    }
}

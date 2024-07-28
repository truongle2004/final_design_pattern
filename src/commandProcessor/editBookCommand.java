package commandProcessor;

import java.sql.SQLException;

import domain.BookService;
import domain.model.Book;

public class editBookCommand extends Command {
    private Book book;

    public editBookCommand(Book book, BookService bookService) {
        super(bookService);
    }

    @Override
    public void execute() throws SQLException {
        bookService.editBook(book);
    }
}

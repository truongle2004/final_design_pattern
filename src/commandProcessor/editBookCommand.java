package commandProcessor;

import java.sql.SQLException;

import domain.BookService;
import domain.model.Book;

public class editBookCommand extends Command {
    public editBookCommand(Book book, BookService bookService) {
        this.book = book;
        this.bookService = bookService;
    }

    @Override
    public void execute() throws SQLException {
        bookService.editBook(book);
    }

}

package commandProcessor;

import java.sql.SQLException;

import domain.BookService;
import domain.model.Book;

public class addBookCommand extends Command {
    public addBookCommand(Book book, BookService bookService) {
        this.book = book;
        this.bookService = bookService;
    }

    @Override
    public void execute() throws SQLException {
        bookService.addBook(book);
    }
}

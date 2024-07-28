package commandProcessor;

import java.sql.SQLException;

import domain.BookService;
import domain.model.Book;

public class findBookByIdCommand extends Command {
    private int id;
    private Book book;

    public findBookByIdCommand(int id, BookService bookService) {
        super(bookService);
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public void execute() throws SQLException {
        setBook(bookService.findBook(id));
    }

}

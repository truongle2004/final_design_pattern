package commandProcessor;

import java.sql.SQLException;

import domain.BookService;

public class findBookCommand extends Command {
    private int id;

    public findBookCommand(int id, BookService bookService) {
        this.id = id;
        this.bookService = bookService;
    }

    @Override
    public void execute() throws SQLException {
        setBook(bookService.findBook(id));
    }

}

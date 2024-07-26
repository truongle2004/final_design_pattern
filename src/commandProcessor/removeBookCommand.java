package commandProcessor;

import java.sql.SQLException;

import domain.BookService;

public class removeBookCommand extends Command {
    private int id;

    public removeBookCommand(int id, BookService bookService) {
        this.bookService = bookService;
        this.id = id;
    }

    @Override
    public void execute() throws SQLException {
        bookService.removeBook(id);
    }
}

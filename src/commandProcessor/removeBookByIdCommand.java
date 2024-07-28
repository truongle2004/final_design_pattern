package commandProcessor;

import java.sql.SQLException;

import domain.BookService;

public class removeBookByIdCommand extends Command {
    private int id;

    public removeBookByIdCommand(int id, BookService bookService) {
        super(bookService);
        this.id = id;
    }

    @Override
    public void execute() throws SQLException {
        bookService.removeBook(id);
    }
}

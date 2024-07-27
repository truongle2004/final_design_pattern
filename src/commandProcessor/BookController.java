package commandProcessor;

import java.sql.SQLException;

public class BookController {
    private static BookController bookController = null;

    public static BookController getInstance() {
        if (bookController == null) {
            bookController = new BookController();
        }
        return bookController;
    }

    public void execute(Command cmmd) throws SQLException {
        cmmd.execute();
    }

}

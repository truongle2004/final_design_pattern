package commandProcessor;

import java.sql.SQLException;

public class BookServiceController {
    private static BookServiceController bookController = null;

    public static BookServiceController getInstance() {
        if (bookController == null) {
            bookController = new BookServiceController();
        }
        return bookController;
    }

    public void execute(Command cmmd) throws SQLException {
        cmmd.execute();
    }

}

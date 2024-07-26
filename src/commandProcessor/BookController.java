package commandProcessor;

import java.sql.SQLException;

import presentation.BookManagementUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BookController implements ActionListener {
    private static BookController bookController = null;
    private BookManagementUI bookManagementUI;

    public BookController getInstance(BookManagementUI bookManagementUI) {
        if(bookController == null) {
            bookController = new BookController();
        }
        return bookController;
    }

    public void execute(Command cmmd) throws SQLException {
        cmmd.execute();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}

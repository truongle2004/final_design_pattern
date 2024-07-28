package commandProcessor;

import java.sql.SQLException;

import domain.BookService;

public class totalAmountTextBookCommand extends Command {
    private double totalAmountTextBook;

    public totalAmountTextBookCommand(BookService bookService) {
        super(bookService);
    }

    public double getTotalAmountTextBook() {
        return totalAmountTextBook;
    }

    public void setTotalAmountTextBook(double totalAmountTextBook) {
        this.totalAmountTextBook = totalAmountTextBook;
    }

    @Override
    public void execute() throws SQLException {
        setTotalAmountTextBook(bookService.getTotalAmountTextBook());
    }

}

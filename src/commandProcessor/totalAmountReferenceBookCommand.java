package commandProcessor;

import java.sql.SQLException;

import domain.BookService;

public class totalAmountReferenceBookCommand extends Command {
    private double totalAmountReferenceBook;

    public totalAmountReferenceBookCommand(BookService bookService) {
        this.bookService = bookService;
    }

    public double getTotalAmountReferenceBook() {
        return totalAmountReferenceBook;
    }

    public void setTotalAmountReferenceBook(double totalAmountReferenceBook) {
        this.totalAmountReferenceBook = totalAmountReferenceBook;
    }

    @Override
    public void execute() throws SQLException {
        setTotalAmountReferenceBook(bookService.getTotalAmountReferenceBook());
    }

}

package commandProcessor;

import java.sql.SQLException;

import domain.BookService;

public class AvgPriceBookCommand extends Command {
    private double avgPriceBook;

    public AvgPriceBookCommand(BookService bookService) {
        super(bookService);
    }

    public double getAvgPriceBook() {
        return avgPriceBook;
    }

    public void setAvgPriceBook(double avgPriceBook) {
        this.avgPriceBook = avgPriceBook;
    }

    @Override
    public void execute() throws SQLException {
        setAvgPriceBook(bookService.AvgPriceBook());
    }
}

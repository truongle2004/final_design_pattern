package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import domain.model.Book;
import domain.model.ReferenceBook;
import domain.model.TextBook;
import persistence.DbConnection.DbConnection;

public class BookPersistenceServiceImpl implements BookPersistenceService {
    private Connection connection = null;

    @Override
    public void saveBook(Book book) throws SQLException {
        String query = "INSERT INTO Book (id, date, price, quantity, publisher, tax, status) VALUES (?, ? , ?, ? , ? , ?, ?) ";
        connection = DbConnection.getDbConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setInt(1, book.getId());
        preparedStatement.setString(2, book.getDate());
        preparedStatement.setDouble(3, book.getPrice());
        preparedStatement.setInt(4, book.getQuantity());
        preparedStatement.setString(5, book.getPublisher());
        if (book instanceof TextBook) {
            TextBook textBook = (TextBook) book;
            preparedStatement.setNull(6, java.sql.Types.DECIMAL);
            preparedStatement.setString(7, textBook.getStatus());
        } else if (book instanceof ReferenceBook) {
            ReferenceBook referenceBook = (ReferenceBook) book;
            preparedStatement.setDouble(6, referenceBook.getTax());
            preparedStatement.setNull(7, java.sql.Types.VARCHAR);
        }

        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    @Override
    public void deleteStudent(int id) throws SQLException {
        String query = "delete from book where id = ?";
        connection = DbConnection.getDbConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        preparedStatement.close();
    }

    @Override
    public List<Book> getAllBooksFromDb() throws SQLException {
        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM book";
        connection = DbConnection.getDbConnection();
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String date = resultSet.getString("date");
                double price = resultSet.getDouble("price");
                int quantity = resultSet.getInt("quantity");
                String publisher = resultSet.getString("publisher");
                double tax = resultSet.getDouble("tax");
                String status = resultSet.getString("status");

                if (status != null) {
                    // It's a TextBook
                    TextBook textBook = new TextBook(id, date, price, quantity, publisher, status);
                    books.add(textBook);
                } else {
                    // It's a ReferenceBook
                    ReferenceBook referenceBook = new ReferenceBook(id, date, price, quantity, publisher, tax);
                    books.add(referenceBook);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
        }
        return books;
    }

    @Override
    public void updateBook(Book book) throws SQLException {
        String query = "UPDATE book SET date = ?, price = ?, quantity = ?, publisher = ?, tax = ?, status = ? WHERE id = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DbConnection.getDbConnection();
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, book.getDate());
            preparedStatement.setDouble(2, book.getPrice());
            preparedStatement.setInt(3, book.getQuantity());
            preparedStatement.setString(4, book.getPublisher());

            if (book instanceof TextBook) {
                TextBook textBook = (TextBook) book;
                preparedStatement.setNull(5, java.sql.Types.DECIMAL);
                preparedStatement.setString(6, textBook.getStatus());
            } else if (book instanceof ReferenceBook) {
                ReferenceBook referenceBook = (ReferenceBook) book;
                preparedStatement.setDouble(5, referenceBook.getTax());
                preparedStatement.setNull(6, java.sql.Types.VARCHAR);
            }

            preparedStatement.setInt(7, book.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    @Override
    public Book getBookById(int id) throws SQLException {
        String query = "SELECT id, date, price, quantity, publisher, tax, status FROM book WHERE id = ?";
        connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Book book = null;

        try {
            connection = DbConnection.getDbConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int bookId = resultSet.getInt("id");
                String date = resultSet.getString("date");
                double price = resultSet.getDouble("price");
                int quantity = resultSet.getInt("quantity");
                String publisher = resultSet.getString("publisher");
                double tax = resultSet.getDouble("tax");
                String status = resultSet.getString("status");

                if (status != null) {
                    book = new TextBook(bookId, date, price, quantity, publisher, status);
                } else {
                    book = new ReferenceBook(bookId, date, price, quantity, publisher, tax);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return book;
    }

    @Override
    public List<Book> findBooksByPublisher(String publisherName) throws SQLException {
        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM book WHERE publisher = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DbConnection.getDbConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, publisherName);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String date = resultSet.getString("date");
                double price = resultSet.getDouble("price");
                int quantity = resultSet.getInt("quantity");
                String publisher = resultSet.getString("publisher");
                double tax = resultSet.getDouble("tax");
                String status = resultSet.getString("status");

                if (status != null) {
                    TextBook textBook = new TextBook(id, date, price, quantity, publisher, status);
                    books.add(textBook);
                } else {
                    ReferenceBook referenceBook = new ReferenceBook(id, date, price, quantity, publisher, tax);
                    books.add(referenceBook);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
        return books;
    }

    @Override
    public double avgPriceBook() throws SQLException {
        String query = "SELECT AVG(price) AS avg_price FROM book WHERE status IS NULL";
        connection = DbConnection.getDbConnection();
        Statement statement = null;
        ResultSet resultSet = null;

        double avgPrice = 0.0;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                avgPrice = resultSet.getDouble("avg_price");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
        }

        return avgPrice;
    }

    @Override
    public double totalAmountTextBook() throws SQLException {
        String query = "SELECT * FROM book where tax is null";
        connection = DbConnection.getDbConnection();
        Statement statement = null;
        ResultSet resultSet = null;

        double totalAmount = 0.0;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                System.out.println(totalAmount);
                int id = resultSet.getInt("id");
                String date = resultSet.getString("date");
                double price = resultSet.getDouble("price");
                int quantity = resultSet.getInt("quantity");
                String publisher = resultSet.getString("publisher");
                String status = resultSet.getString("status");

                TextBook textBook = new TextBook(id, date, price, quantity, publisher,
                        status);
                totalAmount += textBook.calculateTotalAmount();

            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
        }
        return totalAmount;
    }

    @Override
    public double totalAmountReferenceBook() throws SQLException {
        String query = "SELECT * FROM book where status is null";
        connection = DbConnection.getDbConnection();
        Statement statement = null;
        ResultSet resultSet = null;

        double totalAmount = 0.0;

        try {
            System.out.println(totalAmount);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String date = resultSet.getString("date");
                double price = resultSet.getDouble("price");
                int quantity = resultSet.getInt("quantity");
                String publisher = resultSet.getString("publisher");

                double tax = resultSet.getDouble("tax");
                ReferenceBook referenceBook = new ReferenceBook(id, date, price, quantity, publisher, tax);
                totalAmount += referenceBook.calculateTotalAmount();

            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
        }
        return totalAmount;
    }

}

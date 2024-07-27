package presentation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import commandProcessor.AvgPriceBookCommand;
import commandProcessor.BookServiceController;
import commandProcessor.Command;
import commandProcessor.addBookCommand;
import commandProcessor.editBookCommand;
import commandProcessor.findBookByIdCommand;
import commandProcessor.findBookByPublisherCommand;
import commandProcessor.removeBookByIdCommand;
import commandProcessor.totalAmountReferenceBookCommand;
import commandProcessor.totalAmountTextBookCommand;
import domain.model.Book;
import domain.model.ReferenceBook;
import domain.model.TextBook;
import observer.Subscriber;
import persistence.*;
import persistence.DbConnection.DbConnection;
import domain.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BookManagementUI extends JFrame implements Subscriber {
    private BookServiceController bookController;
    private final JPanel searchPanel;
    private final BookService bookService;
    private final DefaultTableModel tableModel;
    private final JTable bookTable;
    private final JLabel idLabel, dateLabel, priceLabel, quantityLabel, publisherLabel, statusLabel, taxLabel,
            textBookLabel, referenceBookLabel, totalAmountOfTextBookLabel, totalAmountOfReferenceLabel, AvgLabel;
    private final JTextField idField, dateField, priceField, quantityField, publisherField, statusField, taxField,
            AvgField,
            totalAmountOfTextBookField, totalAmountOfReferenceBookField;
    private final JButton addButton, removeButton, editButton, findButton, clearButton, refreshTableButton, AvgButton,
            totalAmountTextBookButton, totalAmountReferenceButton;
    private final JCheckBox textBookCheckBox, referenceBookCheckBox;

    // search
    private final JLabel searchLabel;
    private final JTextField searchField;
    private final JButton searchButton;
    private final JPanel inputPanel;

    public BookManagementUI(BookService bookService, BookServiceController bookServiceController) throws SQLException {
        this.bookService = bookService;
        this.bookController = bookServiceController;
        ((BookServiceImpl) bookService).subscribe(this);

        // Initialize components
        idLabel = new JLabel("ID:");
        dateLabel = new JLabel("date:");
        priceLabel = new JLabel("price:");
        quantityLabel = new JLabel("quantity:");
        publisherLabel = new JLabel("publisher:");
        statusLabel = new JLabel("status:");
        taxLabel = new JLabel("tax:");
        textBookLabel = new JLabel("text book");
        referenceBookLabel = new JLabel("reference book");
        totalAmountOfTextBookLabel = new JLabel("total amount text book");
        totalAmountOfReferenceLabel = new JLabel("total amount of reference book");
        AvgLabel = new JLabel("Average total amount reference book");

        idField = new JTextField(10);
        dateField = new JTextField(20);
        priceField = new JTextField(20);
        quantityField = new JTextField(20);
        publisherField = new JTextField(20);
        statusField = new JTextField(20);
        taxField = new JTextField(20);
        totalAmountOfTextBookField = new JTextField(10);
        totalAmountOfReferenceBookField = new JTextField(10);
        AvgField = new JTextField(10);

        textBookCheckBox = new JCheckBox();
        referenceBookCheckBox = new JCheckBox();

        addButton = new JButton("Add");
        removeButton = new JButton("Remove");
        editButton = new JButton("Edit");
        findButton = new JButton("Find");
        clearButton = new JButton("Clear");
        refreshTableButton = new JButton("Refresh");
        totalAmountTextBookButton = new JButton("get");
        totalAmountReferenceButton = new JButton("get");
        AvgButton = new JButton("get");

        searchLabel = new JLabel("Search by publisher:");
        searchField = new JTextField(20);
        searchButton = new JButton("Search");

        String[] columnNames = { "id", "date", "price", "quantity", "publisher", "status", "tax", "type" };
        tableModel = new DefaultTableModel(columnNames, 0);
        bookTable = new JTable(tableModel);

        totalAmountTextBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    totalAmountTextBook();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }

        });

        totalAmountReferenceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    totalAmountReferenceBook();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }

        });
        refreshTableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    refreshbookTable();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }

        });

        clearButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                clearInputFields();
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    addBook();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    removeBook();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    editBook();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    findBookById();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    searchBookByPublisher();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        AvgButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    AvgPrice();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        // search
        searchPanel = new JPanel(new FlowLayout());
        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        // Table selection action
        bookTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                try {
                    showSelectedStudentInfo();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        // Layout setup
        inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);
        inputPanel.add(idLabel, gbc);
        gbc.gridx++;
        inputPanel.add(idField, gbc);
        gbc.gridy++;
        gbc.gridx = 0;
        inputPanel.add(dateLabel, gbc);
        gbc.gridx++;
        inputPanel.add(dateField, gbc);
        gbc.gridy++;
        gbc.gridx = 0;
        inputPanel.add(priceLabel, gbc);
        gbc.gridx++;
        inputPanel.add(priceField, gbc);
        gbc.gridy++;
        gbc.gridx = 0;
        inputPanel.add(quantityLabel, gbc);
        gbc.gridx++;
        inputPanel.add(quantityField, gbc);
        gbc.gridy++;
        gbc.gridx = 0;
        inputPanel.add(publisherLabel, gbc);
        gbc.gridx++;
        inputPanel.add(publisherField, gbc);
        gbc.gridy++;
        gbc.gridx = 0;
        inputPanel.add(statusLabel, gbc);
        gbc.gridx++;
        inputPanel.add(statusField, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        inputPanel.add(taxLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(taxField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        inputPanel.add(textBookCheckBox, gbc);
        gbc.gridx = 1;
        inputPanel.add(textBookLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        inputPanel.add(referenceBookCheckBox, gbc);
        gbc.gridx = 1;
        inputPanel.add(referenceBookLabel, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        inputPanel.add(searchLabel, gbc);
        gbc.gridx++;
        inputPanel.add(searchField, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        inputPanel.add(totalAmountOfTextBookLabel, gbc);
        gbc.gridx++;
        inputPanel.add(totalAmountOfTextBookField, gbc);
        gbc.gridx++;
        inputPanel.add(totalAmountTextBookButton, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        inputPanel.add(totalAmountOfReferenceLabel, gbc);
        gbc.gridx++;
        inputPanel.add(totalAmountOfReferenceBookField, gbc);
        gbc.gridx++;
        inputPanel.add(totalAmountReferenceButton, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        inputPanel.add(AvgLabel, gbc);
        gbc.gridx++;
        inputPanel.add(AvgField, gbc);
        gbc.gridx++;
        inputPanel.add(AvgButton, gbc);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(editButton);
        buttonPanel.add(findButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(refreshTableButton);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(new JScrollPane(bookTable), BorderLayout.CENTER);
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        // search
        // mainPanel.add(searchPanel);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        this.setTitle("Book Management System");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 700);
        this.add(mainPanel);
        this.setVisible(true);

        refreshbookTable();
    }

    private void addBook() throws SQLException {
        try {
            int id = Integer.parseInt(idField.getText());
            double price = Double.parseDouble(priceField.getText());
            int quantity = Integer.parseInt(quantityField.getText());
            String publisher = publisherField.getText();
            String date = dateField.getText();
            bookController = BookServiceController.getInstance();
            if (textBookCheckBox.isSelected()) {
                String status = statusField.getText();
                Command command = new addBookCommand(new TextBook(id, date, price, quantity, publisher, status),
                        bookService);
                bookController.execute(command);
            } else {

                double tax = Double.parseDouble(taxField.getText());
                Command command = new addBookCommand(new ReferenceBook(id, date, price, quantity, publisher, tax),
                        bookService);
                bookController.execute(command);
            }

            // Clear input fields
            idField.setText("");
            dateField.setText("");
            priceField.setText("");
            quantityField.setText("");
            publisherField.setText("");
            statusField.setText("");

            // Refresh the table
            refreshbookTable();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input");
        }
    }

    private void removeBook() throws SQLException {
        int selectedRow = bookTable.getSelectedRow();
        if (selectedRow != -1) {
            int bookId = (int) bookTable.getValueAt(selectedRow, 0);
            bookController = BookServiceController.getInstance();
            Command command = new removeBookByIdCommand(bookId, bookService);
            bookController.execute(command);
            clearInputFields();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a book to remove.");
        }
    }

    private void editBook() throws SQLException {
        int selectedRow = bookTable.getSelectedRow();
        if (selectedRow != -1) {
            try {
                int id = Integer.parseInt(idField.getText());
                double price = Double.parseDouble(priceField.getText());
                int quantity = Integer.parseInt(quantityField.getText());
                String publisher = publisherField.getText();
                String date = dateField.getText();
                bookController = BookServiceController.getInstance();
                if (textBookCheckBox.isSelected()) {
                    String status = statusField.getText();
                    Command command = new editBookCommand(new TextBook(id, date, price, quantity, publisher, status),
                            bookService);
                    bookController.execute(command);
                } else {

                    double tax = Double.parseDouble(taxField.getText());
                    Command command = new editBookCommand(new ReferenceBook(id, date, price, quantity, publisher, tax),
                            bookService);
                    bookController.execute(command);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input for marks or ID. Please enter valid numbers.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a book to edit.");
        }
    }

    private void findBookById() throws SQLException {

        String idStr = JOptionPane.showInputDialog(this, "Enter the Book ID to find:");
        if (idStr != null && !idStr.isEmpty()) {
            try {
                bookController = BookServiceController.getInstance();
                Command command = new findBookByIdCommand(Integer.parseInt(idStr), bookService);
                bookController.execute(command);
                Book book = ((findBookByIdCommand) command).getBook();
                populateInputFields(book);
                if (book != null) {
                    populateInputFields(book);
                } else {
                    JOptionPane.showMessageDialog(this, "Book not found with ID: " + idStr);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input for ID. Please enter a valid number.");
            }
        }
    }

    private void showSelectedStudentInfo() throws SQLException {
        int selectedRow = bookTable.getSelectedRow();
        if (selectedRow != -1) {
            int bookId = (int) bookTable.getValueAt(selectedRow, 0);
            bookController = BookServiceController.getInstance();
            Command command = new findBookByIdCommand(bookId, bookService);
            findBookByIdCommand new_command = (findBookByIdCommand) command;
            bookController.execute(command);
            Book book = new_command.getBook();
            if (book != null) {
                populateInputFields(book);
            }
        }
    }

    private void populateInputFields(Book book) {
        idField.setText(String.valueOf(book.getId()));
        dateField.setText(book.getDate());
        priceField.setText(String.valueOf(book.getPrice()));
        quantityField.setText(String.valueOf(book.getQuantity()));
        publisherField.setText(book.getPublisher());

        if (book instanceof TextBook) {
            TextBook textBook = (TextBook) book;
            statusField.setText(textBook.getStatus());
            taxField.setText("");
            textBookCheckBox.setSelected(true);
            referenceBookCheckBox.setSelected(false);
        } else if (book instanceof ReferenceBook) {
            ReferenceBook referenceBook = (ReferenceBook) book;
            taxField.setText(String.valueOf(referenceBook.getTax()));
            statusField.setText("");
            referenceBookCheckBox.setSelected(true);
            textBookCheckBox.setSelected(false);
        } else {
            statusField.setText("");
            taxField.setText("");
        }
    }

    private void clearInputFields() {
        idField.setText("");
        dateField.setText("");
        priceField.setText("");
        quantityField.setText("");
        publisherField.setText("");
        statusField.setText("");
        taxField.setText("");
        searchField.setText("");
        textBookCheckBox.setSelected(false);
        referenceBookCheckBox.setSelected(false);
    }

    private void refreshbookTable() throws SQLException {

        tableModel.setRowCount(0);

        List<Book> books = bookService.getAllBooks();
        updateTableData(books);
    }

    public void AvgPrice() throws SQLException {
        bookController = BookServiceController.getInstance();
        Command command = new AvgPriceBookCommand(bookService);
        bookController.execute(command);
        String result = Double.toString(((AvgPriceBookCommand) command).getAvgPriceBook());
        AvgField.setText(result);
    }

    public void totalAmountReferenceBook() throws SQLException {
        bookController = BookServiceController.getInstance();
        Command totalAmountCommand = new totalAmountReferenceBookCommand(bookService);
        bookController.execute(totalAmountCommand);

        String result = Double
                .toString(((totalAmountReferenceBookCommand) totalAmountCommand).getTotalAmountReferenceBook());
        totalAmountOfReferenceBookField.setText(result);
    }

    public void totalAmountTextBook() throws SQLException {
        bookController = BookServiceController.getInstance();
        Command totalAmountCommand = new totalAmountTextBookCommand(bookService);
        bookController.execute(totalAmountCommand);

        String result = Double.toString(((totalAmountTextBookCommand) totalAmountCommand).getTotalAmountTextBook());
        totalAmountOfTextBookField.setText(result);
    }

    public void updateTableData(List<Book> books) {
        for (Book book : books) {
            if (book instanceof TextBook) {
                TextBook textBook = (TextBook) book;
                Object[] rowData = {
                        textBook.getId(),
                        textBook.getDate(),
                        textBook.getPrice(),
                        textBook.getQuantity(),
                        textBook.getPublisher(),
                        textBook.getStatus(),
                        null,
                        "text book"

                };
                tableModel.addRow(rowData);
            } else if (book instanceof ReferenceBook) {
                ReferenceBook referenceBook = (ReferenceBook) book;
                Object[] rowData = {
                        referenceBook.getId(),
                        referenceBook.getDate(),
                        referenceBook.getPrice(),
                        referenceBook.getQuantity(),
                        referenceBook.getPublisher(),
                        null,
                        referenceBook.getTax(),
                        "reference book"
                };
                tableModel.addRow(rowData);
            }
        }
    }

    private void searchBookByPublisher() throws SQLException {
        String publisher = searchField.getText();
        bookController = BookServiceController.getInstance();
        Command command = new findBookByPublisherCommand(publisher, bookService);
        bookController.execute(command);

        findBookByPublisherCommand newCommand = (findBookByPublisherCommand) command;
        List<Book> books = newCommand.getBooks();

        tableModel.setRowCount(0);

        if (books.size() > 0) {
            updateTableData(books);
        } else {
            tableModel.addRow(new Object[] { "No results found", "", "", "", "", "", "", "" });
        }
    }

    public static void main(String[] args) throws SQLException {
        BookPersistenceServiceImpl bookPersistenceServiceImpl = new BookPersistenceServiceImpl();
        BookService bookService = new BookServiceImpl(bookPersistenceServiceImpl);
        BookServiceController bookServiceController = BookServiceController.getInstance();
        BookManagementUI ui = new BookManagementUI(bookService, bookServiceController);
    }

    @Override
    public void update() throws SQLException {
        refreshbookTable();
    }
}

package presentation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import commandProcessor.Command;
import commandProcessor.BookController;
import commandProcessor.addBookCommand;
import commandProcessor.findBookCommand;
import commandProcessor.removeBookCommand;
import domain.BookService;
import domain.model.Book;
import domain.model.ReferenceBook;
import domain.model.TextBook;
import observer.Subscriber;
import persistence.*;
import domain.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class BookManagementUI extends JFrame implements Subscriber {
    private BookController commandProcessor = null;
    private final BookService bookService;
    private final DefaultTableModel tableModel;
    private final JTable bookTable;
    private final JLabel idLabel, dateLabel, priceLabel, quantityLabel, publisherLabel, statusLabel, taxLabel,
            textBookLabel, referenceBookLabel;
    private final JTextField idField, dateField, priceField, quantityField, publisherField, statusField, taxField;
    private final JButton addButton, removeButton, editButton, findButton;
    private final JCheckBox textBookCheckBox, referenceBookCheckBox;

    // search
    private final JLabel searchLabel;
    private final JTextField searchField;
    private final JButton searchButton;
    // TODO: add textfield show total amount
    // TODO: add button find book by name of publisher
    // TODO: using dto class
    // TODO: Conserdering about command pattern

    public BookManagementUI(BookService bookService) throws SQLException {
        this.bookService = bookService;
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

        idField = new JTextField(10);
        dateField = new JTextField(20);
        priceField = new JTextField(20);
        quantityField = new JTextField(20);
        publisherField = new JTextField(20);
        statusField = new JTextField(20);
        taxField = new JTextField(20);

        textBookCheckBox = new JCheckBox();
        referenceBookCheckBox = new JCheckBox();

        addButton = new JButton("Add");
        removeButton = new JButton("Remove");
        editButton = new JButton("Edit");
        findButton = new JButton("Find");

        searchLabel = new JLabel("Search by id:");
        searchField = new JTextField(20);
        searchButton = new JButton("Search");

        String[] columnNames = { "id", "date", "price", "quantity", "publisher", "status", "tax", "type" };
        tableModel = new DefaultTableModel(columnNames, 0);
        bookTable = new JTable(tableModel);

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

        // editButton.addActionListener(new ActionListener() {
        // @Override
        // public void actionPerformed(ActionEvent e) {
        // editStudent();
        // }
        // });

        // findButton.addActionListener(new ActionListener() {
        // @Override
        // public void actionPerformed(ActionEvent e) {
        // findStudent();
        // }
        // });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    searchBookById();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        // search
        JPanel searchPanel = new JPanel(new FlowLayout());
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
        JPanel inputPanel = new JPanel(new GridBagLayout());
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

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(editButton);
        buttonPanel.add(findButton);
        buttonPanel.add(searchButton);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(new JScrollPane(bookTable), BorderLayout.CENTER);
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        // search
        // mainPanel.add(searchPanel);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        this.setTitle("Student Management System");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 500);
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
            commandProcessor = BookController.makeCommandProcessor();
            if (textBookCheckBox.isSelected()) {
                String status = statusField.getText();
                Command command = new addBookCommand(new TextBook(id, date, price, quantity, publisher, status),
                        bookService);
                commandProcessor.execute(command);
            } else {

                double tax = Double.parseDouble(taxField.getText());
                Command command = new addBookCommand(new ReferenceBook(id, date, price, quantity, publisher, tax),
                        bookService);
                commandProcessor.execute(command);
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
            JOptionPane.showMessageDialog(this, "Invalid input for marks or ID. Please enter valid numbers.");
        }
    }

    private void removeBook() throws SQLException {
        int selectedRow = bookTable.getSelectedRow();
        if (selectedRow != -1) {
            int bookId = (int) bookTable.getValueAt(selectedRow, 0);
            commandProcessor = BookController.makeCommandProcessor();
            Command command = new removeBookCommand(bookId, bookService);
            commandProcessor.execute(command);
            clearInputFields();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a student to remove.");
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
                commandProcessor = BookController.makeCommandProcessor();
                if (textBookCheckBox.isSelected()) {
                    String status = statusField.getText();
                    Command command = new addBookCommand(new TextBook(id, date, price, quantity, publisher, status),
                            bookService);
                    commandProcessor.execute(command);
                } else {

                    double tax = Double.parseDouble(taxField.getText());
                    Command command = new addBookCommand(new ReferenceBook(id, date, price, quantity, publisher, tax),
                            bookService);
                    commandProcessor.execute(command);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input for marks or ID. Please enter valid numbers.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a student to edit.");
        }
    }

    // private void findStudent() {
    // String idStr = JOptionPane.showInputDialog(this, "Enter the student ID to
    // find:");
    // if (idStr != null && !idStr.isEmpty()) {
    // try {
    // int id = Integer.parseInt(idStr);
    // Student student = studentService.findStudent(id);
    // if (student != null) {
    // populateInputFields(student);
    // } else {
    // JOptionPane.showMessageDialog(this, "Student not found with ID: " + id);
    // }
    // } catch (NumberFormatException ex) {
    // JOptionPane.showMessageDialog(this, "Invalid input for ID. Please enter a
    // valid number.");
    // }
    // }
    // }

    private void showSelectedStudentInfo() throws SQLException {
        int selectedRow = bookTable.getSelectedRow();
        if (selectedRow != -1) {
            int bookId = (int) bookTable.getValueAt(selectedRow, 0);
            commandProcessor = BookController.makeCommandProcessor();
            Command command = new findBookCommand(bookId, bookService);
            commandProcessor.execute(command);
            Book book = command.getBook();
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
        } else if (book instanceof ReferenceBook) {
            ReferenceBook referenceBook = (ReferenceBook) book;
            taxField.setText(String.valueOf(referenceBook.getTax()));
            statusField.setText("");
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
    }

    private void refreshbookTable() throws SQLException {

        tableModel.setRowCount(0);

        List<Book> books = bookService.getAllBooks();
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

    private void searchBookById() throws SQLException {
        int bookId = Integer.parseInt(searchField.getText());
        commandProcessor = BookController.makeCommandProcessor();
        Command command = new findBookCommand(bookId, bookService);
        commandProcessor.execute(command);
        Book book = command.getBook();
        if (book != null) {
            populateInputFields(book);
        }
    }

    // private void populatebookTable(List<Student> students) {
    // // Clear existing data in the table
    // tableModel.setRowCount(0);

    // // Populate the table with matching students
    // for (Student student : students) {
    // Object[] rowData = { student.getId(), student.getName(), student.getMajor(),
    // student.getJavaMark(),
    // student.getHtmlMark(), student.getCssMark() };
    // tableModel.addRow(rowData);
    // }
    // }

    public static void main(String[] args) throws SQLException {
        BookPersistenceServiceImpl bookPersistenceServiceImpl = new BookPersistenceServiceImpl();
        BookService bookService = new BookServiceImpl(bookPersistenceServiceImpl);
        BookManagementUI ui = new BookManagementUI(bookService);
    }

    @Override
    public void update() throws SQLException {
        refreshbookTable();
    }
}

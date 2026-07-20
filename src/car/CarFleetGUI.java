package car;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CarFleetGUI extends JFrame {

    private CarManager manager = new CarManager();

    // Input fields
    private JTextField idField = new JTextField();
    private JTextField plateField = new JTextField();
    private JTextField brandField = new JTextField();
    private JTextField modelField = new JTextField();
    private JTextField yearField = new JTextField();
    private JTextField rentField = new JTextField();
    private JTextField mileageField = new JTextField();
    private JTextField searchField = new JTextField(20);

    // Combo boxes
    private JComboBox<String> typeBox;
    private JComboBox<String> statusBox;

    // Table
    private JTable carTable;
    private DefaultTableModel tableModel;

    // Colors
    private Color backgroundColor = new Color(240, 244, 248);
    private Color darkBlue = new Color(40, 70, 110);
    private Color greenColor = new Color(60, 150, 90);
    private Color blueColor = new Color(65, 120, 190);
    private Color redColor = new Color(190, 70, 70);
    private Color grayColor = new Color(110, 115, 120);

    public CarFleetGUI() {

        setTitle("Car Fleet Management System");
        setSize(1100, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main tabs
        JTabbedPane tabs = new JTabbedPane();

        tabs.setFont(new Font("Arial", Font.BOLD, 15));

        tabs.addTab("Car Form", createFormPanel());
        tabs.addTab("View Cars", createTablePanel());
        tabs.addTab("Search", createSearchPanel());
        tabs.addTab("Status Update", createStatusPanel());
        tabs.addTab("Report", createReportPanel());

        add(tabs);

        refreshTable();
    }

    // Create Add, Edit and Delete form
    private JPanel createFormPanel() {

        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBackground(backgroundColor);
        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(20, 30, 20, 30)
        );

        JLabel title = new JLabel("Car Information");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 25));
        title.setForeground(darkBlue);

        mainPanel.add(title, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(9, 2, 15, 15));
        formPanel.setBackground(backgroundColor);

        String[] carTypes = {
            "Sedan",
            "SUV",
            "Van",
            "Luxury",
            "Economy"
        };

        typeBox = new JComboBox<String>(carTypes);

        String[] statuses = {
            "AVAILABLE",
            "BOOKED",
            "RENTED",
            "MAINTENANCE",
            "RETIRED"
        };

        statusBox = new JComboBox<String>(statuses);

        addFormRow(formPanel, "Car ID:", idField);
        addFormRow(formPanel, "Plate Number:", plateField);
        addFormRow(formPanel, "Brand:", brandField);
        addFormRow(formPanel, "Model:", modelField);
        addFormRow(formPanel, "Manufacturing Year:", yearField);
        addFormRow(formPanel, "Car Type:", typeBox);
        addFormRow(formPanel, "Daily Rental Price:", rentField);
        addFormRow(formPanel, "Mileage:", mileageField);
        addFormRow(formPanel, "Status:", statusBox);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(backgroundColor);

        JButton addButton = createButton("Add Car", greenColor);
        JButton editButton = createButton("Edit Car", blueColor);
        JButton deleteButton = createButton("Delete Car", redColor);
        JButton clearButton = createButton("Clear", grayColor);

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);

        addButton.addActionListener(e -> addCar());
        editButton.addActionListener(e -> editCar());
        deleteButton.addActionListener(e -> deleteCar());
        clearButton.addActionListener(e -> clearFields());

        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        return mainPanel;
    }

    // Add label and input component to the form
    private void addFormRow(
            JPanel panel,
            String text,
            JComponent component) {

        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 15));
        label.setForeground(new Color(50, 50, 50));

        component.setFont(new Font("Arial", Font.PLAIN, 15));

        panel.add(label);
        panel.add(component);
    }

    // Create button with same style
    private JButton createButton(String text, Color color) {

        JButton button = new JButton(text);

        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setPreferredSize(new Dimension(135, 40));
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);

        return button;
    }

    // Create cars table
    private JPanel createTablePanel() {

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(backgroundColor);
        panel.setBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        );

        JLabel title = new JLabel("All Cars");
        title.setFont(new Font("Arial", Font.BOLD, 23));
        title.setForeground(darkBlue);
        title.setHorizontalAlignment(SwingConstants.CENTER);

        String[] columns = {
            "Car ID",
            "Plate",
            "Brand",
            "Model",
            "Year",
            "Type",
            "Daily Rent",
            "Mileage",
            "Status"
        };

        tableModel = new DefaultTableModel(columns, 0) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        carTable = new JTable(tableModel);

        carTable.setFont(new Font("Arial", Font.PLAIN, 14));
        carTable.setRowHeight(28);
        carTable.getTableHeader().setFont(
                new Font("Arial", Font.BOLD, 14)
        );

        carTable.getSelectionModel()
                .addListSelectionListener(e -> {

            if (!e.getValueIsAdjusting()) {
                copySelectedCarToFields();
            }
        });

        JButton refreshButton =
                createButton("Refresh Table", darkBlue);

        refreshButton.addActionListener(e -> refreshTable());

        panel.add(title, BorderLayout.NORTH);
        panel.add(new JScrollPane(carTable), BorderLayout.CENTER);
        panel.add(refreshButton, BorderLayout.SOUTH);

        return panel;
    }

    // Create search page
    private JPanel createSearchPanel() {

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(backgroundColor);
        panel.setBorder(
                BorderFactory.createEmptyBorder(30, 30, 30, 30)
        );

        JPanel topPanel = new JPanel();
        topPanel.setBackground(backgroundColor);

        JLabel label = new JLabel("Search:");
        label.setFont(new Font("Arial", Font.BOLD, 16));

        searchField.setFont(new Font("Arial", Font.PLAIN, 15));

        JButton searchButton =
                createButton("Search", blueColor);

        JButton showAllButton =
                createButton("Show All", grayColor);

        topPanel.add(label);
        topPanel.add(searchField);
        topPanel.add(searchButton);
        topPanel.add(showAllButton);

        String[] columns = {
            "Car ID",
            "Plate",
            "Brand",
            "Model",
            "Year",
            "Type",
            "Daily Rent",
            "Mileage",
            "Status"
        };

        DefaultTableModel searchModel =
                new DefaultTableModel(columns, 0) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable searchTable = new JTable(searchModel);

        searchTable.setFont(new Font("Arial", Font.PLAIN, 14));
        searchTable.setRowHeight(28);

        searchButton.addActionListener(e -> {

            String word = searchField.getText().trim();

            if (word.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter a search value."
                );

                return;
            }

            ArrayList<Car> result =
                    manager.searchCars(word);

            loadCarsToTable(searchModel, result);

            if (result.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "No cars were found."
                );
            }
        });

        showAllButton.addActionListener(e -> {

            searchField.setText("");

            loadCarsToTable(
                    searchModel,
                    manager.getAllCars()
            );
        });

        loadCarsToTable(
                searchModel,
                manager.getAllCars()
        );

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(searchTable), BorderLayout.CENTER);

        return panel;
    }

    // Create status update page
    private JPanel createStatusPanel() {

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(backgroundColor);
        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(50, 100, 50, 100)
        );

        JLabel title = new JLabel("Update Car Status");
        title.setFont(new Font("Arial", Font.BOLD, 25));
        title.setForeground(darkBlue);
        title.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel form = new JPanel(new GridLayout(3, 2, 15, 20));
        form.setBackground(backgroundColor);

        JTextField statusIdField = new JTextField();

        JComboBox<String> newStatusBox =
                new JComboBox<String>(
                        new String[]{
                            "AVAILABLE",
                            "BOOKED",
                            "RENTED",
                            "MAINTENANCE",
                            "RETIRED"
                        }
                );

        JLabel idLabel = new JLabel("Car ID:");
        JLabel statusLabel = new JLabel("New Status:");

        idLabel.setFont(new Font("Arial", Font.BOLD, 16));
        statusLabel.setFont(new Font("Arial", Font.BOLD, 16));

        statusIdField.setFont(new Font("Arial", Font.PLAIN, 15));
        newStatusBox.setFont(new Font("Arial", Font.PLAIN, 15));

        JButton updateButton =
                createButton("Update Status", blueColor);

        form.add(idLabel);
        form.add(statusIdField);

        form.add(statusLabel);
        form.add(newStatusBox);

        form.add(new JLabel(""));
        form.add(updateButton);

        updateButton.addActionListener(e -> {

            String id = statusIdField.getText().trim();

            if (id.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter the Car ID."
                );

                return;
            }

            String newStatus =
                    newStatusBox.getSelectedItem().toString();

            boolean updated =
                    manager.updateStatus(id, newStatus);

            if (updated) {

                JOptionPane.showMessageDialog(
                        this,
                        "Car status updated successfully."
                );

                statusIdField.setText("");
                refreshTable();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Car ID not found."
                );
            }
        });

        mainPanel.add(title, BorderLayout.NORTH);
        mainPanel.add(form, BorderLayout.CENTER);
            return mainPanel;
    }

    // Create report page
    private JPanel createReportPanel() {

        JPanel panel = new JPanel(new BorderLayout(15, 15));
        panel.setBackground(backgroundColor);
        panel.setBorder(
                BorderFactory.createEmptyBorder(30, 30, 30, 30)
        );

        JLabel title = new JLabel("Car Fleet Report");
        title.setFont(new Font("Arial", Font.BOLD, 25));
        title.setForeground(darkBlue);
        title.setHorizontalAlignment(SwingConstants.CENTER);

        JTextArea reportArea = new JTextArea();

        reportArea.setEditable(false);
        reportArea.setFont(
                new Font("Monospaced", Font.PLAIN, 17)
        );

        reportArea.setBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        );

        JButton reportButton =
                createButton("Generate Report", darkBlue);

        reportButton.addActionListener(e -> {

            String report =
                    "CAR FLEET REPORT\n"
                    + "----------------------------------\n"
                    + "Total Cars: "
                    + manager.getAllCars().size()
                    + "\n\n"
                    + "Available Cars: "
                    + manager.countByStatus("AVAILABLE")
                    + "\n"
                    + "Booked Cars: "
                    + manager.countByStatus("BOOKED")
                    + "\n"
                    + "Rented Cars: "
                    + manager.countByStatus("RENTED")
                    + "\n"
                    + "Maintenance Cars: "
                    + manager.countByStatus("MAINTENANCE")
                    + "\n"
                    + "Retired Cars: "
                    + manager.countByStatus("RETIRED");

            reportArea.setText(report);
        });

        panel.add(title, BorderLayout.NORTH);
        panel.add(new JScrollPane(reportArea), BorderLayout.CENTER);
        panel.add(reportButton, BorderLayout.SOUTH);

        return panel;
    }

    // Add new car
    private void addCar() {

        try {

            if (!fieldsAreValid()) {
                return;
            }

            Car car = getCarFromFields();

            boolean added = manager.addCar(car);

            if (added) {

                JOptionPane.showMessageDialog(
                        this,
                        "Car added successfully."
                );

                refreshTable();
                clearFields();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Car ID already exists."
                );
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Year, rent, and mileage must be numbers."
            );
        }
    }

    // Edit existing car
    private void editCar() {

        try {

            if (!fieldsAreValid()) {
                return;
            }

            Car car = getCarFromFields();

            boolean updated = manager.updateCar(car);

            if (updated) {

                JOptionPane.showMessageDialog(
                        this,
                        "Car updated successfully."
                );

                refreshTable();
                clearFields();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Car ID not found."
                );
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Year, rent, and mileage must be numbers."
            );
        }
    }

    // Delete car
    private void deleteCar() {

        String id = idField.getText().trim();

        if (id.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter the Car ID."
            );

            return;
        }

        int answer = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to delete this car?",
                "Delete Car",
                JOptionPane.YES_NO_OPTION
        );

        if (answer == JOptionPane.YES_OPTION) {

            boolean deleted = manager.deleteCar(id);

            if (deleted) {

                JOptionPane.showMessageDialog(
                        this,
                        "Car deleted successfully."
                );

                refreshTable();
                clearFields();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Car ID not found."
                );
            }
        }
    }

    // Check fields
    private boolean fieldsAreValid() {

        if (idField.getText().trim().isEmpty()
                || plateField.getText().trim().isEmpty()
                || brandField.getText().trim().isEmpty()
                || modelField.getText().trim().isEmpty()
                || yearField.getText().trim().isEmpty()
                || rentField.getText().trim().isEmpty()
                || mileageField.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please fill in all fields."
            );

            return false;
        }

        int year = Integer.parseInt(
                yearField.getText().trim()
        );

        double rent = Double.parseDouble(
                rentField.getText().trim()
        );

        int mileage = Integer.parseInt(
                mileageField.getText().trim()
        );

        if (year < 1900 || year > 2030) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid year."
            );

            return false;
        }

        if (rent < 0 || mileage < 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "Rent and mileage cannot be negative."
            );

            return false;
        }

        return true;
    }

    // Read information from input fields
    private Car getCarFromFields() {

        return new Car(
                idField.getText().trim(),
                plateField.getText().trim(),
                brandField.getText().trim(),
                modelField.getText().trim(),
                Integer.parseInt(yearField.getText().trim()),
                typeBox.getSelectedItem().toString(),
                Double.parseDouble(rentField.getText().trim()),
                Integer.parseInt(mileageField.getText().trim()),
                statusBox.getSelectedItem().toString()
        );
    }

    // Show cars in main table
    private void refreshTable() {

        if (tableModel != null) {

            loadCarsToTable(
                    tableModel,
                    manager.getAllCars()
            );
        }
    }

    // Add cars to any table
    private void loadCarsToTable(
            DefaultTableModel model,
            ArrayList<Car> cars) {

        model.setRowCount(0);

        for (Car car : cars) {

            model.addRow(new Object[]{
                car.getCarId(),
                car.getPlateNumber(),
                car.getBrand(),
                car.getModel(),
                car.getYear(),
                car.getCarType(),
                car.getDailyRent(),
                car.getMileage(),
                car.getStatus()
            });
        }
    }

    // Copy selected table row to fields
    private void copySelectedCarToFields() {

        int row = carTable.getSelectedRow();

        if (row >= 0) {

            idField.setText(
                    tableModel.getValueAt(row, 0).toString()
            );

            plateField.setText(
                    tableModel.getValueAt(row, 1).toString()
            );

            brandField.setText(
                    tableModel.getValueAt(row, 2).toString()
            );

            modelField.setText(
                    tableModel.getValueAt(row, 3).toString()
            );yearField.setText(
                    tableModel.getValueAt(row, 4).toString()
            );

            typeBox.setSelectedItem(
                    tableModel.getValueAt(row, 5).toString()
            );

            rentField.setText(
                    tableModel.getValueAt(row, 6).toString()
            );

            mileageField.setText(
                    tableModel.getValueAt(row, 7).toString()
            );

            statusBox.setSelectedItem(
                    tableModel.getValueAt(row, 8).toString()
            );
        }
    }

    // Clear input fields
    private void clearFields() {

        idField.setText("");
        plateField.setText("");
        brandField.setText("");
        modelField.setText("");
        yearField.setText("");
        rentField.setText("");
        mileageField.setText("");

        typeBox.setSelectedIndex(0);
        statusBox.setSelectedIndex(0);

        if (carTable != null) {
            carTable.clearSelection();
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            CarFleetGUI window = new CarFleetGUI();
            window.setVisible(true);
        });
    }
}
package car;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class CarFleetGUI extends JFrame {
    private final CarManager manager = new CarManager();

    private final JTextField txtCarId = new JTextField();
    private final JTextField txtPlate = new JTextField();
    private final JTextField txtBrand = new JTextField();
    private final JTextField txtModel = new JTextField();
    private final JTextField txtYear = new JTextField();
    private final JTextField txtRent = new JTextField();
    private final JTextField txtMileage = new JTextField();
    private final JTextField txtSearch = new JTextField(25);

    private final JComboBox<String> cmbType = new JComboBox<String>(
            new String[]{"Sedan", "SUV", "Van", "Luxury", "Economy"});

    private final JComboBox<String> cmbStatus = new JComboBox<String>(
            new String[]{"AVAILABLE", "BOOKED", "RENTED", "MAINTENANCE", "RETIRED"});

    private final DefaultTableModel tableModel = new DefaultTableModel(
            new String[]{"Car ID", "Plate", "Brand", "Model", "Year",
                         "Type", "Daily Rent", "Mileage", "Status"}, 0) {
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    private final JTable table = new JTable(tableModel);

    public CarFleetGUI() {
        setTitle("Car Fleet Management System");
        setSize(1050, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Car Form", createFormPanel());
        tabs.addTab("View Cars", createTablePanel());
        tabs.addTab("Search", createSearchPanel());
        tabs.addTab("Status Update", createStatusPanel());
        tabs.addTab("Report", createReportPanel());

        add(tabs);
        refreshTable();
    }

    private JPanel createFormPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel form = new JPanel(new GridLayout(9, 2, 10, 10));

        form.add(new JLabel("Car ID:")); form.add(txtCarId);
        form.add(new JLabel("Plate Number:")); form.add(txtPlate);
        form.add(new JLabel("Brand:")); form.add(txtBrand);
        form.add(new JLabel("Model:")); form.add(txtModel);
        form.add(new JLabel("Manufacturing Year:")); form.add(txtYear);
        form.add(new JLabel("Car Type:")); form.add(cmbType);
        form.add(new JLabel("Daily Rental Price:")); form.add(txtRent);
        form.add(new JLabel("Mileage:")); form.add(txtMileage);
        form.add(new JLabel("Status:")); form.add(cmbStatus);

        JPanel buttons = new JPanel();
        JButton add = new JButton("Add Car");
        JButton edit = new JButton("Edit Car");
        JButton delete = new JButton("Delete Car");
        JButton clear = new JButton("Clear");

        buttons.add(add);
        buttons.add(edit);
        buttons.add(delete);
        buttons.add(clear);

        add.addActionListener(e -> addCar());
        edit.addActionListener(e -> editCar());
        delete.addActionListener(e -> deleteCar());
        clear.addActionListener(e -> clearFields());

        panel.add(form, BorderLayout.CENTER);
        panel.add(buttons, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        table.setRowHeight(24);

        table.getSelectionModel().addListSelectionListener(e -> {
            int row = table.getSelectedRow();

            if (row >= 0) {
                txtCarId.setText(tableModel.getValueAt(row, 0).toString());
                txtPlate.setText(tableModel.getValueAt(row, 1).toString());
                txtBrand.setText(tableModel.getValueAt(row, 2).toString());
                txtModel.setText(tableModel.getValueAt(row, 3).toString());
                txtYear.setText(tableModel.getValueAt(row, 4).toString());
                cmbType.setSelectedItem(tableModel.getValueAt(row, 5).toString());
                txtRent.setText(tableModel.getValueAt(row, 6).toString());
                txtMileage.setText(tableModel.getValueAt(row, 7).toString());
                cmbStatus.setSelectedItem(tableModel.getValueAt(row, 8).toString());
            }
        });

        JButton refresh = new JButton("Refresh");
        refresh.addActionListener(e -> refreshTable());

        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        panel.add(refresh, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createSearchPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel top = new JPanel();
        JButton search = new JButton("Search");
        JButton showAll = new JButton("Show All");

        top.add(new JLabel("Search:"));
        top.add(txtSearch);
        top.add(search);
        top.add(showAll);

        search.addActionListener(e -> loadTable(manager.searchCars(txtSearch.getText().trim())));
        showAll.addActionListener(e -> refreshTable());

        panel.add(top, BorderLayout.NORTH);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        return panel;
    }

    private JPanel createStatusPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        JTextField idField = new JTextField();
        JComboBox<String> newStatus = new JComboBox<String>(
                new String[]{"AVAILABLE", "BOOKED", "RENTED", "MAINTENANCE", "RETIRED"});

        JButton update = new JButton("Update Status");

        panel.add(new JLabel("Car ID:")); panel.add(idField);
        panel.add(new JLabel("New Status:")); panel.add(newStatus);
        panel.add(new JLabel("")); panel.add(update);

        update.addActionListener(e -> {
            if (manager.updateStatus(idField.getText().trim(),
                    newStatus.getSelectedItem().toString())) {
                JOptionPane.showMessageDialog(this, "Status updated successfully.");
                refreshTable();
            } else {
                JOptionPane.showMessageDialog(this, "Car ID not found.");
            }
        });

        return panel;
    }

    private JPanel createReportPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setFont(new Font("Monospaced", Font.PLAIN, 16));

        JButton report = new JButton("Generate Report");

        report.addActionListener(e -> area.setText(
                "CAR FLEET REPORT\n" +
                "----------------------------\n" +
                "Total Cars: " + manager.getAllCars().size() + "\n" +
                "Available: " + manager.countByStatus("AVAILABLE") + "\n" +
                "Booked: " + manager.countByStatus("BOOKED") + "\n" +
                "Rented: " + manager.countByStatus("RENTED") + "\n" +
                "Maintenance: " + manager.countByStatus("MAINTENANCE") + "\n" +
                "Retired: " + manager.countByStatus("RETIRED")
        ));

        panel.add(new JScrollPane(area), BorderLayout.CENTER);
        panel.add(report, BorderLayout.SOUTH);

        return panel;
    }

    private Car readCarFromFields() {
        return new Car(
                txtCarId.getText().trim(),
                txtPlate.getText().trim(),
                txtBrand.getText().trim(),
                txtModel.getText().trim(),
                Integer.parseInt(txtYear.getText().trim()),
                cmbType.getSelectedItem().toString(),
                Double.parseDouble(txtRent.getText().trim()),
                Integer.parseInt(txtMileage.getText().trim()),
                cmbStatus.getSelectedItem().toString()
        );
    }

    private boolean fieldsValid() {
        if (txtCarId.getText().trim().isEmpty()
                || txtPlate.getText().trim().isEmpty()
                || txtBrand.getText().trim().isEmpty()
                || txtModel.getText().trim().isEmpty()
                || txtYear.getText().trim().isEmpty()
                || txtRent.getText().trim().isEmpty()
                || txtMileage.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields.");
            return false;
        }

        return true;
    }

    private void addCar() {
        try {
            if (!fieldsValid()) return;

            if (manager.addCar(readCarFromFields())) {
                JOptionPane.showMessageDialog(this, "Car added successfully.");
                refreshTable();
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "Car ID already exists.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Year, rent and mileage must be numbers.");
        }
    }

    private void editCar() {
        try {
            if (!fieldsValid()) return;

            if (manager.updateCar(readCarFromFields())) {
                JOptionPane.showMessageDialog(this, "Car updated successfully.");
                refreshTable();
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "Car ID not found.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Year, rent and mileage must be numbers.");
        }
    }

    private void deleteCar() {
        if (manager.deleteCar(txtCarId.getText().trim())) {
            JOptionPane.showMessageDialog(this, "Car deleted successfully.");
            refreshTable();
            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "Car ID not found.");
        }
    }

    private void refreshTable() {
        loadTable(manager.getAllCars());
    }

    private void loadTable(ArrayList<Car> cars) {
        tableModel.setRowCount(0);

        for (Car car : cars) {
            tableModel.addRow(new Object[]{
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

    private void clearFields() {
        txtCarId.setText("");
        txtPlate.setText("");
        txtBrand.setText("");
        txtModel.setText("");
        txtYear.setText("");
        txtRent.setText("");
        txtMileage.setText("");
        cmbType.setSelectedIndex(0);
        cmbStatus.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CarFleetGUI().setVisible(true));
    }
}

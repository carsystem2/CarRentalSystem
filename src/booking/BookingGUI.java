//Aldana
package booking;

import rental.customer.Customer;
import rental.customer.CustomerHelpers;
import car.Car;
import car.CarManager;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author aldanai
 */
public class BookingGUI extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(BookingGUI.class.getName());

    private BookingManager bookingManager = new BookingManager();
    private CarManager carManager = new CarManager();

    public BookingGUI() {
        initComponents();

        loadCustomers();
        loadCars();
        loadTable();
    }

    private void loadCustomers() {

        cmbCustomer.removeAllItems();

        CustomerHelpers.readFile();

        for (Customer customer : CustomerHelpers.getCustomres()) {

            cmbCustomer.addItem(
                    customer.getCustomerId() + " - " + customer.getCustomerName()
            );

        }

    }

    private void loadCars() {

        cmbCar.removeAllItems();

        for (Car car : carManager.getAllCars()) {

            if (car.getStatus().equalsIgnoreCase("Available")) {

                cmbCar.addItem(
                        car.getCarId() + " - "
                        + car.getBrand() + " "
                        + car.getModel()
                );

            }

        }

    }

    private void loadTable() {

        DefaultTableModel model
                = (DefaultTableModel) tblBookings.getModel();

        model.setRowCount(0);

        for (RentalBooking booking : bookingManager.getBookings()) {
            Car car = carManager.findCarById(booking.getCarId());

            String carInfo = booking.getCarId();

            if (car != null) {
                carInfo = car.getCarId() + " - "
                        + car.getBrand();
            }
            model.addRow(new Object[]{
                booking.getRentalId(),
                booking.getCustomerName(),
                carInfo,
                booking.getStartDate(),
                booking.getEndDate(),
                booking.getPickupLocation(),
                booking.getReturnLocation(),
                booking.getDailyRent(),
                booking.getNumberOfDays(),
                booking.getEstimatedAmount(),
                booking.getBookingStatus()
            });

        }

    }

    private void clearFields() {

        txtRentalID.setText("");
        txtStartDate.setText("");
        txtEndDate.setText("");
        txtPickupLocation.setText("");
        txtReturnLocation.setText("");

        lblDailyRent.setText("-");
        lblDays.setText("-");
        lblAmount.setText("-");

        cmbCustomer.setSelectedIndex(-1);
        cmbCar.setSelectedIndex(-1);
    }

    private boolean validateFields() {

        return validateEmptyFields()
                && validateRentalId()
                && validateLocations();

    }

    private boolean validateEmptyFields() {

        if (txtRentalID.getText().trim().isEmpty()) {
            return false;
        }

        if (cmbCustomer.getSelectedIndex() == -1) {
            return false;
        }

        if (cmbCar.getSelectedIndex() == -1) {
            return false;
        }

        if (txtStartDate.getText().trim().isEmpty()) {
            return false;
        }

        if (txtEndDate.getText().trim().isEmpty()) {
            return false;
        }

        if (txtPickupLocation.getText().trim().isEmpty()) {
            return false;
        }

        if (txtReturnLocation.getText().trim().isEmpty()) {
            return false;
        }

        return true;
    }

    private boolean validateRentalId() {

        if (bookingManager.findBookingById(txtRentalID.getText()) != null) {

            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "Rental ID already exists."
            );

            return false;
        }

        return true;
    }

    private boolean validateLocations() {

        if (!txtPickupLocation.getText().matches("[a-zA-Z ]+")) {

            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "Pickup location must contain letters only."
            );

            return false;
        }

        if (!txtReturnLocation.getText().matches("[a-zA-Z ]+")) {

            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "Return location must contain letters only."
            );

            return false;
        }

        return true;
    }

    private boolean isCarAvailable(String carId, String startDate, String endDate) {

        DateTimeFormatter formatter
                = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        LocalDate newStart = LocalDate.parse(startDate, formatter);
        LocalDate newEnd = LocalDate.parse(endDate, formatter);

        for (RentalBooking booking : bookingManager.getBookings()) {

            if (!booking.getCarId().equals(carId)) {
                continue;
            }

            LocalDate bookedStart
                    = LocalDate.parse(booking.getStartDate(), formatter);

            LocalDate bookedEnd
                    = LocalDate.parse(booking.getEndDate(), formatter);

            if (!(newEnd.isBefore(bookedStart)
                    || newStart.isAfter(bookedEnd))) {

                return false;
            }
        }

        return true;
    }

    private boolean calculateBookingInfo() {

        try {

            DateTimeFormatter formatter
                    = DateTimeFormatter.ofPattern("dd-MM-yyyy");

            LocalDate start
                    = LocalDate.parse(txtStartDate.getText().trim(), formatter);

            LocalDate end
                    = LocalDate.parse(txtEndDate.getText().trim(), formatter);

            LocalDate today = LocalDate.now();

            if (start.isBefore(today)) {

                javax.swing.JOptionPane.showMessageDialog(
                        this,
                        "Start date cannot be before today."
                );

                return false;
            }

            if (end.isAfter(today.plusYears(2))) {

                javax.swing.JOptionPane.showMessageDialog(
                        this,
                        "End date is too far."
                );

                return false;
            }

            if (!end.isAfter(start)) {
                javax.swing.JOptionPane.showMessageDialog(
                        this,
                        "End Date must be after Start Date."
                );
                return false;
            }

            long days = ChronoUnit.DAYS.between(start, end);

            lblDays.setText(String.valueOf(days));

            String selectedCar = cmbCar.getSelectedItem().toString();
            String carId = selectedCar.split(" - ")[0];

            Car car = carManager.findCarById(carId);

            if (car == null) {
                javax.swing.JOptionPane.showMessageDialog(
                        this,
                        "Car not found."
                );
                return false;
            }

            lblDailyRent.setText(String.valueOf(car.getDailyRent()));

            double amount = days * car.getDailyRent();

            lblAmount.setText(String.format("%.3f", amount));

            return true;

        } catch (Exception e) {

            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "Please enter dates in DD-MM-YYYY format."
            );

            return false;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnSearch = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtRentalID = new javax.swing.JTextField();
        txtStartDate = new javax.swing.JTextField();
        txtEndDate = new javax.swing.JTextField();
        txtPickupLocation = new javax.swing.JTextField();
        txtReturnLocation = new javax.swing.JTextField();
        cmbCustomer = new javax.swing.JComboBox<>();
        cmbCar = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lblDailyRent = new javax.swing.JLabel();
        lblDays = new javax.swing.JLabel();
        lblAmount = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblBookings = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        btnRefresh = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/booking/icons8-add-16.png"))); // NOI18N
        btnAdd.setText("Add Booking");
        btnAdd.addActionListener(this::btnAddActionPerformed);

        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/booking/update-24.png"))); // NOI18N
        btnUpdate.setText("Update Booking");
        btnUpdate.addActionListener(this::btnUpdateActionPerformed);

        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/booking/icons8-delete-16.png"))); // NOI18N
        btnDelete.setText("Delete Booking");
        btnDelete.addActionListener(this::btnDeleteActionPerformed);

        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/booking/icons8-search-32.png"))); // NOI18N
        btnSearch.setText("Search");
        btnSearch.addActionListener(this::btnSearchActionPerformed);

        btnClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/booking/icons8-clear-32.png"))); // NOI18N
        btnClear.setText("Clear");
        btnClear.addActionListener(this::btnClearActionPerformed);

        jLabel1.setText("Rental ID");

        jLabel2.setText("Customer");

        jLabel3.setText("Car");

        jLabel4.setText("Start Date: DD-MM-YYYY");

        jLabel5.setText("End Date: DD-MM-YYYY");

        jLabel6.setText("Pickup Location");

        jLabel7.setText("Return Location");

        txtRentalID.addActionListener(this::txtRentalIDActionPerformed);

        txtStartDate.addActionListener(this::txtStartDateActionPerformed);

        txtEndDate.addActionListener(this::txtEndDateActionPerformed);

        txtPickupLocation.addActionListener(this::txtPickupLocationActionPerformed);

        cmbCustomer.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbCustomer.addActionListener(this::cmbCustomerActionPerformed);

        cmbCar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel8.setText("Daily Rent");

        jLabel9.setText("Number of Days");

        jLabel10.setText("Estimated Amount ");

        lblDailyRent.setText("0.000 KD");

        lblDays.setText("0");

        lblAmount.setText("0.000KD");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtEndDate)
                            .addComponent(txtPickupLocation)
                            .addComponent(txtReturnLocation, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                            .addComponent(txtStartDate))
                        .addGap(39, 39, 39)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(196, 196, 196))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(lblDays, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblDailyRent, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(178, 178, 178))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtRentalID)
                                .addComponent(cmbCustomer, 0, 350, Short.MAX_VALUE))
                            .addComponent(cmbCar, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRentalID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbCar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(lblDailyRent))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(lblDays))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(lblAmount)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPickupLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtReturnLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        tblBookings.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Rental ID", "Customer", "Car", "Start Date", "End Date", "Pickup Location", "Return Location", "Daily Rent", "Days", "Amount", "Status"
            }
        ));
        tblBookings.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBookingsMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblBookings);

        jLabel11.setText("All Bookings");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 929, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1165, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel12.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(153, 0, 255));
        jLabel12.setText("Car Rental Booking Management");

        btnRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/booking/icons8-refresh-16.png"))); // NOI18N
        btnRefresh.setText("Refresh");
        btnRefresh.addActionListener(this::btnRefreshActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 593, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(161, 161, 161))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(92, 92, 92))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed

        clearFields();


    }//GEN-LAST:event_btnClearActionPerformed

    private void txtRentalIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRentalIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRentalIDActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed

        if (!validateFields()) {

            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "Please fill in all fields."
            );

            return;
        }

        if (!calculateBookingInfo()) {
            return;
        }
        String customerData = cmbCustomer.getSelectedItem().toString();
        String[] customerParts = customerData.split(" - ", 2);

        String carData = cmbCar.getSelectedItem().toString();
        String[] carParts = carData.split(" - ", 2);

        Car car = carManager.findCarById(carParts[0]);

        if (!isCarAvailable(
                car.getCarId(),
                txtStartDate.getText().trim(),
                txtEndDate.getText().trim())) {

            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "This car is already booked during the selected period."
            );

            return;
        }

        if (car == null) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "Selected car not found.");
            return;
        }
        RentalBooking booking = new RentalBooking(
                txtRentalID.getText(),
                customerParts[0],
                customerParts[1],
                car.getCarId(),
                car.getPlateNumber(),
                txtStartDate.getText(),
                txtEndDate.getText(),
                txtPickupLocation.getText(),
                txtReturnLocation.getText(),
                Integer.parseInt(lblDays.getText()),
                car.getDailyRent(),
                Double.parseDouble(lblAmount.getText()),
                "Booked"
        );

        bookingManager.addBooking(booking);

        carManager.updateStatus(car.getCarId(), "Booked");

        loadTable();
        loadCars();
        clearFields();

        javax.swing.JOptionPane.showMessageDialog(
                this,
                "Booking added successfully!"
        );


    }//GEN-LAST:event_btnAddActionPerformed

    private void txtPickupLocationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPickupLocationActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPickupLocationActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed

        String rentalId = txtRentalID.getText().trim();

        if (rentalId.isEmpty()) {

            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "Please enter or select a Rental ID."
            );

            return;
        }

        RentalBooking booking = bookingManager.findBookingById(rentalId);

        if (booking == null) {

            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "Booking not found."
            );

            return;
        }

        int choice = javax.swing.JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to delete this booking?",
                "Delete Booking",
                javax.swing.JOptionPane.YES_NO_OPTION
        );

        if (choice != javax.swing.JOptionPane.YES_OPTION) {
            return;
        }

        carManager.updateStatus(booking.getCarId(), "Available");

        bookingManager.deleteBooking(rentalId);

        loadTable();
        loadCars();
        clearFields();

        javax.swing.JOptionPane.showMessageDialog(
                this,
                "Booking deleted successfully!"
        );

    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed

        if (!validateEmptyFields()) {

            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "Please fill in all fields."
            );
            return;
        }

        if (!validateLocations()) {
            return;
        }

        if (!calculateBookingInfo()) {
            return;
        }

        RentalBooking oldBooking
                = bookingManager.findBookingById(txtRentalID.getText().trim());

        if (oldBooking == null) {

            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "Booking not found."
            );
            return;
        }

        String customerData = cmbCustomer.getSelectedItem().toString();
        String[] customerParts = customerData.split(" - ", 2);

        String carData = cmbCar.getSelectedItem().toString();
        String[] carParts = carData.split(" - ", 2);

        Car car = carManager.findCarById(carParts[0]);

        if (car == null) {

            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "Selected car not found."
            );
            return;
        }

        RentalBooking booking = new RentalBooking(
                txtRentalID.getText().trim(),
                customerParts[0],
                customerParts[1],
                car.getCarId(),
                car.getPlateNumber(),
                txtStartDate.getText().trim(),
                txtEndDate.getText().trim(),
                txtPickupLocation.getText().trim(),
                txtReturnLocation.getText().trim(),
                Integer.parseInt(lblDays.getText()),
                car.getDailyRent(),
                Double.parseDouble(lblAmount.getText()),
                "Booked"
        );

        if (!oldBooking.getCarId().equalsIgnoreCase(car.getCarId())) {

            carManager.updateStatus(oldBooking.getCarId(), "Available");

            carManager.updateStatus(car.getCarId(), "Booked");

        } else {

            carManager.updateStatus(car.getCarId(), "Booked");
        }

        bookingManager.updateBooking(booking);

        loadTable();
        loadCars();
        clearFields();

        javax.swing.JOptionPane.showMessageDialog(
                this,
                "Booking updated successfully!"
        );

    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed

        String rentalId = txtRentalID.getText().trim();

        RentalBooking booking = bookingManager.searchBooking(rentalId);

        if (booking == null) {

            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "Booking not found."
            );
            return;
        }

        txtRentalID.setText(booking.getRentalId());
        txtStartDate.setText(booking.getStartDate());
        txtEndDate.setText(booking.getEndDate());
        txtPickupLocation.setText(booking.getPickupLocation());
        txtReturnLocation.setText(booking.getReturnLocation());

        lblDailyRent.setText(String.valueOf(booking.getDailyRent()));
        lblDays.setText(String.valueOf(booking.getNumberOfDays()));
        lblAmount.setText(String.valueOf(booking.getEstimatedAmount()));

        for (int i = 0; i < cmbCustomer.getItemCount(); i++) {
            if (cmbCustomer.getItemAt(i).startsWith(booking.getCustomerId() + " -")) {
                cmbCustomer.setSelectedIndex(i);
                break;
            }
        }
        String carItem = booking.getCarId();

        Car car = carManager.findCarById(booking.getCarId());

        if (car != null) {
            carItem = car.getCarId() + " - "
                    + car.getBrand() + " "
                    + car.getModel();
        }

        boolean found = false;

        for (int i = 0; i < cmbCar.getItemCount(); i++) {

            if (cmbCar.getItemAt(i).startsWith(booking.getCarId() + " -")) {
                cmbCar.setSelectedIndex(i);
                found = true;
                break;
            }
        }

        if (!found) {
            cmbCar.addItem(carItem);
            cmbCar.setSelectedItem(carItem);
        }


    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        loadTable();

        loadCars();

        clearFields();


    }//GEN-LAST:event_btnRefreshActionPerformed

    private void txtEndDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEndDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEndDateActionPerformed

    private void txtStartDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStartDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtStartDateActionPerformed

    private void cmbCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCustomerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbCustomerActionPerformed

    private void tblBookingsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBookingsMouseClicked

        int row = tblBookings.getSelectedRow();

        if (row == -1) {
            return;
        }

        txtRentalID.setText(tblBookings.getValueAt(row, 0).toString());
        txtStartDate.setText(tblBookings.getValueAt(row, 3).toString());
        txtEndDate.setText(tblBookings.getValueAt(row, 4).toString());
        txtPickupLocation.setText(tblBookings.getValueAt(row, 5).toString());
        txtReturnLocation.setText(tblBookings.getValueAt(row, 6).toString());

        lblDailyRent.setText(tblBookings.getValueAt(row, 7).toString());
        lblDays.setText(tblBookings.getValueAt(row, 8).toString());
        lblAmount.setText(tblBookings.getValueAt(row, 9).toString());

        RentalBooking booking
                = bookingManager.findBookingById(txtRentalID.getText());

        if (booking != null) {

            for (int i = 0; i < cmbCustomer.getItemCount(); i++) {
                if (cmbCustomer.getItemAt(i).startsWith(booking.getCustomerId() + " -")) {
                    cmbCustomer.setSelectedIndex(i);
                    break;
                }
            }

            for (int i = 0; i < cmbCar.getItemCount(); i++) {
                if (cmbCar.getItemAt(i).startsWith(booking.getCarId() + " -")) {
                    cmbCar.setSelectedIndex(i);
                    break;
                }
            }
        }

    }//GEN-LAST:event_tblBookingsMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new BookingGUI().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cmbCar;
    private javax.swing.JComboBox<String> cmbCustomer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblAmount;
    private javax.swing.JLabel lblDailyRent;
    private javax.swing.JLabel lblDays;
    private javax.swing.JTable tblBookings;
    private javax.swing.JTextField txtEndDate;
    private javax.swing.JTextField txtPickupLocation;
    private javax.swing.JTextField txtRentalID;
    private javax.swing.JTextField txtReturnLocation;
    private javax.swing.JTextField txtStartDate;
    // End of variables declaration//GEN-END:variables
}

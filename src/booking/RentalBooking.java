package booking;

public class RentalBooking {

    private String rentalId;
    private String customerId;
    private String customerName;
    private String carId;
    private String plateNumber;

    private String startDate;
    private String endDate;

    private String pickupLocation;
    private String returnLocation;

    private int numberOfDays;
    private double dailyRent;
    private double estimatedAmount;

    private String bookingStatus;

    
    public RentalBooking() {
    }

  
    public RentalBooking(String rentalId,String customerId,String customerName,String carId,String plateNumber,
                         String startDate,String endDate,String pickupLocation,String returnLocation,int numberOfDays,
                         double dailyRent,double estimatedAmount,String bookingStatus) 
    {

        this.rentalId = rentalId;
        this.customerId = customerId;
        this.customerName = customerName;
        this.carId = carId;
        this.plateNumber = plateNumber;
        this.startDate = startDate;
        this.endDate = endDate;
        this.pickupLocation = pickupLocation;
        this.returnLocation = returnLocation;
        this.numberOfDays = numberOfDays;
        this.dailyRent = dailyRent;
        this.estimatedAmount = estimatedAmount;
        this.bookingStatus = bookingStatus;
    }

    public String getRentalId() {
        return rentalId;
    }

    public void setRentalId(String rentalId) {
        this.rentalId = rentalId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public String getReturnLocation() {
        return returnLocation;
    }

    public void setReturnLocation(String returnLocation) {
        this.returnLocation = returnLocation;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(int numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public double getDailyRent() {
        return dailyRent;
    }

    public void setDailyRent(double dailyRent) {
        this.dailyRent = dailyRent;
    }

    public double getEstimatedAmount() {
        return estimatedAmount;
    }

    public void setEstimatedAmount(double estimatedAmount) {
        this.estimatedAmount = estimatedAmount;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    @Override
    public String toString() {
        return rentalId + "," +customerId + "," +customerName + "," +carId + "," +plateNumber + "," +startDate + "," +endDate + "," +
               pickupLocation + "," +returnLocation + "," +numberOfDays + "," +dailyRent + "," +estimatedAmount + "," +
               bookingStatus;
    }
}
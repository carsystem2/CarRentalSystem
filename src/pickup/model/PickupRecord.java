package pickup.model;
import java.io.Serializable;
public class PickupRecord implements Serializable {
   private static final long serialVersionUID = 1L;
   private String recordId;
   private String rentalId;
   private String customerId;
   private String carId;
   private String pickupDate;
   private String pickupTime;
   private String returnDate;
   private String returnTime;
   private int pickupMileage;
   private int returnMileage;
   private int distanceDriven;
   private double extraCharges;
   private double finalAmount;
   private String returnStatus;
   public PickupRecord() {
   }
   public PickupRecord(String recordId, String rentalId, String customerId,
           String carId, String pickupDate, String pickupTime,
           String returnDate, String returnTime,
           int pickupMileage, int returnMileage,
           int distanceDriven, double extraCharges,
           double finalAmount, String returnStatus) {
       this.recordId = recordId;
       this.rentalId = rentalId;
       this.customerId = customerId;
       this.carId = carId;
       this.pickupDate = pickupDate;
       this.pickupTime = pickupTime;
       this.returnDate = returnDate;
       this.returnTime = returnTime;
       this.pickupMileage = pickupMileage;
       this.returnMileage = returnMileage;
       this.distanceDriven = distanceDriven;
       this.extraCharges = extraCharges;
       this.finalAmount = finalAmount;
       this.returnStatus = returnStatus;
   }
   public String getRecordId() {
       return recordId;
   }
   public void setRecordId(String recordId) {
       this.recordId = recordId;
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
   public String getCarId() {
       return carId;
   }
   public void setCarId(String carId) {
       this.carId = carId;
   }
   public String getPickupDate() {
       return pickupDate;
   }
   public void setPickupDate(String pickupDate) {
       this.pickupDate = pickupDate;
   }
   public String getPickupTime() {
       return pickupTime;
   }
   public void setPickupTime(String pickupTime) {
       this.pickupTime = pickupTime;
   }
   public String getReturnDate() {
       return returnDate;
   }
   public void setReturnDate(String returnDate) {
       this.returnDate = returnDate;
   }
   public String getReturnTime() {
       return returnTime;
   }
   public void setReturnTime(String returnTime) {
       this.returnTime = returnTime;
   }
   public int getPickupMileage() {
       return pickupMileage;
   }
   public void setPickupMileage(int pickupMileage) {
       this.pickupMileage = pickupMileage;
   }
   public int getReturnMileage() {
       return returnMileage;
   }
   public void setReturnMileage(int returnMileage) {
       this.returnMileage = returnMileage;
   }
   public int getDistanceDriven() {
       return distanceDriven;
   }
   public void setDistanceDriven(int distanceDriven) {
       this.distanceDriven = distanceDriven;
   }
   public double getExtraCharges() {
       return extraCharges;
   }
   public void setExtraCharges(double extraCharges) {
       this.extraCharges = extraCharges;
   }
   public double getFinalAmount() {
       return finalAmount;
   }
   public void setFinalAmount(double finalAmount) {
       this.finalAmount = finalAmount;
   }
   public String getReturnStatus() {
       return returnStatus;
   }
   public void setReturnStatus(String returnStatus) {
       this.returnStatus = returnStatus;
   }
}

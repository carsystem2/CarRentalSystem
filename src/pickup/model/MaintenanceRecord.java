package pickup.model;
import java.io.Serializable;
public class MaintenanceRecord implements Serializable {
   private static final long serialVersionUID = 1L;
   private String maintenanceId;
   private String carId;
   private String maintenanceType;
   private String maintenanceDate;
   private String workshopName;
   private double maintenanceCost;
   private String maintenanceStatus;
   private String remarks;
   public MaintenanceRecord() {
   }
   public MaintenanceRecord(String maintenanceId, String carId,
           String maintenanceType, String maintenanceDate,
           String workshopName, double maintenanceCost,
           String maintenanceStatus, String remarks) {
       this.maintenanceId = maintenanceId;
       this.carId = carId;
       this.maintenanceType = maintenanceType;
       this.maintenanceDate = maintenanceDate;
       this.workshopName = workshopName;
       this.maintenanceCost = maintenanceCost;
       this.maintenanceStatus = maintenanceStatus;
       this.remarks = remarks;
   }
   public String getMaintenanceId() {
       return maintenanceId;
   }
   public void setMaintenanceId(String maintenanceId) {
       this.maintenanceId = maintenanceId;
   }
   public String getCarId() {
       return carId;
   }
   public void setCarId(String carId) {
       this.carId = carId;
   }
   public String getMaintenanceType() {
       return maintenanceType;
   }
   public void setMaintenanceType(String maintenanceType) {
       this.maintenanceType = maintenanceType;
   }
   public String getMaintenanceDate() {
       return maintenanceDate;
   }
   public void setMaintenanceDate(String maintenanceDate) {
       this.maintenanceDate = maintenanceDate;
   }
   public String getWorkshopName() {
       return workshopName;
   }
   public void setWorkshopName(String workshopName) {
       this.workshopName = workshopName;
   }
   public double getMaintenanceCost() {
       return maintenanceCost;
   }
   public void setMaintenanceCost(double maintenanceCost) {
       this.maintenanceCost = maintenanceCost;
   }
   public String getMaintenanceStatus() {
       return maintenanceStatus;
   }
   public void setMaintenanceStatus(String maintenanceStatus) {
       this.maintenanceStatus = maintenanceStatus;
   }
   public String getRemarks() {
       return remarks;
   }
   public void setRemarks(String remarks) {
       this.remarks = remarks;
   }
}
package pickup.manager;
import java.util.ArrayList;
import pickup.helper.Helpers;
import pickup.model.MaintenanceRecord;
public class MaintenanceManager {
   private ArrayList<MaintenanceRecord> records;
   public MaintenanceManager() {
       Helpers.readMaintenanceFile();
       records = Helpers.getMaintenanceRecords();
   }
   public void addRecord(MaintenanceRecord record) {
       records.add(record);
       Helpers.writeMaintenanceFile();
   }
   public ArrayList<MaintenanceRecord> getAllRecords() {
       return records;
   }
   public MaintenanceRecord searchRecord(String maintenanceId) {
       for (MaintenanceRecord record : records) {
           if (record.getMaintenanceId()
                   .equalsIgnoreCase(maintenanceId)) {
               return record;
           }
       }
       return null;
   }
   public boolean deleteRecord(String maintenanceId) {
       MaintenanceRecord record =
               searchRecord(maintenanceId);
       if (record != null) {
           records.remove(record);
           Helpers.writeMaintenanceFile();
           return true;
       }
       return false;
   }
   public boolean updateRecord(
           MaintenanceRecord updatedRecord) {
       for (int i = 0; i < records.size(); i++) {
           if (records.get(i).getMaintenanceId()
                   .equalsIgnoreCase(
                           updatedRecord.getMaintenanceId())) {
               records.set(i, updatedRecord);
               Helpers.writeMaintenanceFile();
               return true;
           }
       }
       return false;
   }
}
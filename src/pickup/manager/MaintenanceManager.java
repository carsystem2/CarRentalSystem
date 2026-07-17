  package pickup.manager;
import java.util.ArrayList;
import pickup.file.MaintenanceFile;
import pickup.model.MaintenanceRecord;
public class MaintenanceManager {
   private ArrayList<MaintenanceRecord> records;
   private MaintenanceFile file;
   public MaintenanceManager() {
       file = new MaintenanceFile();
       records = file.load();
   }
   public void addRecord(MaintenanceRecord record) {
       records.add(record);
       file.save(records);
   }
   public ArrayList<MaintenanceRecord> getAllRecords() {
       return records;
   }
   public MaintenanceRecord searchRecord(String maintenanceId) {
       for (MaintenanceRecord record : records) {
           if (record.getMaintenanceId().equalsIgnoreCase(maintenanceId)) {
               return record;
           }
       }
       return null;
   }
   public boolean deleteRecord(String maintenanceId) {
       MaintenanceRecord record = searchRecord(maintenanceId);
       if (record != null) {
           records.remove(record);
           file.save(records);
           return true;
       }
       return false;
   }
   public boolean updateRecord(MaintenanceRecord updatedRecord) {
       for (int i = 0; i < records.size(); i++) {
           if (records.get(i).getMaintenanceId()
                   .equalsIgnoreCase(updatedRecord.getMaintenanceId())) {
               records.set(i, updatedRecord);
               file.save(records);
               return true;
           }
       }
       return false;
   }
}
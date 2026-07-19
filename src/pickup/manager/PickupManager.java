package pickup.manager;
import java.util.ArrayList;
import pickup.helper.Helpers;
import pickup.model.PickupRecord;
public class PickupManager {
   private ArrayList<PickupRecord> records;
   public PickupManager() {
       Helpers.readPickupFile();
       records = Helpers.getPickupRecords();
   }
   public void addRecord(PickupRecord record) {
       records.add(record);
       Helpers.writePickupFile();
   }
   public ArrayList<PickupRecord> getAllRecords() {
       return records;
   }
   public PickupRecord searchRecord(String recordId) {
       for (PickupRecord record : records) {
           if (record.getRecordId()
                   .equalsIgnoreCase(recordId)) {
               return record;
           }
       }
       return null;
   }
   public boolean deleteRecord(String recordId) {
       PickupRecord record = searchRecord(recordId);
       if (record != null) {
           records.remove(record);
           Helpers.writePickupFile();
           return true;
       }
       return false;
   }
   public boolean updateRecord(PickupRecord updatedRecord) {
       for (int i = 0; i < records.size(); i++) {
           if (records.get(i).getRecordId()
                   .equalsIgnoreCase(
                           updatedRecord.getRecordId())) {
               records.set(i, updatedRecord);
               Helpers.writePickupFile();
               return true;
           }
       }
       return false;
   }
}
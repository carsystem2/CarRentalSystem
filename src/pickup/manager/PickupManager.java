package pickup.manager;
import java.util.ArrayList;
import pickup.file.PickupFile;
import pickup.model.PickupRecord;
public class PickupManager {
   private ArrayList<PickupRecord> records;
   private PickupFile file;
   public PickupManager() {
       file = new PickupFile();
       records = file.load();
   }
   public void addRecord(PickupRecord record) {
       records.add(record);
       file.save(records);
   }
   public ArrayList<PickupRecord> getAllRecords() {
       return records;
   }
   public PickupRecord searchRecord(String recordId) {
       for (PickupRecord record : records) {
           if (record.getRecordId().equalsIgnoreCase(recordId)) {
               return record;
           }
       }
       return null;
   }
   public boolean deleteRecord(String recordId) {
       PickupRecord record = searchRecord(recordId);
       if (record != null) {
           records.remove(record);
           file.save(records);
           return true;
       }
       return false;
   }
   public boolean updateRecord(PickupRecord updatedRecord) {
       for (int i = 0; i < records.size(); i++) {
           if (records.get(i).getRecordId().equalsIgnoreCase(updatedRecord.getRecordId())) {
               records.set(i, updatedRecord);
               file.save(records);
               return true;
           }
       }
       return false;
   }
}
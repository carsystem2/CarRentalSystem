package pickup.file;
import java.io.*;
import java.util.ArrayList;
import pickup.model.MaintenanceRecord;
public class MaintenanceFile {
   private final String FILE_NAME = "data/car_maintenance.dat";
   public void save(ArrayList<MaintenanceRecord> records) {
       try {
           ObjectOutputStream output =
                   new ObjectOutputStream(
                           new FileOutputStream(FILE_NAME));
           output.writeObject(records);
           output.close();
       } catch (IOException e) {
           e.printStackTrace();
       }
   }
   @SuppressWarnings("unchecked")
   public ArrayList<MaintenanceRecord> load() {
       File file = new File(FILE_NAME);
       if (!file.exists()) {
           return new ArrayList<>();
       }
       try {
           ObjectInputStream input =
                   new ObjectInputStream(
                           new FileInputStream(FILE_NAME));
           ArrayList<MaintenanceRecord> records =
                   (ArrayList<MaintenanceRecord>) input.readObject();
           input.close();
           return records;
       } catch (Exception e) {
           return new ArrayList<>();
       }
   }
}
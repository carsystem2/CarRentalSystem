package pickup.file;
import java.io.*;
import java.util.ArrayList;
import pickup.model.PickupRecord;
public class PickupFile {
   private final String FILE_NAME = "data/pickup_return_records.dat";
   public void save(ArrayList<PickupRecord> records) {
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
   public ArrayList<PickupRecord> load() {
       File file = new File(FILE_NAME);
       if (!file.exists()) {
           return new ArrayList<>();
       }
       try {
           ObjectInputStream input =
                   new ObjectInputStream(
                           new FileInputStream(FILE_NAME));
           ArrayList<PickupRecord> records =
                   (ArrayList<PickupRecord>) input.readObject();
           input.close();
           return records;
       } catch (Exception e) {
           return new ArrayList<>();
       }
   }
}

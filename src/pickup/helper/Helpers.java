package pickup.helper;
import java.io.*;
import java.util.ArrayList;
import pickup.model.PickupRecord;
import pickup.model.MaintenanceRecord;
public class Helpers {
   private static ArrayList<PickupRecord> pickupRecords = new ArrayList<>();
   private static ArrayList<MaintenanceRecord> maintenanceRecords = new ArrayList<>();
   public static ArrayList<PickupRecord> getPickupRecords() {
       return pickupRecords;
   }
   public static ArrayList<MaintenanceRecord> getMaintenanceRecords() {
       return maintenanceRecords;
   }
   public static void writePickupFile() {
       try {
           String fileName = "data/pickup_return_records.txt";
           FileOutputStream fileOut = new FileOutputStream(fileName);
           ObjectOutputStream out = new ObjectOutputStream(fileOut);
           out.writeObject(pickupRecords);
           out.close();
           fileOut.close();
       } catch (Exception e) {
           System.out.println(e);
       }
   }
   @SuppressWarnings("unchecked")
   public static void readPickupFile() {
       try {
           String fileName = "data/pickup_return_records.txt";
           File file = new File(fileName);
           if (!file.exists()) {
               return;
           }
           FileInputStream fileIn = new FileInputStream(fileName);
           ObjectInputStream in = new ObjectInputStream(fileIn);
           pickupRecords = (ArrayList<PickupRecord>) in.readObject();
           in.close();
           fileIn.close();
       } catch (Exception e) {
           System.out.println(e);
       }
   }
   public static void writeMaintenanceFile() {
       try {
           String fileName = "data/car_maintenance.txt";
           FileOutputStream fileOut = new FileOutputStream(fileName);
           ObjectOutputStream out = new ObjectOutputStream(fileOut);
           out.writeObject(maintenanceRecords);
           out.close();
           fileOut.close();
       } catch (Exception e) {
           System.out.println(e);
       }
   }
   @SuppressWarnings("unchecked")
   public static void readMaintenanceFile() {
       try {
           String fileName = "data/car_maintenance.txt";
           File file = new File(fileName);
           if (!file.exists()) {
               return;
           }
           FileInputStream fileIn = new FileInputStream(fileName);
           ObjectInputStream in = new ObjectInputStream(fileIn);
           maintenanceRecords = (ArrayList<MaintenanceRecord>) in.readObject();
           in.close();
           fileIn.close();
       } catch (Exception e) {
           System.out.println(e);
       }
   }
}
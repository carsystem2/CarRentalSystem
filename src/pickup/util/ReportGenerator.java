package pickup.util;
import java.util.ArrayList;
import pickup.model.PickupRecord;
public class ReportGenerator {
   public int getTotalPickups(ArrayList<PickupRecord> records) {
       return records.size();
   }
   public int getCompletedReturns(ArrayList<PickupRecord> records) {
       int count = 0;
       for (PickupRecord record : records) {
           if (record.getReturnStatus().equalsIgnoreCase("Returned")) {
               count++;
           }
       }
       return count;
   }
   public double getTotalCharges(ArrayList<PickupRecord> records) {
       double total = 0;
       for (PickupRecord record : records) {
           total += record.getExtraCharges();
       }
       return total;
   }
}
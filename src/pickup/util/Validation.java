package pickup.util;
public class Validation {
   public static boolean isEmpty(String text) {
       return text == null || text.trim().isEmpty();
   }
   public static boolean isInteger(String text) {
       try {
           Integer.parseInt(text);
           return true;
       } catch (NumberFormatException e) {
           return false;
       }
   }
   public static boolean isDouble(String text) {
       try {
           Double.parseDouble(text);
           return true;
       } catch (NumberFormatException e) {
           return false;
       }
   }
}
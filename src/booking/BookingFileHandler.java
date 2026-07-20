package booking;

import java.io.*;
import java.util.ArrayList;

public class BookingFileHandler {

    private static final String FILE_NAME = "rental_bookings.txt";

    public static void saveBookings(ArrayList<RentalBooking> bookings) {

        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {

            for (RentalBooking booking : bookings) {
                writer.println(booking.toString());
            }

        } catch (IOException e) {
            System.out.println("Error saving bookings.");
        }
    }

  
    public static ArrayList<RentalBooking> loadBookings() {

        ArrayList<RentalBooking> bookings = new ArrayList<>();

        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return bookings;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",");

                if (data.length == 13) {

                    RentalBooking booking = new RentalBooking(
                            data[0],                       // rentalId
                            data[1],                       // customerId
                            data[2],                       // customerName
                            data[3],                       // carId
                            data[4],                       // plateNumber
                            data[5],                       // startDate
                            data[6],                       // endDate
                            data[7],                       // pickupLocation
                            data[8],                       // returnLocation
                            Integer.parseInt(data[9]),     // numberOfDays
                            Double.parseDouble(data[10]),  // dailyRent
                            Double.parseDouble(data[11]),  // estimatedAmount
                            data[12]                       // bookingStatus
                    );

                    bookings.add(booking);
                }
            }

        } catch (IOException e) {
            System.out.println("Error loading bookings.");
        }

        return bookings;
    }

}

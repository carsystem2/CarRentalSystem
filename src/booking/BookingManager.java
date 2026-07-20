package booking;


import java.util.ArrayList;

public class BookingManager {

    private ArrayList<RentalBooking> bookings;

    public BookingManager() {
        bookings = BookingFileHandler.loadBookings();
    }

    public ArrayList<RentalBooking> getBookings() {
        return bookings;
    }

    public void addBooking(RentalBooking booking) {
        bookings.add(booking);
        BookingFileHandler.saveBookings(bookings);
    }

    public void deleteBooking(String rentalId) {

        for (int i = 0; i < bookings.size(); i++) {

            if (bookings.get(i).getRentalId().equalsIgnoreCase(rentalId)) {

                bookings.remove(i);
                BookingFileHandler.saveBookings(bookings);
                return;

            }
        }
    }

    public RentalBooking searchBooking(String rentalId) {

        for (RentalBooking booking : bookings) {

            if (booking.getRentalId().equalsIgnoreCase(rentalId)) {
                return booking;
            }

        }

        return null;
    }

    public boolean updateBooking(RentalBooking updatedBooking) {

    for (int i = 0; i < bookings.size(); i++) {

        if (bookings.get(i).getRentalId()
                .equalsIgnoreCase(updatedBooking.getRentalId())) {

            bookings.set(i, updatedBooking);
            BookingFileHandler.saveBookings(bookings);
            return true;
        }
    }

    return false;
}
    
    public RentalBooking findBookingById(String rentalId) {

    for (RentalBooking booking : bookings) {

        if (booking.getRentalId().equalsIgnoreCase(rentalId)) {
            return booking;
        }

    }

    return null;
}
    
    
    

}
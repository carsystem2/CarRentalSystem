package car;

import java.io.*;
import java.util.ArrayList;

public class CarFileHandler {
    private static final String FILE_NAME = "cars.txt";

    public static ArrayList<Car> readCars() {
        ArrayList<Car> cars = new ArrayList<Car>();

        try {
            File file = new File(FILE_NAME);
            if (!file.exists()) file.createNewFile();

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;

            while ((line = reader.readLine()) != null) {
                Car car = Car.fromFileString(line);
                if (car != null) cars.add(car);
            }

            reader.close();
        } catch (IOException e) {
            System.out.println("Error reading cars.txt: " + e.getMessage());
        }

        return cars;
    }

    public static void saveCars(ArrayList<Car> cars) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME));

            for (Car car : cars) {
                writer.println(car.toFileString());
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving cars.txt: " + e.getMessage());
        }
    }
}

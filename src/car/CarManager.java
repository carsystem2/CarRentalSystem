package car;

import java.util.ArrayList;

public class CarManager {
    private final ArrayList<Car> cars;

    public CarManager() {
        cars = CarFileHandler.readCars();
    }

    public ArrayList<Car> getAllCars() {
        return new ArrayList<Car>(cars);
    }

    public boolean addCar(Car car) {
        if (findCarById(car.getCarId()) != null) return false;
        cars.add(car);
        CarFileHandler.saveCars(cars);
        return true;
    }

    public boolean updateCar(Car updatedCar) {
        Car car = findCarById(updatedCar.getCarId());
        if (car == null) return false;

        car.setPlateNumber(updatedCar.getPlateNumber());
        car.setBrand(updatedCar.getBrand());
        car.setModel(updatedCar.getModel());
        car.setYear(updatedCar.getYear());
        car.setCarType(updatedCar.getCarType());
        car.setDailyRent(updatedCar.getDailyRent());
        car.setMileage(updatedCar.getMileage());
        car.setStatus(updatedCar.getStatus());

        CarFileHandler.saveCars(cars);
        return true;
    }

    public boolean deleteCar(String carId) {
        Car car = findCarById(carId);
        if (car == null) return false;
        cars.remove(car);
        CarFileHandler.saveCars(cars);
        return true;
    }

    public boolean updateStatus(String carId, String status) {
        Car car = findCarById(carId);
        if (car == null) return false;
        car.setStatus(status);
        CarFileHandler.saveCars(cars);
        return true;
    }

    public Car findCarById(String carId) {
        for (Car car : cars) {
            if (car.getCarId().equalsIgnoreCase(carId)) return car;
        }
        return null;
    }

    public ArrayList<Car> searchCars(String keyword) {
        ArrayList<Car> results = new ArrayList<Car>();
        String search = keyword.toLowerCase();

        for (Car car : cars) {
            if (car.getCarId().toLowerCase().contains(search)
                    || car.getPlateNumber().toLowerCase().contains(search)
                    || car.getBrand().toLowerCase().contains(search)
                    || car.getModel().toLowerCase().contains(search)
                    || car.getCarType().toLowerCase().contains(search)
                    || car.getStatus().toLowerCase().contains(search)) {
                results.add(car);
            }
        }

        return results;
    }

    public int countByStatus(String status) {
        int count = 0;

        for (Car car : cars) {
            if (car.getStatus().equalsIgnoreCase(status)) count++;
        }

        return count;
    }
}

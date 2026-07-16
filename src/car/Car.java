package car;

public class Car {
    private final String carId;
    private String plateNumber;
    private String brand;
    private String model;
    private int year;
    private String carType;
    private double dailyRent;
    private int mileage;
    private String status;

    public Car(String carId, String plateNumber, String brand, String model,
               int year, String carType, double dailyRent, int mileage, String status) {
        this.carId = carId;
        this.plateNumber = plateNumber;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.carType = carType;
        this.dailyRent = dailyRent;
        this.mileage = mileage;
        this.status = status;
    }

    public String getCarId() { return carId; }
    public String getPlateNumber() { return plateNumber; }
    public String getBrand() { return brand; }
    public String getModel() { return model; }
    public int getYear() { return year; }
    public String getCarType() { return carType; }
    public double getDailyRent() { return dailyRent; }
    public int getMileage() { return mileage; }
    public String getStatus() { return status; }

    public void setPlateNumber(String plateNumber) { this.plateNumber = plateNumber; }
    public void setBrand(String brand) { this.brand = brand; }
    public void setModel(String model) { this.model = model; }
    public void setYear(int year) { this.year = year; }
    public void setCarType(String carType) { this.carType = carType; }
    public void setDailyRent(double dailyRent) { this.dailyRent = dailyRent; }
    public void setMileage(int mileage) { this.mileage = mileage; }
    public void setStatus(String status) { this.status = status; }

    public String toFileString() {
        return carId + "|" + plateNumber + "|" + brand + "|" + model + "|" +
               year + "|" + carType + "|" + dailyRent + "|" + mileage + "|" + status;
    }

    public static Car fromFileString(String line) {
        try {
            String[] data = line.split("\\|", -1);
            if (data.length != 9) return null;
            return new Car(data[0], data[1], data[2], data[3],
                    Integer.parseInt(data[4]), data[5],
                    Double.parseDouble(data[6]),
                    Integer.parseInt(data[7]), data[8]);
        } catch (Exception e) {
            return null;
        }
    }
}

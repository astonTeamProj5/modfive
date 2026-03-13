public class Bus {
    private Integer number;
    private String model;
    private Integer mileage;

    public Bus(Integer number, String model, Integer mileage) {
        this.number = number;
        this.model = model;
        this.mileage = mileage;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }
}

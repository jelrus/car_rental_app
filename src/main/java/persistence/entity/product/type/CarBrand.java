package persistence.entity.product.type;

public enum CarBrand {

    BMW("BMW"),
    MERCEDES("Mercedes"),
    HONDA("Honda"),
    TOYOTA("Toyota");

    private final String carBrand;

    CarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getCarBrand() {
        return carBrand;
    }
}
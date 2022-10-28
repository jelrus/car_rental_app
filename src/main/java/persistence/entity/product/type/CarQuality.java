package persistence.entity.product.type;

public enum CarQuality {
    MPV("MPV"),
    LUXURY("Luxury"),
    SPORTS("Sports"),
    SUV("SUV");

    private final String carQuality;

    CarQuality(String carQuality) {
        this.carQuality = carQuality;
    }

    public String getCarQuality() {
        return carQuality;
    }
}
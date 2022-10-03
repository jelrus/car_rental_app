package persistence.entity.product;

import persistence.entity.BaseEntity;
import persistence.entity.product.type.CarBrand;
import persistence.entity.product.type.CarQualityType;

import java.math.BigDecimal;
import java.util.Objects;

public class Car extends BaseEntity {

    private String title;
    private String productPic;
    private CarBrand carBrand;
    private CarQualityType carQualityType;
    private String info;
    private BigDecimal rentalPrice;

    public Car() {
        super();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProductPic() {
        return productPic;
    }

    public void setProductPic(String productPic) {
        this.productPic = productPic;
    }

    public CarBrand getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(CarBrand carBrand) {
        this.carBrand = carBrand;
    }

    public CarQualityType getCarQualityType() {
        return carQualityType;
    }

    public void setCarQualityType(CarQualityType carQualityType) {
        this.carQualityType = carQualityType;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public BigDecimal getRentalPrice() {
        return rentalPrice;
    }

    public void setRentalPrice(BigDecimal rentalPrice) {
        this.rentalPrice = rentalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Car car = (Car) o;
        return Objects.equals(title, car.title) &&
               Objects.equals(productPic, car.productPic) &&
               carBrand == car.carBrand &&
               carQualityType == car.carQualityType &&
               Objects.equals(info, car.info) &&
               Objects.equals(rentalPrice, car.rentalPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), title, productPic, carBrand, carQualityType, info, rentalPrice);
    }
}
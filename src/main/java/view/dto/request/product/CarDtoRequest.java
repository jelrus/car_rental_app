package view.dto.request.product;

import persistence.entity.product.type.CarBrand;
import persistence.entity.product.type.CarQuality;
import view.dto.request.DtoRequest;

import java.math.BigDecimal;
import java.util.Objects;

public class CarDtoRequest extends DtoRequest {

    private String title;
    private String productPic;
    private CarBrand carBrand;
    private CarQuality carQuality;
    private String info;
    private BigDecimal rentalPrice;
    private Boolean enabled;

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

    public CarQuality getCarQuality() {
        return carQuality;
    }

    public void setCarQuality(CarQuality carQuality) {
        this.carQuality = carQuality;
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

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarDtoRequest that = (CarDtoRequest) o;
        return Objects.equals(title, that.title) &&
               Objects.equals(productPic, that.productPic) &&
               carBrand == that.carBrand &&
               carQuality == that.carQuality &&
               Objects.equals(info, that.info) &&
               Objects.equals(rentalPrice, that.rentalPrice) &&
               Objects.equals(enabled, that.enabled);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, productPic, carBrand, carQuality, info, rentalPrice, enabled);
    }

    @Override
    public String toString() {
        return "CarDtoRequest{" +
                "title='" + title + '\'' +
                ", productPic='" + productPic + '\'' +
                ", carBrand=" + carBrand +
                ", carQuality=" + carQuality +
                ", info='" + info + '\'' +
                ", rentalPrice=" + rentalPrice +
                ", enabled=" + enabled +
                '}';
    }
}
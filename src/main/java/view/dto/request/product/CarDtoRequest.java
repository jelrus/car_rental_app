package view.dto.request.product;

import persistence.entity.annotations.Column;
import persistence.entity.product.type.CarBrand;
import persistence.entity.product.type.CarQualityType;
import view.dto.request.DtoRequest;

import java.math.BigDecimal;
import java.util.Objects;

public class CarDtoRequest extends DtoRequest {

    private String title;
    private String productPic;
    private CarBrand brand;
    private CarQualityType quality;
    private String info;
    private BigDecimal rentalPrice;

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

    public CarBrand getBrand() {
        return brand;
    }

    public void setBrand(CarBrand brand) {
        this.brand = brand;
    }

    public CarQualityType getQuality() {
        return quality;
    }

    public void setQuality(CarQualityType quality) {
        this.quality = quality;
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
        CarDtoRequest that = (CarDtoRequest) o;
        return Objects.equals(title, that.title) &&
               Objects.equals(productPic, that.productPic) &&
               brand == that.brand &&
               quality == that.quality &&
               Objects.equals(info, that.info) &&
               Objects.equals(rentalPrice, that.rentalPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, productPic, brand, quality, info, rentalPrice);
    }

    @Override
    public String toString() {
        return "CarDtoRequest{" +
                "title='" + title + '\'' +
                ", productPic='" + productPic + '\'' +
                ", brand=" + brand +
                ", quality=" + quality +
                ", info='" + info + '\'' +
                ", rentalPrice=" + rentalPrice +
                '}';
    }
}

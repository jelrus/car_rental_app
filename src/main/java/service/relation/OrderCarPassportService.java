package service.relation;

import persistence.entity.interaction.Passport;
import persistence.entity.product.Car;
import persistence.entity.relation.OrderCarPassport;
import service.BaseService;

import java.math.BigDecimal;

public interface OrderCarPassportService extends BaseService<OrderCarPassport> {

    Passport findPassportByOrder(Long passportId);

    Car findCarByOrder(Long carId);

    BigDecimal calculateCost(Long orderId);
}
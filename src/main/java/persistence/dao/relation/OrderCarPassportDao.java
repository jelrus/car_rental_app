package persistence.dao.relation;

import persistence.dao.BaseDao;
import persistence.entity.interaction.Passport;
import persistence.entity.product.Car;
import persistence.entity.relation.OrderCarPassport;

public interface OrderCarPassportDao extends BaseDao<OrderCarPassport> {

    Passport findPassportByOrder(Long orderId);

    Car findCarByOrder(Long orderId);
}
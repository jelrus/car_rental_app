package test;

import persistence.dao.impl.interaction.ActionDao;
import persistence.dao.impl.interaction.OrderDao;
import persistence.dao.impl.interaction.PassportDao;
import persistence.dao.impl.interaction.impl.ActionDaoImpl;
import persistence.dao.impl.interaction.impl.OrderDaoImpl;
import persistence.dao.impl.interaction.impl.PassportDaoImpl;
import persistence.dao.impl.product.CarDao;
import persistence.dao.impl.product.impl.CarDaoImpl;
import persistence.dao.impl.relation.OrderActionsDao;
import persistence.dao.impl.relation.OrderCarDao;
import persistence.dao.impl.relation.OrderPassportDao;
import persistence.dao.impl.relation.UserOrdersDao;
import persistence.dao.impl.relation.impl.OrderActionsDaoImpl;
import persistence.dao.impl.relation.impl.OrderCarDaoImpl;
import persistence.dao.impl.relation.impl.OrderPassportDaoImpl;
import persistence.dao.impl.relation.impl.UserOrdersDaoImpl;
import persistence.dao.impl.user.UserDao;
import persistence.dao.impl.user.impl.UserDaoImpl;
import persistence.datatable.DataTableRequest;
import persistence.entity.interaction.Action;
import persistence.entity.interaction.Order;
import persistence.entity.interaction.Passport;
import persistence.entity.interaction.type.OrderStatus;
import persistence.entity.product.Car;
import persistence.entity.product.type.CarBrand;
import persistence.entity.product.type.CarQualityType;
import persistence.entity.relation.OrderActions;
import persistence.entity.relation.OrderCar;
import persistence.entity.relation.OrderPassport;
import persistence.entity.relation.UserOrders;
import persistence.entity.user.BaseUser;
import persistence.entity.user.impl.Admin;
import persistence.entity.user.impl.User;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;

public class Main {

    public static void main(String[] args) throws SQLException {

        DataTableRequest dtr = new DataTableRequest();
        dtr.setCurrentPage(1);
        dtr.setPageSize(10);
        dtr.setOrder("id");
        dtr.setSort("ASC");

        /*BaseUser bu = new User();
        bu.setCreated(new Date());
        bu.setUpdated(new Date());
        bu.setUsername("jelrus");
        bu.setPassword("moloko11");
        bu.setFirstName("Vladyslaw");
        bu.setLastName("Gordyeyev");
        bu.setProfilePic("src/main");
        bu.setBalance(new BigDecimal("2000.00"));
        bu.setDescription("My profile");
        bu.setEnabled(true);

        UserDao u = new UserDaoImpl();
        u.create(bu);

        Car car = new Car();
        car.setCreated(new Date());
        car.setUpdated(new Date());
        car.setTitle("New Car");
        car.setProductPic("src/main/car");
        car.setBrand(CarBrand.BMW);
        car.setQuality(CarQualityType.LUXURY);
        car.setInfo("asdasda");
        car.setRentalPrice(new BigDecimal("1000.00"));

        CarDao c = new CarDaoImpl();
        c.create(car);


        Order order = new Order();
        order.setCreated(new Date());
        order.setUpdated(new Date());
        order.setWithDriver(true);
        order.setLeaseTermStart(new Date());
        order.setLeaseTermEnd(new Date());
        order.setOrderStatus(OrderStatus.PROCESSING);

        OrderDao o = new OrderDaoImpl();
        o.create(order);

        Passport passport = new Passport();
        passport.setCreated(new Date());
        passport.setUpdated(new Date());
        passport.setFirstName("Vladyslav");
        passport.setLastName("Gordyeyev");
        passport.setCountry("Ukraine");
        passport.setZipCode("87500");
        passport.setRegion("Donetska obl.");
        passport.setCity("Mariupol");
        passport.setStreet("Nakhimova ave");
        passport.setBuilding("101");
        passport.setPhoneNumber("+380969776048");
        passport.setEmail("jelrus@mail.ru");

        PassportDao p = new PassportDaoImpl();
        p.create(passport);

        Action action = new Action();
        action.setCreated(new Date());
        action.setUpdated(new Date());
        action.setIdentifier("AAXX");
        action.setMessage("Something");

        ActionDao a = new ActionDaoImpl();
        a.create(action);*/

        /*OrderActions orderActions = new OrderActions();
        orderActions.setCreated(new Date());
        orderActions.setUpdated(new Date());
        orderActions.setOrderId(1L);
        orderActions.setActionId(1L);

        OrderActionsDao o = new OrderActionsDaoImpl();
        System.out.println(o.findActionsByOrder(1L, dtr).getItems());*/
        /*o.create(orderActions);*/

        /*OrderCar orderCar = new OrderCar();
        orderCar.setCreated(new Date());
        orderCar.setUpdated(new Date());
        orderCar.setOrderId(1L);
        orderCar.setCarId(1L);
        System.out.println(orderCar);

        OrderCarDao o = new OrderCarDaoImpl();
        o.create(orderCar);*/

        /*OrderPassport orderPassport = new OrderPassport();
        orderPassport.setCreated(new Date());
        orderPassport.setUpdated(new Date());
        orderPassport.setOrderId(1L);
        orderPassport.setPassportId(1L);

        OrderPassportDao o = new OrderPassportDaoImpl();
        o.create(orderPassport);*/

        /*UserOrders userOrders = new UserOrders();
        userOrders.setCreated(new Date());
        userOrders.setUpdated(new Date());
        userOrders.setUserId(1L);
        userOrders.setOrderId(1L);

        UserOrdersDao u = new UserOrdersDaoImpl();
        u.create(userOrders);*/
    }
}

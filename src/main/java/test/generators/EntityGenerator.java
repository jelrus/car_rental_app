package test.generators;

import persistence.entity.interaction.Action;
import persistence.entity.interaction.Order;
import persistence.entity.interaction.Passport;
import persistence.entity.interaction.type.OrderStatus;
import persistence.entity.product.Car;
import persistence.entity.product.type.CarBrand;
import persistence.entity.product.type.CarQualityType;
import persistence.entity.user.impl.Admin;
import persistence.entity.user.impl.Manager;
import persistence.entity.user.impl.User;
import persistence.entity.user.type.UserRole;

import java.math.BigDecimal;
import java.util.Date;

public class EntityGenerator {

    public static User generateUser() {
        User user = new User();
        user.setCreated(new Date());
        user.setUpdated(new Date());
        user.setUsername("jelrus");
        user.setPassword("password");
        user.setFirstName("Mylo");
        user.setLastName("Lohopulos");
        user.setProfilePic("src/main");
        user.setBalance(new BigDecimal("1000.00"));
        user.setDescription("myprofile");
        user.setEnabled(true);
        return user;
    }

    public static Manager generateManager() {
        Manager manager = new Manager();
        manager.setCreated(new Date());
        manager.setUpdated(new Date());
        manager.setUsername("vasiliy");
        manager.setPassword("password11");
        manager.setFirstName("Mylo");
        manager.setLastName("Lohopulos");
        manager.setProfilePic("src/main");
        manager.setBalance(new BigDecimal("1000.00"));
        manager.setDescription("myprofile");
        manager.setEnabled(true);
        return manager;
    }

    public static Admin generateAdmin() {
        Admin admin = new Admin();
        admin.setCreated(new Date());
        admin.setUpdated(new Date());
        admin.setUsername("vasiliy11");
        admin.setPassword("password222");
        admin.setFirstName("Mylo");
        admin.setLastName("Lohopulos");
        admin.setProfilePic("src/main");
        admin.setBalance(new BigDecimal("1000.00"));
        admin.setDescription("myprofile");
        admin.setEnabled(true);
        return admin;
    }

    public static Car generateCar() {
        Car car = new Car();
        car.setCreated(new Date());
        car.setUpdated(new Date());
        car.setTitle("BMW X5");
        car.setProductPic("src/main");
        car.setBrand(CarBrand.BMW);
        car.setQuality(CarQualityType.MPV);
        car.setInfo("Something");
        car.setRentalPrice(new BigDecimal("1000.00"));
        return car;
    }

    public static Passport generatePassport() {
        Passport passport = new Passport();
        passport.setCreated(new Date());
        passport.setUpdated(new Date());
        passport.setFirstName("first_name");
        passport.setLastName("last_name");
        passport.setAge(20);
        passport.setCountry("Ukraine");
        passport.setZipCode("87500");
        passport.setRegion("sdasd");
        passport.setCity("Mariupol");
        passport.setStreet("Nakhimova ave");
        passport.setBuilding("101");
        passport.setPhoneNumber("+380969776048");
        passport.setEmail("jelrus@mail.ru");
        return passport;
    }

    public static Order generateOrder() {
        Order order = new Order();
        order.setCreated(new Date());
        order.setUpdated(new Date());
        order.setWithDriver(true);
        order.setLeaseTermStart(new Date());
        order.setLeaseTermEnd(new Date());
        order.setOrderStatus(OrderStatus.PROCESSING);
        return order;
    }

    public static Action generateAction() {
        Action action = new Action();
        action.setCreated(new Date());
        action.setUpdated(new Date());
        action.setIdentifier(String.valueOf(new Date().getTime()));
        return action;
    }
}

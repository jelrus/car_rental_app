package util.resultsets;

import persistence.entity.interaction.Action;
import persistence.entity.interaction.Order;
import persistence.entity.interaction.Passport;
import persistence.entity.interaction.type.OrderStatus;
import persistence.entity.product.Car;
import persistence.entity.product.type.CarBrand;
import persistence.entity.product.type.CarQuality;
import persistence.entity.relation.ManagerActions;
import persistence.entity.relation.OrderActions;
import persistence.entity.relation.OrderCarPassport;
import persistence.entity.relation.UserOrders;
import persistence.entity.user.BaseUser;
import persistence.entity.user.type.RoleType;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class ResultSetConverter {

    public static Action convertResultToAction(ResultSet rs) {
        Action action = new Action();

        try {
            action.setId(rs.getLong("id"));
            action.setCreated(rs.getTimestamp("created"));
            action.setUpdated(rs.getTimestamp("updated"));
            action.setIdentifier(rs.getString("identifier"));
            action.setMessage(rs.getString("message"));
            action.setEnabled(rs.getBoolean("enabled"));
        } catch (SQLException resEx) {
            resEx.printStackTrace();
        }

        return action;
    }

    public static Order convertResultToOrder(ResultSet rs) {
        Order order = new Order();

        try {
            order.setId(rs.getLong("id"));
            order.setCreated(rs.getTimestamp("created"));
            order.setUpdated(rs.getTimestamp("updated"));
            order.setWithDriver(rs.getBoolean("with_driver"));
            order.setStart(rs.getTimestamp("start"));
            order.setEnd(rs.getTimestamp("end"));
            order.setOrderStatus(OrderStatus.valueOf(rs.getString("order_status")));
            order.setEnabled(rs.getBoolean("enabled"));
        } catch (SQLException resEx) {
            resEx.printStackTrace();
        }

        return order;
    }

    public static Passport convertResultToPassport(ResultSet rs) {
        Passport passport = new Passport();

        try {
            passport.setId(rs.getLong("id"));
            passport.setCreated(rs.getTimestamp("created"));
            passport.setUpdated(rs.getTimestamp("updated"));
            passport.setFirstName(rs.getString("first_name"));
            passport.setLastName(rs.getString("last_name"));
            passport.setBirthDate(rs.getTimestamp("birth_date"));
            passport.setCountry(rs.getString("country"));
            passport.setZipCode(rs.getString("zip_code"));
            passport.setRegion(rs.getString("region"));
            passport.setCity(rs.getString("city"));
            passport.setStreet(rs.getString("street"));
            passport.setBuilding(rs.getString("building"));
            passport.setPhoneNumber(rs.getString("phone_number"));
            passport.setEmail(rs.getString("email"));
        } catch (SQLException resEx) {
            resEx.printStackTrace();
        }

        return passport;
    }

    public static ManagerActions convertResultToManagerActions(ResultSet rs) {
        ManagerActions managerActions = new ManagerActions();

        try {
            managerActions.setId(rs.getLong("id"));
            managerActions.setCreated(rs.getTimestamp("created"));
            managerActions.setUpdated(rs.getTimestamp("updated"));
            managerActions.setUserId(rs.getLong("user_id"));
            managerActions.setActionId(rs.getLong("action_id"));
        } catch (SQLException resEx) {
            resEx.printStackTrace();
        }

        return managerActions;
    }

    public static OrderActions convertResultToOrderActions(ResultSet rs) {
        OrderActions orderActions = new OrderActions();

        try {
            orderActions.setId(rs.getLong("id"));
            orderActions.setCreated(rs.getTimestamp("created"));
            orderActions.setUpdated(rs.getTimestamp("updated"));
            orderActions.setOrderId(rs.getLong("order_id"));
            orderActions.setActionId(rs.getLong("action_id"));
        } catch (SQLException resEx) {
            resEx.printStackTrace();
        }

        return orderActions;
    }

    public static OrderCarPassport convertResultToOrderCarPassport(ResultSet rs) {
        OrderCarPassport orderCarPassport = new OrderCarPassport();

        try {
            orderCarPassport.setId(rs.getLong("id"));
            orderCarPassport.setCreated(rs.getTimestamp("created"));
            orderCarPassport.setUpdated(rs.getTimestamp("updated"));
            orderCarPassport.setOrderId(rs.getLong("order_id"));
            orderCarPassport.setCarId(rs.getLong("car_id"));
            orderCarPassport.setPassportId(rs.getLong("passport_id"));
        } catch (SQLException resEx) {
            resEx.printStackTrace();
        }

        return orderCarPassport;
    }

    public static UserOrders convertResultToUserOrders(ResultSet rs) {
        UserOrders userOrders = new UserOrders();

        try {
            userOrders.setId(rs.getLong("id"));
            userOrders.setCreated(rs.getTimestamp("created"));
            userOrders.setUpdated(rs.getTimestamp("updated"));
            userOrders.setUserId(rs.getLong("user_id"));
            userOrders.setOrderId(rs.getLong("order_id"));
        } catch (SQLException resEx) {
            resEx.printStackTrace();
        }

        return userOrders;
    }

    public static Car convertResultToCar(ResultSet rs) {
        Car car = new Car();

        try {
            car.setId(rs.getLong("id"));
            car.setCreated(rs.getTimestamp("created"));
            car.setUpdated(rs.getTimestamp("updated"));
            car.setTitle(rs.getString("title"));
            car.setProductPic(rs.getString("product_pic"));
            car.setCarBrand(CarBrand.valueOf(rs.getString("brand")));
            car.setCarQuality(CarQuality.valueOf(rs.getString("quality")));
            car.setInfo(rs.getString("info"));
            car.setRentalPrice(rs.getBigDecimal("rental_price"));
            car.setEnabled(rs.getBoolean("enabled"));

        } catch (SQLException resEx) {
            resEx.printStackTrace();
        }

        return car;
    }

    public static BaseUser convertResultToUser(ResultSet rs) {
        BaseUser baseUser = new BaseUser();

        try {
            baseUser.setId(rs.getLong("id"));
            baseUser.setCreated(rs.getTimestamp("created"));
            baseUser.setUpdated(rs.getTimestamp("updated"));
            baseUser.setUsername(rs.getString("username"));
            baseUser.setFirstName(rs.getString("first_name"));
            baseUser.setLastName(rs.getString("last_name"));
            baseUser.setProfilePic(rs.getString("profile_pic"));
            baseUser.setBalance(rs.getBigDecimal("balance"));
            baseUser.setDescription(rs.getString("description"));
            baseUser.setEnabled(rs.getBoolean("enabled"));
            baseUser.setRoleType(RoleType.valueOf(rs.getString("role_type")));
        } catch (SQLException resEx) {
            resEx.printStackTrace();
        }

        return baseUser;
    }
}
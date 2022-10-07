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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
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
        
    }
}

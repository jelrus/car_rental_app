package test;

import persistence.dao.impl.interaction.ActionDao;
import persistence.dao.impl.interaction.OrderDao;
import persistence.dao.impl.interaction.PassportDao;
import persistence.dao.impl.interaction.impl.ActionDaoImpl;
import persistence.dao.impl.interaction.impl.OrderDaoImpl;
import persistence.dao.impl.interaction.impl.PassportDaoImpl;
import persistence.dao.impl.product.CarDao;
import persistence.dao.impl.product.impl.CarDaoImpl;
import persistence.dao.impl.relation.*;
import persistence.dao.impl.relation.impl.*;
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
import persistence.entity.relation.*;
import persistence.entity.user.BaseUser;
import persistence.entity.user.impl.Admin;
import persistence.entity.user.impl.Manager;
import persistence.entity.user.impl.User;
import persistence.entity.user.type.UserRole;
import service.impl.interaction.ActionService;
import service.impl.interaction.impl.ActionServiceImpl;
import service.impl.user.ManagerService;
import service.impl.user.impl.ManagerServiceImpl;

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

        /*BaseUser baseUser = new Manager();
        baseUser.setCreated(new Date());
        baseUser.setUpdated(new Date());
        baseUser.setFirstName("me");
        baseUser.setLastName("not me");
        baseUser.setUsername("jelrus");
        baseUser.setPassword("moloko11");
        baseUser.setProfilePic("src/main");
        baseUser.setBalance(new BigDecimal("10000.00"));
        baseUser.setDescription("asdas");
        baseUser.setEnabled(true);
        baseUser.setRoleType(UserRole.MANAGER);

        Order order = new Order();
        order.setCreated(new Date());
        order.setUpdated(new Date());
        order.setWithDriver(true);
        order.setLeaseTermStart(new Date());
        order.setLeaseTermEnd(new Date());
        order.setOrderStatus(OrderStatus.PROCESSING);

        Action action = new Action();
        action.setCreated(new Date());
        action.setUpdated(new Date());
        action.setIdentifier(String.valueOf(new Date().getTime()));
        action.setMessage("Your status updated");

        OrderDaoImpl orderDao = new OrderDaoImpl();
        ActionDaoImpl actionDao = new ActionDaoImpl();
        UserDaoImpl userDao = new UserDaoImpl();

        *//*OrderActions orderActions = new OrderActions();
        orderActions.setCreated(new Date());
        orderActions.setUpdated(new Date());
        orderActions.setOrderId(orderDao.create(order));
        orderActions.setActionId(actionDao.create(action));

        OrderActionsDaoImpl orderActionsDao = new OrderActionsDaoImpl();
        orderActionsDao.create(orderActions);*//*

        ManagerActions managerActions = new ManagerActions();
        managerActions.setCreated(new Date());
        managerActions.setUpdated(new Date());
        managerActions.setManagerId(userDao.create(baseUser));
        managerActions.setActionId(actionDao.create(action));

        ManagerActionsDaoImpl managerActionsDao = new ManagerActionsDaoImpl();
        System.out.println(managerActionsDao.create(managerActions));*/
    }
}

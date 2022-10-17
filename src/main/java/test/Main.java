package test;


import com.sun.net.httpserver.Headers;
import facade.interaction.ActionFacade;
import facade.interaction.impl.ActionFacadeImpl;
import persistence.dao.impl.interaction.PassportDao;
import persistence.dao.impl.interaction.impl.PassportDaoImpl;
import persistence.datatable.DataTableRequest;
import persistence.entity.interaction.Action;
import persistence.entity.interaction.Order;
import persistence.entity.interaction.Passport;
import persistence.entity.product.Car;
import persistence.entity.relation.ManagerActions;
import persistence.entity.user.BaseUser;
import persistence.entity.user.impl.Admin;
import persistence.entity.user.impl.Manager;
import persistence.entity.user.impl.User;
import persistence.entity.user.type.UserRole;
import service.impl.interaction.ActionService;
import service.impl.interaction.impl.ActionServiceImpl;
import service.impl.relation.ManagerActionsService;
import service.impl.relation.impl.ManagerActionsServiceImpl;
import service.impl.user.UserService;
import service.impl.user.impl.UserServiceImpl;
import test.generators.EntityGenerator;
import view.dto.request.interaction.ActionDtoRequest;

import javax.management.remote.JMXPrincipal;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.sql.DataSource;
import java.math.BigDecimal;
import java.net.http.HttpHeaders;
import java.security.Principal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) throws SQLException, NamingException {


    }
}

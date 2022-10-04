package test;

import config.datasource.DataSourceConnection;
import config.datasource.impl.DataSourceConnectionImpl;
import persistence.dao.impl.user.UserDao;
import persistence.dao.impl.user.impl.UserDaoImpl;
import persistence.entity.user.impl.User;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;


public class Main {

    public static void main(String[] args) throws SQLException {

        User u = new User();
        u.setCreated(new Date());
        u.setUpdated(new Date());

        u.setUsername("jelrus");
        u.setPassword("moloko11");

        u.setFirstName("aaaa");
        u.setLastName("bbbbb");
        u.setProfilePic("C:/Documents/something.jpg");
        u.setBalance(new BigDecimal("20111233.0010"));
        u.setDescription("ccccc");
        u.setEnabled(true);
        u.setRoleType(u.getRoleType());
        System.out.println(u);
        UserDao ud = new UserDaoImpl();
        /*System.out.println(ud.create(u));*/
        /*System.out.println(ud.update(u));*/
        System.out.println(ud.existById(2L));
    }


}

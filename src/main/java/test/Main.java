package test;

import persistence.datatable.DataTableRequest;
import persistence.entity.user.BaseUser;
import persistence.entity.user.impl.Manager;
import persistence.entity.user.type.UserRole;

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

        BaseUser baseUser = new Manager();
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


    }
}

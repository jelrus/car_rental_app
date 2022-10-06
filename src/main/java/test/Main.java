package test;

import persistence.dao.impl.user.impl.UserDaoImpl;
import persistence.datatable.DataTableRequest;
import persistence.entity.interaction.Action;
import persistence.entity.user.BaseUser;
import persistence.entity.user.impl.Admin;
import persistence.entity.user.impl.Manager;
import persistence.entity.user.impl.User;
import persistence.entity.user.type.UserRole;
import util.QueryGenerator;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) throws SQLException {

        UserDaoImpl udi = new UserDaoImpl();
        DataTableRequest bureq = new DataTableRequest();
        bureq.setCurrentPage(1);
        bureq.setPageSize(10);
        bureq.setOrder("id");
        bureq.setSort("desc");

        System.out.println(udi.count());
    }
}

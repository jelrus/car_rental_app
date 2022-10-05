package test;

import persistence.entity.interaction.Action;
import persistence.entity.interaction.Order;
import persistence.entity.user.impl.Admin;
import persistence.entity.user.impl.User;

import java.sql.SQLException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws SQLException {

        String CREATE = QueryGenerator.createQuery(Action.class);
        String UPDATE = QueryGenerator.updateQuery(Action.class, "id", List.of());
        String DELETE = QueryGenerator.deleteQuery(Action.class, "id");
        String EXIST_BY_ID = QueryGenerator.existBy(Action.class, "id");
        String COUNT = QueryGenerator.count(Action.class);
        String FIND_BY_ID = QueryGenerator.findBy(Action.class, "id");
        String FIND_ALL = QueryGenerator.findAll(Action.class);

        System.out.println(CREATE);
        System.out.println(UPDATE);
        System.out.println(DELETE);
        System.out.println(EXIST_BY_ID);
        System.out.println(COUNT);
        System.out.println(FIND_BY_ID);
        System.out.println(FIND_ALL);
    }
}

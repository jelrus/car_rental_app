package util.queries.relation;

import persistence.datatable.DataTableRequest;

public final class UserOrdersQueries {

    public static final String CREATE = "INSERT INTO user_orders VALUES(default, ?, ?, ?, ?);";
    public static final String UPDATE_FULL = "UPDATE user_orders SET updated=?, user_id=?, order_id, where id=?;";
    public static final String EXIST = "SELECT COUNT(*) AS count FROM user_orders WHERE id=?;";
    public static final String COUNT = "SELECT COUNT(*) AS count FROM user_orders;";
    public static final String DELETE = "DELETE FROM user_orders WHERE id=?;";
    public static final String FIND_BY_ID = "SELECT * FROM user_orders WHERE id = ?;";
    public static final String FIND_ALL = "SELECT * FROM user_orders;";
    public static final String FIND_ORDERS_BY_USER = "SELECT o.id, o.created, o.updated, o.with_driver, o.start, o.end, o.order_status, o.enabled " +
                                                     "FROM user_orders JOIN orders o ON o.id = user_orders.order_id " +
                                                     "WHERE user_id = ?;";
    public static final String FIND_USER_BY_ORDER = "SELECT u.id, u.created, u.updated, u.username, u.first_name, u.last_name, " +
                                                    "u.profile_pic, u.balance, u.description, u.enabled, u.role_type " +
                                                    "FROM user_orders JOIN users u ON u.id = user_orders.user_id " +
                                                    "WHERE order_id=?;";

    public static String findAll(DataTableRequest req) {
        int limit = (req.getCurrentPage() - 1) * req.getPageSize();
        return "SELECT * FROM user_orders " +
               "JOIN users u ON u.id = user_orders.user_id " +
               "JOIN orders o ON o.id = user_orders.order_id " +
               "ORDER BY " + req.getSort() + " " + req.getOrder() + " " +
               "LIMIT " + limit + "," + req.getPageSize() + ";";
    }

    public static String findAllOrdersByUser(DataTableRequest req) {
        int limit = (req.getCurrentPage() - 1) * req.getPageSize();
        return "SELECT o.id, o.created, o.updated, o.with_driver, o.start, o.end, o.order_status, o.enabled " +
               "FROM user_orders JOIN orders o ON o.id = user_orders.order_id " +
               "WHERE user_id = ? and o.enabled = true " +
               "ORDER BY " + req.getSort() + " " + req.getOrder() + " " +
               "LIMIT " + limit + "," + req.getPageSize() + ";";
    }
}
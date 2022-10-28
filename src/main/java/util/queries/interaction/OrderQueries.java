package util.queries.interaction;

import persistence.datatable.DataTableRequest;

public final class OrderQueries {

    public static final String CREATE = "INSERT INTO orders VALUES(default, ?, ?, ?, ?, ?, ?, ?);";
    public static final String UPDATE_FULL = "UPDATE orders SET updated=?, with_driver=?, start=?, end=?, order_status=?, enabled=? WHERE id?;";
    public static final String UPDATE_STATUS = "UPDATE orders SET updated=?, order_status=? WHERE id=?;";
    public static final String UPDATE_ACCESS = "UPDATE orders SET updated=?, enabled=? WHERE id=?";
    public static final String EXIST = "SELECT COUNT(*) AS count FROM orders WHERE id=?;";
    public static final String COUNT = "SELECT COUNT(*) AS count FROM orders;";
    public static final String DELETE = "DELETE FROM orders WHERE id=?;";
    public static final String FIND_BY_ID = "SELECT * FROM orders WHERE id = ?;";
    public static final String FIND_ALL = "SELECT * FROM actions;";

    public static String findAll(DataTableRequest req) {
        int limit = (req.getCurrentPage() - 1) * req.getPageSize();
        return "SELECT * FROM orders " +
               "ORDER BY " + req.getSort() + " " + req.getOrder() + " " +
               "LIMIT " + limit + "," + req.getPageSize() + ";";
    }

    public static String findAllFiltered(DataTableRequest req) {
        int limit = (req.getCurrentPage() - 1) * req.getPageSize();
        return "SELECT * FROM orders " +
                "WHERE enabled=true " +
                "ORDER BY " + req.getSort() + " " + req.getOrder() + " " +
                "LIMIT " + limit + "," + req.getPageSize() + ";";
    }
}
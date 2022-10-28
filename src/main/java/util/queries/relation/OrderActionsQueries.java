package util.queries.relation;

import persistence.datatable.DataTableRequest;

public final class OrderActionsQueries {

    public static final String CREATE = "INSERT INTO order_actions VALUES(default, ?, ?, ?, ?);";
    public static final String UPDATE_FULL = "UPDATE order_actions SET updated=?, order_id=?, action_id, where id=?;";
    public static final String EXIST = "SELECT COUNT(*) AS count FROM order_actions WHERE id=?;";
    public static final String COUNT = "SELECT COUNT(*) AS count FROM order_actions;";
    public static final String DELETE = "DELETE FROM order_actions WHERE id=?;";
    public static final String FIND_BY_ID = "SELECT * FROM order_actions WHERE id = ?;";
    public static final String FIND_ALL = "SELECT * FROM order_actions;";
    public static final String FIND_ACTIONS_BY_ORDER = "SELECT a.id, a.created, a.updated, a.identifier, a.message, a.enabled " +
                                                       "FROM order_actions JOIN actions a ON a.id = order_actions.action_id " +
                                                       "WHERE order_id = ?;";

    public static final String FIND_ACTIONS_BY_ORDER_FILTERED = "SELECT a.id, a.created, a.updated, a.identifier, a.message, a.enabled " +
            "FROM order_actions JOIN actions a ON a.id = order_actions.action_id " +
            "WHERE order_id = ? AND a.enabled = true;";

    public static final String FIND_ORDER_BY_ACTION = "SELECT o.id, o.created, o.updated, o.with_driver, o.start, o.end, " +
                                                      "o.order_status, o.enabled " +
                                                      "FROM order_actions JOIN orders o ON o.id = order_actions.order_id " +
                                                      "WHERE action_id=?;";

    public static String findAll(DataTableRequest req) {
        int limit = (req.getCurrentPage() - 1) * req.getPageSize();
        return "SELECT * FROM order_actions " +
               "ORDER BY " + req.getSort() + " " + req.getOrder() + " " +
               "LIMIT " + limit + "," + req.getPageSize() + ";";
    }

    public static String findAllActionsByOrder(DataTableRequest req) {
        int limit = (req.getCurrentPage() - 1) * req.getPageSize();
        return "SELECT a.id, a.created, a.updated, a.identifier, a.message, a.enabled " +
               "FROM order_actions JOIN actions a ON a.id = order_actions.action_id " +
               "WHERE order_id = ? and enabled = true " +
               "ORDER BY " + req.getSort() + " " + req.getOrder() + " " +
               "LIMIT " + limit + "," + req.getPageSize() + ";";
    }
}
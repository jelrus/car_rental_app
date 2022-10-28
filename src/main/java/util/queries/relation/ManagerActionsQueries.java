package util.queries.relation;

import persistence.datatable.DataTableRequest;

public final class ManagerActionsQueries {

    public static final String CREATE = "INSERT INTO manager_actions VALUES(default, ?, ?, ?, ?);";
    public static final String UPDATE_FULL = "UPDATE manager_actions SET updated=?, user_id=?, action_id, where id=?;";
    public static final String EXIST = "SELECT COUNT(*) AS count FROM manager_actions WHERE id=?;";
    public static final String COUNT = "SELECT COUNT(*) AS count FROM manager_actions;";
    public static final String DELETE = "DELETE FROM manager_actions WHERE id=?;";
    public static final String FIND_BY_ID = "SELECT * FROM manager_actions WHERE id = ?;";
    public static final String FIND_ALL = "SELECT * FROM manager_actions;";
    public static final String FIND_ACTIONS_BY_MANAGER = "SELECT a.id, a.created, a.updated, a.identifier, a.message, a.enabled " +
                                                         "FROM manager_actions JOIN actions a ON a.id = manager_actions.action_id " +
                                                         "WHERE user_id = ?;";
    public static final String FIND_MANAGER_BY_ACTION = "SELECT u.id, u.created, u.updated, u.username, u.first_name, u.last_name, " +
                                                        "u.profile_pic, u.balance, u.description, u.enabled, u.role_type " +
                                                        "FROM manager_actions JOIN users u ON u.id = manager_actions.user_id " +
                                                        "WHERE action_id=?;";

    public static String findAll(DataTableRequest req) {
        int limit = (req.getCurrentPage() - 1) * req.getPageSize();
        return "SELECT * FROM manager_actions " +
               "ORDER BY " + req.getSort() + " " + req.getOrder() + " " +
               "LIMIT " + limit + "," + req.getPageSize() + ";";
    }

    public static String findActionsByManager(DataTableRequest req) {
        int limit = (req.getCurrentPage() - 1) * req.getPageSize();
        return "SELECT a.id, a.created, a.updated, a.identifier, a.message, a.enabled " +
                "FROM manager_actions JOIN actions a ON a.id = manager_actions.action_id WHERE user_id = ? " +
                "ORDER BY " + req.getSort() + " " + req.getOrder() + " " +
                "LIMIT " + limit + "," + req.getPageSize() + ";";
    }
}
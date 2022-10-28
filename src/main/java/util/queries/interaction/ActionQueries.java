package util.queries.interaction;

import persistence.datatable.DataTableRequest;

public final class ActionQueries {

    public static final String CREATE = "INSERT INTO actions VALUES(default, ?, ?, ?, ?, ?);";
    public static final String UPDATE_FULL = "UPDATE actions SET updated=?, identifier=?, message=?, enabled=? WHERE id=?;";
    public static final String UPDATE_MESSAGE = "UPDATE actions SET updated=?, message=? WHERE id=?;";
    public static final String UPDATE_ACCESS = "UPDATE actions SET updated=?, enabled=? WHERE id=?";
    public static final String EXIST = "SELECT COUNT(*) AS count FROM actions WHERE id=?;";
    public static final String COUNT = "SELECT COUNT(*) AS count FROM actions;";
    public static final String DELETE = "DELETE FROM actions WHERE id=?;";
    public static final String FIND_BY_ID = "SELECT * FROM actions WHERE id = ?;";
    public static final String FIND_ALL = "SELECT * FROM actions;";

    public static String findAll(DataTableRequest req) {
        int limit = (req.getCurrentPage() - 1) * req.getPageSize();
        return "SELECT * FROM actions " +
               "ORDER BY " + req.getSort() + " " + req.getOrder() + " " +
               "LIMIT " + limit + "," + req.getPageSize() + ";";
    }

    public static String findAllFiltered(DataTableRequest req) {
        int limit = (req.getCurrentPage() - 1) * req.getPageSize();
        return "SELECT * FROM actions " +
               "WHERE enabled=true " +
               "ORDER BY " + req.getSort() + " " + req.getOrder() + " " +
               "LIMIT " + limit + "," + req.getPageSize() + ";";
    }
}
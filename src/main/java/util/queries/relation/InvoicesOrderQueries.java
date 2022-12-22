package util.queries.relation;

import persistence.datatable.DataTableRequest;

public final class InvoicesOrderQueries {

    public static final String CREATE = "INSERT INTO invoices_order VALUES(default, ?, ?, ?, ?, ?);";
    public static final String UPDATE_FULL = "UPDATE invoices_order SET updated=?, file_link=?, order_id=?, enabled=? where id=?;";
    public static final String EXIST = "SELECT COUNT(*) AS count FROM invoices_order WHERE id=?;";
    public static final String COUNT = "SELECT COUNT(*) AS count FROM invoices_order;";
    public static final String DELETE = "DELETE FROM invoices_order WHERE id=?;";
    public static final String FIND_BY_ID = "SELECT * FROM invoices_order WHERE id = ?;";
    public static final String FIND_ALL = "SELECT * FROM invoices_order;";
    public static final String FIND_ALL_BY_ORDER = "SELECT * FROM invoices_order WHERE order_id=?";
    public static final String FIND_ALL_BY_ORDER_FILTERED = "SELECT * FROM invoices_order WHERE order_id=? AND enabled=true";

    public static String findAll(DataTableRequest req) {
        int limit = (req.getCurrentPage() - 1) * req.getPageSize();
        return "SELECT * FROM invoices_order " +
                "ORDER BY " + req.getSort() + " " + req.getOrder() + " " +
                "LIMIT " + limit + "," + req.getPageSize() + ";";
    }
}
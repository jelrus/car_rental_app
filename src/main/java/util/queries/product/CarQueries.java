package util.queries.product;

import persistence.datatable.DataTableRequest;

public final class CarQueries {

    public static final String CREATE = "INSERT INTO cars VALUES(default, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    public static final String UPDATE_FULL = "UPDATE cars SET updated=?, title=?, product_pic=?, brand=?, " +
                                             "quality=?, info=?, rental_price=?, enabled=? where id=?;";
    public static final String UPDATE_ACCESS = "UPDATE cars SET updated=?, enabled=? WHERE id=?";
    public static final String EXIST = "SELECT COUNT(*) AS count FROM cars WHERE id=?;";
    public static final String COUNT = "SELECT COUNT(*) AS count FROM cars;";
    public static final String DELETE = "DELETE FROM cars WHERE id=?;";
    public static final String FIND_BY_ID = "SELECT * FROM cars WHERE id = ?;";
    public static final String FIND_ALL = "SELECT * FROM cars;";
    public static final String FIND_ALL_FILTERED = "SELECT * FROM cars where enabled=true;";

    public static String findAll(DataTableRequest req) {
        int limit = (req.getCurrentPage() - 1) * req.getPageSize();
        return "SELECT * FROM cars " +
               "ORDER BY " + req.getSort() + " " + req.getOrder() + " " +
               "LIMIT " + limit + "," + req.getPageSize() + ";";
    }

    public static String findAllFiltered(DataTableRequest req) {
        int limit = (req.getCurrentPage() - 1) * req.getPageSize();
        return "SELECT * FROM cars " +
                "WHERE enabled=true " +
                "ORDER BY " + req.getSort() + " " + req.getOrder() + " " +
                "LIMIT " + limit + "," + req.getPageSize() + ";";
    }
}
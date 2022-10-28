package util.queries.interaction;

import persistence.datatable.DataTableRequest;

public final class PassportQueries {

    public static final String CREATE = "INSERT INTO passports VALUES(default, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    public static final String UPDATE_FULL = "UPDATE passports SET updated=?, first_name=?, last_name=?, birth_date=?, " +
                                             "country=?, zip_code=?, region=?, city=?, " +
                                             "street=?, building=?, phone_number=?, email=? " +
                                             "where id=?;";
    public static final String EXIST = "SELECT COUNT(*) AS count FROM passports WHERE id=?;";
    public static final String COUNT = "SELECT COUNT(*) AS count FROM passports;";
    public static final String DELETE = "DELETE FROM passports WHERE id=?;";
    public static final String FIND_BY_ID = "SELECT * FROM passports WHERE id = ?;";
    public static final String FIND_ALL = "SELECT * FROM passports;";

    public static String findAll(DataTableRequest req) {
        int limit = (req.getCurrentPage() - 1) * req.getPageSize();
        return "SELECT * FROM passports " +
               "ORDER BY " + req.getSort() + " " + req.getOrder() + " " +
               "LIMIT " + limit + "," + req.getPageSize() + ";";
    }
}
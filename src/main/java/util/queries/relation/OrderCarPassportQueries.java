package util.queries.relation;

import persistence.datatable.DataTableRequest;

public final class OrderCarPassportQueries {

    public static final String CREATE = "INSERT INTO order_car_passport VALUES(default, ?, ?, ?, ?, ?);";
    public static final String UPDATE_FULL = "UPDATE order_car_passport SET updated=?, order_id=?, car_id, passport_id where id=?;";
    public static final String EXIST = "SELECT COUNT(*) AS count FROM order_car_passport WHERE id=?;";
    public static final String COUNT = "SELECT COUNT(*) AS count FROM order_car_passport;";
    public static final String DELETE = "DELETE FROM order_car_passport WHERE id=?;";
    public static final String FIND_BY_ID = "SELECT * FROM order_car_passport WHERE id = ?;";
    public static final String FIND_ALL = "SELECT * FROM order_car_passport;";
    public static final String FIND_PASSPORT_BY_ORDER = "SELECT p.id, p.created, p.updated, p.first_name, p.last_name, p.birth_date, " +
                                                               "p.country, p.zip_code, p.region, p.city, p.street, p.building, " +
                                                               "p.phone_number, p.email " +
                                                        "FROM order_car_passport JOIN passports p ON p.id = order_car_passport.passport_id " +
                                                        "WHERE order_id=?;";
    public static final String FIND_CAR_BY_ORDER = "SELECT c.id, c.created, c.updated, c.title, c.product_pic, c.brand, c.quality, c.info, c.rental_price, c.enabled " +
                                                    "FROM order_car_passport JOIN cars c ON c.id = order_car_passport.car_id " +
                                                    "WHERE order_id=?;";

    public static String findAll(DataTableRequest req) {
        int limit = (req.getCurrentPage() - 1) * req.getPageSize();
        return "SELECT * FROM order_car_passport " +
               "ORDER BY " + req.getSort() + " " + req.getOrder() + " " +
               "LIMIT " + limit + "," + req.getPageSize() + ";";
    }
}
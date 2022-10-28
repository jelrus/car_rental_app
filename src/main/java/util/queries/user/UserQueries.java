package util.queries.user;

import persistence.datatable.DataTableRequest;
import persistence.entity.user.type.RoleType;

public final class UserQueries {

    public static final String CREATE = "INSERT INTO users VALUES(default, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    public static final String UPDATE_FULL = "UPDATE users SET updated=?, username=?, password=?, first_name=?, " +
                                             "last_name=?, profile_pic=?, balance=?, description=?, " +
                                             "enabled=?, role_type=? where id=?;";
    public static final String DELETE = "DELETE FROM users WHERE id=?;";
    public static final String UPDATE_SECURITY_INFO = "UPDATE users SET updated=?, username=?, password=? WHERE id=?;";
    public static final String UPDATE_INFO = "UPDATE users SET updated=?, first_name=?, last_name=?, profile_pic=?, description=? WHERE id=?;";
    public static final String UPDATE_BALANCE = "UPDATE users SET updated=?, balance=? where id=?;";
    public static final String UPDATE_ACCESS = "UPDATE users SET updated=?, enabled=? where id=?;";
    public static final String UPDATE_ROLE = "UPDATE users SET updated=?, role_type=? where id=?;";
    public static final String EXIST = "SELECT COUNT(*) AS count FROM users WHERE id=?;";
    public static final String COUNT = "SELECT COUNT(*) AS count FROM users;";
    public static final String FIND_BY_ID = "SELECT * FROM users WHERE id = ?;";
    public static final String EXIST_BY_USERNAME_PASSWORD = "SELECT COUNT(*) AS count FROM users WHERE username=? AND password=?;";
    public static final String FIND_BY_USERNAME_PASSWORD = "SELECT * FROM users WHERE username=? AND password=?;";
    public static final String FIND_ALL = "SELECT * FROM users;";

    public static String findAll(DataTableRequest req) {
        int limit = (req.getCurrentPage() - 1) * req.getPageSize();
        return "SELECT * FROM users " +
               "GROUP BY id " +
               "ORDER BY " + req.getSort() + " " + req.getOrder() + " " +
               "LIMIT " + limit + "," + req.getPageSize() + ";";
    }

    public static String findAllByRole(DataTableRequest req) {
        int limit = (req.getCurrentPage() - 1) * req.getPageSize();
        return "SELECT * FROM users " +
               "WHERE role_type=? " +
               "GROUP BY id " +
               "ORDER BY " + req.getSort() + " " + req.getOrder() + " " +
               "LIMIT " + limit + "," + req.getPageSize() + ";";
    }
}
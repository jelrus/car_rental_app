package persistence.dao.impl.user.impl.queries;

public final class UserQueries {

    public final static String CREATE = "INSERT INTO USERS VALUES(default, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    public final static String UPDATE = "UPDATE USERS SET " +
                                        "updated = ?, username = ?, password = ?, " +
                                        "first_name = ?, last_name = ?, profile_pic = ?, balance = ?, description = ?, " +
                                        "enabled = ?, role_type = ? WHERE id = ?;";
    public final static String DELETE = "DELETE FROM USERS WHERE id = ?;";
    public final static String EXIST_BY_ID = "SELECT COUNT(*) AS COUNT FROM USERS WHERE id = ?;";
    public final static String COUNT = "SELECT COUNT(*) AS COUNT FROM USERS;";
    public final static String FIND_BY_ID = "SELECT * FROM USERS WHERE id = ?;";
    public final static String FIND_ALL = "SELECT * FROM USERS;";

    private UserQueries() {}

}

package test;

import config.DbPropertyConfig;
import config.impl.MySqlDbConfig;

public class Main {

    public static void main(String[] args) {

        MySqlDbConfig mySqlDbConfig = new MySqlDbConfig(new DbPropertyConfig());
        System.out.println(mySqlDbConfig);
    }
}

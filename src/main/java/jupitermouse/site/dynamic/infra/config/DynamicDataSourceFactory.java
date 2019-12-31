package jupitermouse.site.dynamic.infra.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.SQLException;

/**
 * DruidDataSource
 * @author jupiter
 */
public class DynamicDataSourceFactory {

    public static HikariDataSource buildDruidDataSource(HikariConfig config) {
        HikariDataSource hikariDataSource = new HikariDataSource(config);
        try {
            hikariDataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hikariDataSource;
    }
}
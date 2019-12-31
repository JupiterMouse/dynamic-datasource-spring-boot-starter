package jupitermouse.site.dynamic.infra.properties;

import com.zaxxer.hikari.HikariConfig;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author jupiter
 */
@ConfigurationProperties(prefix = "jm.dynamic")
@Data
public class DynamicDataSourceProperties {

    /**
     * 存储datasource
     */
    private Map<String, HikariConfig> datasource = new ConcurrentHashMap<>();

}

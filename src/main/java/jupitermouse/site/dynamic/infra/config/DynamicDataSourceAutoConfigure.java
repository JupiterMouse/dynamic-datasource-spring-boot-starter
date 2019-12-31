package jupitermouse.site.dynamic.infra.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jupitermouse.site.dynamic.infra.aop.DataSourceAspect;
import jupitermouse.site.dynamic.infra.properties.DynamicDataSourceProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.HashMap;
import java.util.Map;

/**
 * 配置多数据源
 * @author jupiter
 */
@EnableConfigurationProperties(DynamicDataSourceProperties.class)
public class DynamicDataSourceAutoConfigure {

    @Autowired
    private DynamicDataSourceProperties dynamicProperties;

    @Bean
    public DataSourceAspect dataSourceAspect(){
        return new DataSourceAspect();
    }

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public HikariConfig getHikariConfig() {
        return new HikariConfig();
    }

    @Bean
    public DynamicDataSource setDynamicDataSource(HikariConfig hikariConfig) {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setTargetDataSources(getDynamicDataSourceMap());
        // 默认数据源
        HikariDataSource defaultDataSource = DynamicDataSourceFactory.buildDruidDataSource(hikariConfig);
        // 切换数据源
        dynamicDataSource.setDefaultTargetDataSource(defaultDataSource);
        return dynamicDataSource;
    }

    private Map<Object, Object> getDynamicDataSourceMap() {
        Map<String, HikariConfig> configMap = dynamicProperties.getDatasource();
        Map<Object, Object> targetDataSources = new HashMap<>(configMap.size());
        configMap.forEach((k, v) -> {
            HikariDataSource hikariDataSource = DynamicDataSourceFactory.buildDruidDataSource(v);
            targetDataSources.put(k, hikariDataSource);
        });
        return targetDataSources;
    }

}
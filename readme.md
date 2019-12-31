# dynamic-datasource-spring-boot-starter

> springboot集成Hirika，动态切换数据源

## 依赖

还未发布到 maven 公共仓库

```xml
<dependency>
    <groupId>jupitermouse.site</groupId>
	  <artifactId>dynamic-datasource-spring-boot-starter</artifactId>
	  <version>1.0.0-SNAPSHOT</version>
</dependency>
```

## 使用

增加`@EnableDynamicDataSource` `jupitermouse.site.dynamic.infra.annotion.EnableDynamicDataSource`

### pom

> 使用时可排除mybatis-spring-boot-starter

```xml
<dependency>
  <groupId>jupitermouse.site</groupId>
  <artifactId>dynamic-datasource-spring-boot-starter</artifactId>
  <version>1.0.0-SNAPSHOT</version>
  <exclusions>
    <exclusion>
      <groupId>org.mybatis.spring.boot</groupId>
      <artifactId>mybatis-spring-boot-starter</artifactId>
    </exclusion>
  </exclusions>
</dependency>
```



### 配置

- 指定默认数据源

    `spring.datasource.hikari`

- 动态数据源

    `jm.dynamic.datasource.数据源名称`

示例：

```yml
spring:
  application:
    name: dynamic-datasource-spring-boot-starter
  datasource:
    hikari:
      jdbc-url: ${SPRING_DATASOURCE_URL:jdbc:mysql://db.mysql.com:3306/database?useUnicode=true&characterEncoding=utf-8&useSSL=false}
      username: ${SPRING_DATASOURCE_USERNAME:root}
      password: ${SPRING_DATASOURCE_PASSWORD:root}
      # 连接池最小空闲连接数
      minimum-idle: ${SPRING_DATASOURCE_MINIMUM_IDLE:20}
      # 连接池允许的最大连接数
      maximum-pool-size: ${SPRING_DATASOURCE_MAXIMUM_POOL_SIZE:200}
      # 等待连接池分配连接的最大时长（毫秒）
      connection-timeout: ${SPRING_DATASOURCE_CONNECTION_TIMEOUT:30000}
jm:
  dynamic:
    datasource:
      pg:
        jdbc-url: ${DYNAMIC_GP_URL:jdbc:postgresql://db.postgresql.com:5432/databaseName?searchpath=schema}
        username: ${DYNAMIC_GP_USERNAME:root}
        password: ${DYNAMIC_GP_PASSWORD:root}
        driver-class-name: ${DYNAMIC_GP_DRIVER:org.postgresql.Driver}
```

* 声明切换

  ```shell
  @Mapper
  public interface xxxMapper {
  
      @DS(name = "gp")
      List<xxxDTO> listxxx(xxxDTO dto);
  }
  ```




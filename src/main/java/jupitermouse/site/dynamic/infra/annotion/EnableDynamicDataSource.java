package jupitermouse.site.dynamic.infra.annotion;

import jupitermouse.site.dynamic.infra.config.DynamicDataSourceAutoConfigure;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * <p>
 *     启动类
 * </p>
 *
 * @author jupiter
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(DynamicDataSourceAutoConfigure.class)
@ComponentScan("jupitermouse.site.dynamic.*")
public @interface EnableDynamicDataSource {
}

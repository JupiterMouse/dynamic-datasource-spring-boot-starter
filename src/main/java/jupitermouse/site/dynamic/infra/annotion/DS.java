package jupitermouse.site.dynamic.infra.annotion;

import java.lang.annotation.*;

/**
 * <p>
 * 多数据源标志
 * </p>
 *
 * @author jupiter
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface DS {
    String name() default "";
}

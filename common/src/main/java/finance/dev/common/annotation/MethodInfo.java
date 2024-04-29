package finance.dev.common.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface MethodInfo {
    @AliasFor(annotation = Component.class)
    String value() default "";

    String name();

    String description();
}

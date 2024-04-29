package finance.dev.common.annotation;

import java.lang.annotation.*;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

@TypeInfo(name = "UseCase", description = "UseCase 어노테이션 정의 인터페이스")
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface UseCase {
    @AliasFor(annotation = Component.class)
    String value() default "";
}

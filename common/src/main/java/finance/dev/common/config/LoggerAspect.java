package finance.dev.common.config;

import finance.dev.common.annotation.MethodInfo;
import finance.dev.common.annotation.TypeInfo;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@TypeInfo(name = "LoggerAspect", description = "로그 출력 Aspect 클래스")
@Component
@Aspect
public class LoggerAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggerAspect.class);

    @MethodInfo(name = "logMethodInfo", description = "메소드 정보를 로그로 출력합니다.")
    @Before("@annotation(methodInfo)")
    public void logMethodInfo(JoinPoint joinPoint, MethodInfo methodInfo) {
        LOGGER.info(
                "'{}' 클래스의 '{}' 메소드가 실행됩니다. {}",
                joinPoint.getTarget().getClass().getName(),
                joinPoint.getSignature().getName(),
                methodInfo.description());
    }
}

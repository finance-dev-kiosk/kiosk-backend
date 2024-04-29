package finance.dev.common.config;

import finance.dev.common.annotation.TypeInfo;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@TypeInfo(name = "AppConfig", description = "애플리케이션 설정 클래스")
@Configuration
@EnableAspectJAutoProxy
class AppConfig {}

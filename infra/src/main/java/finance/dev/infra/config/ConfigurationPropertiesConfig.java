package finance.dev.infra.config;

import finance.dev.common.annotation.TypeInfo;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@TypeInfo(name = "ConfigurationPropertiesConfig", description = "프로퍼티 설정 클래스")
@EnableConfigurationProperties
@Configuration
public class ConfigurationPropertiesConfig {}

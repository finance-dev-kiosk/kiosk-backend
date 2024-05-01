package finance.dev.domain.config;

import finance.dev.common.annotation.TypeInfo;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@TypeInfo(name = "JpaConfig", description = "JPA 설정 클래스")
@Configuration
@EnableJpaRepositories(basePackages = "finance.dev.domain.repository.jpa")
@EnableJpaAuditing
public class JpaConfig {}

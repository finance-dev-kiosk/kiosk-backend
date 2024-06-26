package finance.dev.api;

import finance.dev.common.annotation.MethodInfo;
import finance.dev.common.annotation.TypeInfo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@TypeInfo(name = "ApiApplication", description = "API 모듈 애플리케이션 클래스")
@SpringBootApplication(
        scanBasePackages = {
            "finance.dev.api",
            "finance.dev.common",
            "finance.dev.domain",
            "finance.dev.infra",
        })
@EnableAsync
public class ApiApplication {

    @MethodInfo(name = "main", description = "API 모듈을 실행합니다.")
    public static void main(String[] args) {
        System.setProperty(
                "spring.config.name",
                "application-api, application-common, application-domain, application-infra");

        String activeProfile = System.getenv("SPRING_PROFILES_ACTIVE");

        SpringApplication springApplication = new SpringApplication(ApiApplication.class);
        springApplication.setAdditionalProfiles(activeProfile);

        springApplication.run(args);
    }
}

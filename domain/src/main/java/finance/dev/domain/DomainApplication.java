package finance.dev.domain;

import finance.dev.common.annotation.MethodInfo;
import finance.dev.common.annotation.TypeInfo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@TypeInfo(name = "DomainApplication", description = "Domain 모듈 애플리케이션 클래스")
@SpringBootApplication(
        scanBasePackages = {
            "finance.dev.common",
            "finance.dev.domain",
            "finance.dev.infra",
        })
public class DomainApplication {

    @MethodInfo(name = "main", description = "Domain 모듈을 실행합니다.")
    public static void main(String[] args) {
        System.setProperty(
                "spring.config.name", "application-common, application-domain, application-infra");

        String activeProfile = System.getenv("SPRING_PROFILES_ACTIVE");

        SpringApplication springApplication = new SpringApplication(DomainApplication.class);
        springApplication.setAdditionalProfiles(activeProfile);

        springApplication.run(args);
    }
}

package finance.dev.infra;

import finance.dev.common.annotation.MethodInfo;
import finance.dev.common.annotation.TypeInfo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@TypeInfo(name = "InfraApplication", description = "Infra 모듈 애플리케이션 클래스")
@SpringBootApplication(
        scanBasePackages = {
            "finance.dev.common",
            "finance.dev.infra",
        })
public class InfraApplication {
    @MethodInfo(name = "main", description = "Infra 모듈을 실행합니다.")
    public static void main(String[] args) {
        System.setProperty("spring.config.name", "application-common, application-infra");

        String activeProfile = System.getenv("SPRING_PROFILES_ACTIVE");

        SpringApplication springApplication = new SpringApplication(InfraApplication.class);
        springApplication.setAdditionalProfiles(activeProfile);

        springApplication.run(args);
    }
}

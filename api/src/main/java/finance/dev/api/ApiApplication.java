package finance.dev.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
        scanBasePackages = {
            "finance.dev.api",
            "finance.dev.common",
            "finance.dev.domain",
            "finance.dev.infra",
        })
public class ApiApplication {

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

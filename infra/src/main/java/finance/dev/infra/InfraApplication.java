package finance.dev.infra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
        scanBasePackages = {
            "finance.dev.common",
            "finance.dev.infra",
        })
public class InfraApplication {

    public static void main(String[] args) {
        System.setProperty("spring.config.name", "application-common, application-infra");

        String activeProfile = System.getenv("SPRING_PROFILES_ACTIVE");

        SpringApplication springApplication = new SpringApplication(InfraApplication.class);
        springApplication.setAdditionalProfiles(activeProfile);

        springApplication.run(args);
    }
}

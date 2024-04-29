package finance.dev.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
        scanBasePackages = {
            "finance.dev.common",
        })
public class CommonApplication {

    public static void main(String[] args) {
        System.setProperty("spring.config.name", "application-common");

        String activeProfile = System.getenv("SPRING_PROFILES_ACTIVE");

        SpringApplication springApplication = new SpringApplication(CommonApplication.class);
        springApplication.setAdditionalProfiles(activeProfile);

        springApplication.run(args);
    }
}

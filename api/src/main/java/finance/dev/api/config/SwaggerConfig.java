package finance.dev.api.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info =
        @Info(
                title = "디지털하나로 키오스크 API",
                version = "v1",
                description = "디지털하나로 키오스크에서 제공하는 API 목록입니다.",
                license =
                @io.swagger.v3.oas.annotations.info.License(
                        name = "Apache 2.0",
                        url =
                                "http://www.apache.org/licenses/LICENSE-2.0.html"
                )))
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi groupedOpenApi() {
        String[] paths = {"/**"};

        return GroupedOpenApi.builder().group("finance-dev-prj").pathsToMatch(paths).build();
    }
}

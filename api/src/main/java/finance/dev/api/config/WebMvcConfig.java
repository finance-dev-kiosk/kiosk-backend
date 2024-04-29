package finance.dev.api.config;

import finance.dev.common.annotation.MethodInfo;
import finance.dev.common.annotation.TypeInfo;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@TypeInfo(name = "WebMvcConfig", description = "웹 MVC 설정 클래스")
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @MethodInfo(name = "addResourceHandlers", description = "정적 리소스를 처리하는 핸들러를 추가합니다.")
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry resourceHandlerRegistry) {
        resourceHandlerRegistry
                .addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }
}

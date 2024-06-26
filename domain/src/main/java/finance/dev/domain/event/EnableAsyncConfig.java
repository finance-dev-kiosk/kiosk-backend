package finance.dev.domain.event;

import finance.dev.common.annotation.TypeInfo;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;

@TypeInfo(name = "EnableAsyncConfig", description = "Async 설정 클래스")
@EnableAsync
@Configuration
public class EnableAsyncConfig implements AsyncConfigurer {}

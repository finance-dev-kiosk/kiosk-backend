package finance.dev.infra;

import finance.dev.common.annotation.MethodInfo;
import finance.dev.common.annotation.TypeInfo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@TypeInfo(name = "InfraApplicationTests", description = "Infra 모듈 테스트 클래스")
@SpringBootTest
class InfraApplicationTests {

    @MethodInfo(name = "contextLoads", description = "Infra 모듈의 컨텍스트가 로드되는지 테스트합니다.")
    @Test
    void contextLoads() {}
}

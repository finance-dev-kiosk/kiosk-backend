package finance.dev.domain;

import finance.dev.common.annotation.MethodInfo;
import finance.dev.common.annotation.TypeInfo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@TypeInfo(name = "DomainApplicationTests", description = "Domain 모듈 테스트 클래스")
@SpringBootTest
class DomainApplicationTests {

    @MethodInfo(name = "contextLoads", description = "Domain 모듈의 컨텍스트를 로드합니다.")
    @Test
    void contextLoads() {}
}

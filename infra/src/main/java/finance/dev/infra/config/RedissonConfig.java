package finance.dev.infra.config;

import finance.dev.common.annotation.MethodInfo;
import finance.dev.common.annotation.TypeInfo;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@TypeInfo(name = "RedissonConfig", description = "Redisson 설정 클래스")
@Configuration
public class RedissonConfig {
    @Value("${spring.data.redis.host}")
    private String redisHost;

    @Value("${spring.data.redis.port}")
    private int redisPort;

    private static final String REDISSON_HOST_PREFIX = "redis://";

    @MethodInfo(
            name = "redissonClient",
            description = "Redis의 데이터 구조를 자바 어플리케이션에서 사용하기 위한 RedissonClient 빈을 등록합니다.")
    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();

        config.useSingleServer().setAddress(REDISSON_HOST_PREFIX + redisHost + ":" + redisPort);
        config.useSingleServer().setPassword(System.getenv("REDIS_PASSWORD"));

        return Redisson.create(config);
    }
}

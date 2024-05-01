package finance.dev.domain.repository.redis;

import finance.dev.common.annotation.TypeInfo;
import finance.dev.domain.entity.AuthMailEntity;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.repository.CrudRepository;

@TypeInfo(name = "AuthMailRepository", description = "인증 이메일 레포지토리 인터페이스")
@EnableRedisRepositories
public interface AuthMailRepository extends CrudRepository<AuthMailEntity, Long> {}

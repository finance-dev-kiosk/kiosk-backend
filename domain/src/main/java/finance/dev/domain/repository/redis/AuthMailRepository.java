package finance.dev.domain.repository.redis;

import finance.dev.common.annotation.TypeInfo;
import finance.dev.domain.entity.AuthMailEntity;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

@TypeInfo(name = "AuthMailRepository", description = "인증 이메일 레포지토리 인터페이스")
@EnableRedisRepositories
public interface AuthMailRepository extends CrudRepository<AuthMailEntity, Long> {
    AuthMailEntity findAuthMailByEmail(String email); // 이메일로 인증 이메일 정보 탐색

    AuthMailEntity findAuthMailCode(String value); // 인증 키로 인증 이메일 정보 탐색

    void deleteByEmail(String email); // 이메일로 인증 이메일 정보 삭제

    boolean existsAuthMailByEmail(String email); // 이메일이 존재하는지 확인
}

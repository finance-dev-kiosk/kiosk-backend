package finance.dev.domain.repository.jpa;

import finance.dev.common.annotation.TypeInfo;
import finance.dev.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@TypeInfo(name = "UserRepository", description = "회원 레포지토리 인터페이스")
@EnableJpaRepositories
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findById(String userId);


    UserEntity findByName(String name);
    UserEntity findByIdAndPassword(String userId, String password);
}

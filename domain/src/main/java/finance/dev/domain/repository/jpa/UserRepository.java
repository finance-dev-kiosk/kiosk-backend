package finance.dev.domain.repository.jpa;

import finance.dev.common.annotation.TypeInfo;
import finance.dev.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@TypeInfo(name = "UserRepository", description = "회원 레포지토리 인터페이스")
@EnableJpaRepositories
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByIdx(Long userIdx);

    UserEntity findById(String userId);

    UserEntity findByEmail(String email);

    UserEntity findByName(String name);

    UserEntity findByIdAndPassword(String userId, String password);

    UserEntity save(UserEntity userEntity);

    default void update(Long idx, String name) {
        UserEntity user = findByIdx(idx);
        save(
                UserEntity.builder()
                        .idx(user.getIdx())
                        .id(user.getId())
                        .password(user.getPassword())
                        .name(name)
                        .email(user.getEmail())
                        .build()
        );
    }
}

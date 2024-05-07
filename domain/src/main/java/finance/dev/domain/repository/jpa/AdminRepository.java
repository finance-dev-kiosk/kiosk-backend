package finance.dev.domain.repository.jpa;

import finance.dev.common.annotation.TypeInfo;
import finance.dev.domain.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Optional;

@TypeInfo(name = "AdminRepository", description = "관리자 레포지토리 인터페이스")
@EnableJpaRepositories
public interface AdminRepository extends JpaRepository<AdminEntity, Long> {
    AdminEntity findById(String adminId);

    Boolean existsById(String adminId);

}

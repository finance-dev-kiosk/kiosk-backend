package finance.dev.domain.repository.jpa;

import finance.dev.common.annotation.TypeInfo;
import finance.dev.domain.entity.StoreEntity;
import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@TypeInfo(name = "StoreRepository", description = "가게 레포지토리 인터페이스")
@EnableJpaRepositories
public interface StoreRepository extends JpaRepository<StoreEntity, Long> {
    ArrayList<StoreEntity> findAll();
}

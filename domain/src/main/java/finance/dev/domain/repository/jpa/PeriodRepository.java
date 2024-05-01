package finance.dev.domain.repository.jpa;

import finance.dev.common.annotation.TypeInfo;
import finance.dev.domain.entity.PeriodEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@TypeInfo(name = "PeriodRepository", description = "운영 시간 레포지토리 인터페이스")
@EnableJpaRepositories
public interface PeriodRepository extends JpaRepository<PeriodEntity, Long> {}

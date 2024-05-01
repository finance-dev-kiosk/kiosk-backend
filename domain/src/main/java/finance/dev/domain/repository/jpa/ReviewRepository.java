package finance.dev.domain.repository.jpa;

import finance.dev.common.annotation.TypeInfo;
import finance.dev.domain.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@TypeInfo(name = "ReviewRepository", description = "리뷰 레포지토리 인터페이스")
@EnableJpaRepositories
public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {}

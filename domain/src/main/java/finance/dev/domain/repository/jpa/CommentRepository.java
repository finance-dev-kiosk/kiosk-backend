package finance.dev.domain.repository.jpa;

import finance.dev.common.annotation.TypeInfo;
import finance.dev.domain.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@TypeInfo(name = "CommentRepository", description = "댓글 레포지토리 인터페이스")
@EnableJpaRepositories
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {}

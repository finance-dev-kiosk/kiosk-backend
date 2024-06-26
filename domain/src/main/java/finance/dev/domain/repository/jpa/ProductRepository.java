package finance.dev.domain.repository.jpa;

import finance.dev.common.annotation.TypeInfo;
import finance.dev.domain.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@TypeInfo(name = "ProductRepository", description = "상품 레포지토리 인터페이스")
@EnableJpaRepositories
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    ProductEntity findByIdx(Long productIdx);
}

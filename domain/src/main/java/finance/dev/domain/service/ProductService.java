package finance.dev.domain.service;

import finance.dev.common.annotation.TypeInfo;
import finance.dev.domain.entity.ProductEntity;
import jakarta.persistence.EntityManager;
import java.util.List;
import org.springframework.stereotype.Service;

@TypeInfo(name = "ProductService", description = "상품 서비스 클래스")
@Service
public class ProductService {
    private final EntityManager entityManager;

    public List<ProductEntity> getStoreProducts(Long storeId) {
        StringBuilder queryString =
                new StringBuilder("SELECT p FROM ProductEntity p WHERE p.storeIdx = ");
        queryString.append(storeId);

        return entityManager
                .createQuery(queryString.toString(), ProductEntity.class)
                .getResultList();
    }

    public ProductService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}

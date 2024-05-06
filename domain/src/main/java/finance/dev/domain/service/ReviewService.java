package finance.dev.domain.service;

import finance.dev.common.annotation.TypeInfo;
import finance.dev.domain.entity.ReviewEntity;
import jakarta.persistence.EntityManager;
import java.util.List;
import org.springframework.stereotype.Service;

@TypeInfo(name = "ReviewService", description = "리뷰 서비스 클래스")
@Service
public class ReviewService {
    private final EntityManager entityManager;

    public List<ReviewEntity> getStoreReviews(Long storeId) {
        StringBuilder queryString =
                new StringBuilder("SELECT r FROM ReviewEntity r WHERE r.storeIdx = ");
        queryString.append(storeId);

        return entityManager
                .createQuery(queryString.toString(), ReviewEntity.class)
                .getResultList();
    }

    public ReviewService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}

package finance.dev.domain.service;

import finance.dev.common.annotation.TypeInfo;
import finance.dev.domain.repository.jpa.ReviewRepository;
import org.springframework.stereotype.Service;

@TypeInfo(name = "ReviewService", description = "리뷰 서비스 클래스")
@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }
}

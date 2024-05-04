package finance.dev.api.usecase.user;

import finance.dev.api.dto.user.UserStoreReviewsPostRequest;
import finance.dev.api.dto.user.UserStoreReviewsPostResponse;
import finance.dev.common.annotation.MethodInfo;
import finance.dev.common.annotation.TypeInfo;
import finance.dev.common.annotation.UseCase;
import finance.dev.domain.service.ReviewService;
import finance.dev.domain.service.UserService;
import org.springframework.http.ResponseEntity;

@UseCase
@TypeInfo(name = "UserStoreReviewsUseCase", description = "사용자 가게 리뷰목록 조회 유스케이스 클래스")
public class UserStoreReviewsUseCase {
    private final UserService userService;
    private final ReviewService reviewService;

    @MethodInfo(name = "execute", description = "가게에 대한 리뷰목록을 조회합니다.")
    public ResponseEntity<UserStoreReviewsPostResponse> execute(
            UserStoreReviewsPostRequest userStoreReviewsPostRequest, Long storeId)
            throws Exception {
        return null;
    }

    public UserStoreReviewsUseCase(UserService userService, ReviewService reviewService) {
        this.userService = userService;
        this.reviewService = reviewService;
    }
}

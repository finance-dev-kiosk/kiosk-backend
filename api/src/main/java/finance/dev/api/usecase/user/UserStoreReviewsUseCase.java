package finance.dev.api.usecase.user;

import finance.dev.api.dto.user.UserReviewPost;
import finance.dev.api.dto.user.UserStoreReviewsPostRequest;
import finance.dev.api.dto.user.UserStoreReviewsPostResponse;
import finance.dev.common.annotation.MethodInfo;
import finance.dev.common.annotation.TypeInfo;
import finance.dev.common.annotation.UseCase;
import finance.dev.domain.entity.ReviewEntity;
import finance.dev.domain.handler.JwtHandler;
import finance.dev.domain.service.ReviewService;
import finance.dev.domain.service.UserService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;

@UseCase
@TypeInfo(name = "UserStoreReviewsUseCase", description = "사용자 가게 리뷰목록 조회 유스케이스 클래스")
public class UserStoreReviewsUseCase {
    private final UserService userService;
    private final ReviewService reviewService;
    private final JwtHandler jwtHandler;

    @MethodInfo(name = "execute", description = "가게에 대한 리뷰목록을 조회합니다.")
    public ResponseEntity<UserStoreReviewsPostResponse> execute(
            UserStoreReviewsPostRequest userStoreReviewsPostRequest, Long storeId)
            throws Exception {
        try {
            // 토큰 파싱
            String userId =
                    jwtHandler.parseAccessToken(userStoreReviewsPostRequest.getAccessToken());

            // 아이디 존재 유효성 검사
            if (!userService.isExistId(userId)) {
                throw new BadRequestException("존재하지 않는 아이디입니다.");
            }

            // 가게 리뷰목록 조회
            List<ReviewEntity> reviewEntities = reviewService.getStoreReviews(storeId);

            // 가게 리뷰목록이 없을 경우
            if (reviewEntities.isEmpty()) {
                throw new BadRequestException("가게 리뷰목록이 없습니다.");
            }

            // 가게 리뷰응답
            return ResponseEntity.ok(
                    UserStoreReviewsPostResponse.builder()
                            .reviewCount(reviewEntities.size())
                            .pageCount(1)
                            .reviews(
                                    reviewEntities.stream()
                                            .map(
                                                    reviewEntity ->
                                                            UserReviewPost.builder()
                                                                    .idx(reviewEntity.getIdx())
                                                                    .name(reviewEntity.getTitle())
                                                                    .content(
                                                                            reviewEntity
                                                                                    .getContent())
                                                                    .date(reviewEntity.getDate())
                                                                    .image(reviewEntity.getImage())
                                                                    .blogName(
                                                                            reviewEntity
                                                                                    .getBlogName())
                                                                    .url(reviewEntity.getUrl())
                                                                    .build())
                                            .collect(Collectors.toCollection(ArrayList::new)))
                            .build());
        } catch (BadRequestException e) {
            throw new BadRequestException(e.getMessage());
        } catch (IllegalStateException e) {
            throw new IllegalStateException(e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public UserStoreReviewsUseCase(
            UserService userService, ReviewService reviewService, JwtHandler jwtHandler) {
        this.userService = userService;
        this.reviewService = reviewService;
        this.jwtHandler = jwtHandler;
    }
}

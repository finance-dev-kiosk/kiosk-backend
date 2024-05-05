package finance.dev.api.dto.user;

import java.util.ArrayList;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserStoreReviewsPostResponse {
    private int reviewCount; // 리뷰 수
    private int pageCount; // 페이지 수
    private ArrayList<UserReviewPost> reviews; // 리뷰 목록

    @Builder
    public UserStoreReviewsPostResponse(
            int reviewCount, int pageCount, ArrayList<UserReviewPost> reviews) {
        this.reviewCount = reviewCount;
        this.pageCount = pageCount;
        this.reviews = reviews;
    }
}

class UserReviewPost {
    public UserReviewPost(
            Long idx,
            String name,
            String content,
            String date,
            String image,
            String blogName,
            String url) {}
}

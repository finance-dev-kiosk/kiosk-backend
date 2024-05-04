package finance.dev.api.dto.user;

import lombok.Getter;

@Getter
public class UserStoreReviewsPostRequest {
    private String accessToken;

    public UserStoreReviewsPostRequest(String accessToken) {
        this.accessToken = accessToken;
    }
}

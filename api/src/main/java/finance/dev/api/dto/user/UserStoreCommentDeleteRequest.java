package finance.dev.api.dto.user;

import lombok.Getter;

@Getter
public class UserStoreCommentDeleteRequest {
    private String accessToken;

    public UserStoreCommentDeleteRequest(String accessToken) {
        this.accessToken = accessToken;
    }
}

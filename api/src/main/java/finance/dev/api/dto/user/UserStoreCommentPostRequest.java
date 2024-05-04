package finance.dev.api.dto.user;

import lombok.Getter;

@Getter
public class UserStoreCommentPostRequest {
    private String accessToken;
    private String comment;

    public UserStoreCommentPostRequest(String accessToken, String comment) {
        this.accessToken = accessToken;
        this.comment = comment;
    }
}

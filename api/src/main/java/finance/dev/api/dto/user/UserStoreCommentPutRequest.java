package finance.dev.api.dto.user;

import lombok.Getter;

@Getter
public class UserStoreCommentPutRequest {
    private String accessToken;
    private String comment;

    public UserStoreCommentPutRequest(String accessToken, String comment) {
        this.accessToken = accessToken;
        this.comment = comment;
    }
}

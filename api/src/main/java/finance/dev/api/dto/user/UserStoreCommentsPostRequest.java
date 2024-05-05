package finance.dev.api.dto.user;

import lombok.Getter;

@Getter
public class UserStoreCommentsPostRequest {
    private String accessToken;

    public UserStoreCommentsPostRequest(String accessToken) {
        this.accessToken = accessToken;
    }
}

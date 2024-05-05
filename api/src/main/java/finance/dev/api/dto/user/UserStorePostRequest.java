package finance.dev.api.dto.user;

import lombok.Getter;

@Getter
public class UserStorePostRequest {
    private String accessToken; // 액세스 토큰

    public UserStorePostRequest(String accessToken) {
        this.accessToken = accessToken;
    }
}

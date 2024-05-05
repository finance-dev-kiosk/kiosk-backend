package finance.dev.api.dto.user;

import lombok.Getter;

@Getter
public class UserStoreProductsPostRequest {
    private String accessToken; // 액세스 토큰

    public UserStoreProductsPostRequest(String accessToken) {
        this.accessToken = accessToken;
    }
}

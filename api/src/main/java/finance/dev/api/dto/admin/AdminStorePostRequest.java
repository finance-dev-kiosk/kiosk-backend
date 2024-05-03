package finance.dev.api.dto.admin;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AdminStorePostRequest {
    private String accessToken; // 액세스 토큰

    @Builder
    public AdminStorePostRequest(String accessToken) {
        this.accessToken = accessToken;
    }
}

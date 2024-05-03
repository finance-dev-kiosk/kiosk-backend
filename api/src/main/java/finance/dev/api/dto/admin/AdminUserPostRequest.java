package finance.dev.api.dto.admin;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AdminUserPostRequest {
    private String accessToken; // 액세스 토큰

    @Builder
    public AdminUserPostRequest(String accessToken) {
        this.accessToken = accessToken;
    }
}

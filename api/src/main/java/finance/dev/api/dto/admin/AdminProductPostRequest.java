package finance.dev.api.dto.admin;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AdminProductPostRequest {
    private String accessToken; // 액세스 토큰

    @Builder
    public AdminProductPostRequest(String accessToken) {
        this.accessToken = accessToken;
    }
}

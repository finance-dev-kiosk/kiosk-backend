package finance.dev.api.dto.admin;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AdminRefreshTokenPostResponse {
    private String accessToken; // 엑세스 토큰

    @Builder
    public AdminRefreshTokenPostResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}

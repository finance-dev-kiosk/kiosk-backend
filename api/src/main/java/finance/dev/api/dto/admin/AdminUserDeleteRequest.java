package finance.dev.api.dto.admin;

import lombok.Getter;

@Getter
public class AdminUserDeleteRequest {
    private String accessToken; // 액세스 토큰

    public AdminUserDeleteRequest(String accessToken) {
        this.accessToken = accessToken;
    }
}

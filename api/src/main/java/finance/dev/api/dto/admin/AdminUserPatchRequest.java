package finance.dev.api.dto.admin;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AdminUserPatchRequest {
    private String accessToken; // 액세스 토큰

    private String name; // 유저 닉네임

    @Builder
    public AdminUserPatchRequest(String accessToken, String name) {
        this.accessToken = accessToken;
        this.name = name;
    }
}

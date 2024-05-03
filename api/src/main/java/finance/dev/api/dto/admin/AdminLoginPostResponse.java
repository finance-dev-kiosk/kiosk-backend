package finance.dev.api.dto.admin;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AdminLoginPostResponse {
    private String accessToken;

    @Builder
    public AdminLoginPostResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}

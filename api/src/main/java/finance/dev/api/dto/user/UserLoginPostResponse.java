package finance.dev.api.dto.user;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserLoginPostResponse {
    private String accessToken;

    @Builder
    public UserLoginPostResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}

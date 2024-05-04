package finance.dev.api.dto.user;

import lombok.Getter;

@Getter
public class UserLoginPostRequest {
    private String userId;
    private String password;

    public UserLoginPostRequest(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }
}

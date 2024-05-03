package finance.dev.api.dto.admin;

import lombok.Getter;

@Getter
public class AdminLoginPostRequest {
    private String userId;
    private String password;

    public AdminLoginPostRequest(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }
}

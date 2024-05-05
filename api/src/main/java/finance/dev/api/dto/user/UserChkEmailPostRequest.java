package finance.dev.api.dto.user;

import lombok.Getter;

@Getter
public class UserChkEmailPostRequest {
    private String email;

    public UserChkEmailPostRequest(String email) {
        this.email = email;
    }
}

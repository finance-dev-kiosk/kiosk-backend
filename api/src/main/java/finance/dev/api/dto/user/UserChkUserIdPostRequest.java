package finance.dev.api.dto.user;

import lombok.Getter;

@Getter
public class UserChkUserIdPostRequest {
    private String id;

    public UserChkUserIdPostRequest(String id) {
        this.id = id;
    }
}

package finance.dev.api.dto.user;

import lombok.Getter;

@Getter
public class UserChkUserNamePostRequest {
    private String name;

    public UserChkUserNamePostRequest(String name) {
        this.name = name;
    }
}

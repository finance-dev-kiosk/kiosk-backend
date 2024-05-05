package finance.dev.api.dto.user;

import lombok.Getter;

@Getter
public class UserRegisterPostRequest {
    private String id;
    private String password;
    private String passwordCheck;
    private String name;
    private String email;

    public UserRegisterPostRequest(
            String id, String password, String passwordCheck, String name, String email) {
        this.id = id;
        this.password = password;
        this.passwordCheck = passwordCheck;
        this.name = name;
        this.email = email;
    }
}

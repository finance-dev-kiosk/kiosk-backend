package finance.dev.api.usecase.user;

import finance.dev.common.annotation.MethodInfo;
import finance.dev.common.annotation.TypeInfo;
import finance.dev.common.annotation.UseCase;
import finance.dev.domain.service.UserService;
import org.springframework.http.ResponseEntity;

@UseCase
@TypeInfo(name = "UserChkEmailAuthUseCase", description = "이메일 인증 여부 확인 유스케이스 클래스")
public class UserChkEmailAuthUseCase {
    private final UserService userService;

    @MethodInfo(name = "execute", description = "이메일 인증 여부를 확인합니다.")
    public ResponseEntity<Void> execute(String email, String authCode) throws Exception {
        return null;
    }

    public UserChkEmailAuthUseCase(UserService userService) {
        this.userService = userService;
    }
}

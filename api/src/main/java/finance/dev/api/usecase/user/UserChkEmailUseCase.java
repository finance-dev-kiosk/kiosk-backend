package finance.dev.api.usecase.user;

import finance.dev.api.dto.user.UserChkEmailPostRequest;
import finance.dev.common.annotation.MethodInfo;
import finance.dev.common.annotation.TypeInfo;
import finance.dev.common.annotation.UseCase;
import finance.dev.domain.service.UserService;
import org.springframework.http.ResponseEntity;

@UseCase
@TypeInfo(name = "UserChkEmailUseCase", description = "이메일 인증 유스케이스 클래스")
public class UserChkEmailUseCase {
    private final UserService userService;

    @MethodInfo(name = "execute", description = "이메일 중복을 확인합니다.")
    public ResponseEntity<Void> execute(UserChkEmailPostRequest userChkEmailPostRequest)
            throws Exception {
        return null;
    }

    public UserChkEmailUseCase(UserService userService) {
        this.userService = userService;
    }
}

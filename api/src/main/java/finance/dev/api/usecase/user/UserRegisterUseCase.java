package finance.dev.api.usecase.user;

import finance.dev.api.dto.user.UserRegisterPostRequest;
import finance.dev.common.annotation.MethodInfo;
import finance.dev.common.annotation.TypeInfo;
import finance.dev.common.annotation.UseCase;
import finance.dev.domain.service.AuthMailService;
import finance.dev.domain.service.UserService;
import java.util.Objects;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;

@UseCase
@TypeInfo(name = "UserRegisterUseCase", description = "사용자 회원가입 유스케이스 클래스")
public class UserRegisterUseCase {
    private final UserService userService;
    private final AuthMailService authMailService;

    @MethodInfo(name = "execute", description = "사용자 회원가입을 처리합니다.")
    public ResponseEntity<Void> execute(UserRegisterPostRequest userRegisterPostRequest)
            throws Exception {
        return null;
    }

    public UserRegisterUseCase(UserService userService, AuthMailService authMailService) {
        this.userService = userService;
        this.authMailService = authMailService;
    }
}

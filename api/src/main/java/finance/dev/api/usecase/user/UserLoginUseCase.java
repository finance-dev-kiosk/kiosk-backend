package finance.dev.api.usecase.user;

import finance.dev.api.dto.user.UserLoginPostRequest;
import finance.dev.api.dto.user.UserLoginPostResponse;
import finance.dev.common.annotation.MethodInfo;
import finance.dev.common.annotation.TypeInfo;
import finance.dev.common.annotation.UseCase;
import finance.dev.domain.handler.JwtHandler;
import finance.dev.domain.service.UserService;
import java.time.Duration;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;

@UseCase
@TypeInfo(name = "UserLoginUseCase", description = "사용자 로그인 유스케이스 클래스")
public class UserLoginUseCase {
    private final UserService userService;
    private final JwtHandler jwtHandler;

    @MethodInfo(name = "execute", description = "사용자 로그인을 처리합니다.")
    public ResponseEntity<UserLoginPostResponse> execute(UserLoginPostRequest userLoginPostRequest)
            throws Exception {
        return null;
    }

    public UserLoginUseCase(UserService userService, JwtHandler jwtHandler) {
        this.userService = userService;
        this.jwtHandler = jwtHandler;
    }
}

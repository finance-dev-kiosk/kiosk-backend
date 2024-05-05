package finance.dev.api.usecase.user;

import finance.dev.api.dto.user.UserChkUserIdPostRequest;
import finance.dev.common.annotation.MethodInfo;
import finance.dev.common.annotation.TypeInfo;
import finance.dev.common.annotation.UseCase;
import finance.dev.domain.service.UserService;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;

@UseCase
@TypeInfo(name = "UserChkUserIdUseCase", description = "사용자 아이디 중복 확인 유스케이스 클래스")
public class UserChkUserIdUseCase {
    private final UserService userService;

    @MethodInfo(name = "execute", description = "사용자 아이디 중복 확인을 처리합니다.")
    public ResponseEntity<Void> execute(UserChkUserIdPostRequest userChkUserIdPostRequest)
            throws Exception {
        return null;
    }

    public UserChkUserIdUseCase(UserService userService) {
        this.userService = userService;
    }
}

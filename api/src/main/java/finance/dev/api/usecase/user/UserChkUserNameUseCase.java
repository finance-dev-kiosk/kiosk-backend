package finance.dev.api.usecase.user;

import finance.dev.api.dto.user.UserChkUserNamePostRequest;
import finance.dev.common.annotation.MethodInfo;
import finance.dev.common.annotation.TypeInfo;
import finance.dev.common.annotation.UseCase;
import finance.dev.domain.service.UserService;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;

@UseCase
@TypeInfo(name = "UserChkUserNameUseCase", description = "사용자 닉네임 중복 확인 유스케이스 클래스")
public class UserChkUserNameUseCase {
    private final UserService userService;

    @MethodInfo(name = "execute", description = "사용자 닉네임 중복 확인을 처리합니다.")
    public ResponseEntity<Void> execute(UserChkUserNamePostRequest userChkUserNamePostRequest)
            throws Exception {
        return null;
    }

    public UserChkUserNameUseCase(UserService userService) {
        this.userService = userService;
    }
}

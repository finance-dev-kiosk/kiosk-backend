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
        try {
            // 유효성 검사. 아이디에 입력되지 않은 값이 있는 경우
            if (userChkUserIdPostRequest.getId() == null) {
                throw new BadRequestException("아이디를 입력해주세요.");
            }
            // 아이디 중복 확인
            if (userService.isExistId(userChkUserIdPostRequest.getId())) {
                throw new BadRequestException("이미 사용 중인 아이디입니다.");
            }
            // 반환
            return ResponseEntity.ok().build();
        } catch (BadRequestException e) {
            throw new BadRequestException(e.getMessage());
        } catch (Exception e) {
            throw new Exception("아이디 중복 확인 중 오류가 발생했습니다.");
        }
    }

    public UserChkUserIdUseCase(UserService userService) {
        this.userService = userService;
    }
}

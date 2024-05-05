package finance.dev.api.usecase.user;

import finance.dev.common.annotation.MethodInfo;
import finance.dev.common.annotation.TypeInfo;
import finance.dev.common.annotation.UseCase;
import finance.dev.domain.entity.AuthMailEntity;
import finance.dev.domain.service.AuthMailService;
import finance.dev.domain.service.UserService;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;

@UseCase
@TypeInfo(name = "UserChkEmailAuthUseCase", description = "이메일 인증 여부 확인 유스케이스 클래스")
public class UserChkEmailAuthUseCase {
    private final UserService userService;
    private final AuthMailService authMailService;

    @MethodInfo(name = "execute", description = "이메일 인증을 확인합니다.")
    public ResponseEntity<Void> execute(String email, String authCode) throws Exception {
        try {
            if (userService.isExistEmail(email)) {
                throw new BadRequestException("이미 가입된 이메일입니다.");
            }
            if (!authMailService.existsAuthMailByEmail(email)) {
                throw new BadRequestException("인증 메일 정보가 존재하지 않습니다.");
            }
            if (!authMailService.findAuthMailByEmail(email).getCode().equals(authCode)) {
                throw new BadRequestException("인증 코드가 일치하지 않습니다.");
            }

            authMailService.update(
                    authMailService.findAuthMailByEmail(email),
                    AuthMailEntity.builder()
                            .email(email)
                            .code(authCode)
                            .isVerified(true)
                            .build()
            );

            return ResponseEntity.ok().build();
        } catch (BadRequestException e) {
            throw new BadRequestException(e.getMessage());
        } catch (Exception e) {
            throw new Exception("이메일 인증 확인 중 오류가 발생했습니다.");
        }
    }

    public UserChkEmailAuthUseCase(UserService userService, AuthMailService authMailService) {
        this.userService = userService;
        this.authMailService = authMailService;
    }
}

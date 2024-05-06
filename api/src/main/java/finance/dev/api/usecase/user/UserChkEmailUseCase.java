package finance.dev.api.usecase.user;

import finance.dev.api.dto.user.UserChkEmailPostRequest;
import finance.dev.common.annotation.MethodInfo;
import finance.dev.common.annotation.TypeInfo;
import finance.dev.common.annotation.UseCase;
import finance.dev.domain.config.EmailUtils;
import finance.dev.domain.entity.AuthMailEntity;
import finance.dev.domain.service.AuthMailService;
import finance.dev.domain.service.UserService;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;

@UseCase
@TypeInfo(name = "UserChkEmailUseCase", description = "이메일 인증 유스케이스 클래스")
public class UserChkEmailUseCase {
    private final UserService userService;
    private final AuthMailService authMailService;
    private final JavaMailSender javaMailSender;

    @MethodInfo(name = "execute", description = "이메일 인증을 실행합니다.")
    public ResponseEntity<Void> execute(UserChkEmailPostRequest userChkEmailPostRequest)
            throws Exception {
        try {
            if (userService.isExistEmail(userChkEmailPostRequest.getEmail())) {
                throw new BadRequestException("이미 가입된 이메일입니다.");
            }

            if (authMailService.existsAuthMailByEmail(userChkEmailPostRequest.getEmail())) {
                throw new BadRequestException("해당 이메일로 이미 인증 메일이 발송되었습니다.");
            }

            String authKey = createAuthKey();
            saveAuthkey(userChkEmailPostRequest.getEmail(), authKey);
            EmailUtils emailUtils = new EmailUtils(javaMailSender);
            emailUtils.setSubject("[인증 이메일]");
            emailUtils.setText(
                    "<h1>[이메일 인증]</h1><br>"
                            + "<p>아래 링크에서 이메일 인증을 완료해주세요.</p><br>"
                            + "<a href='"
                            + System.getenv("SERVER_URL")
                            + ":"
                            + System.getenv("SERVER_PORT")
                            + "/user/auth?email="
                            + userChkEmailPostRequest.getEmail()
                            + "&key="
                            + authKey
                            + "' target='_blank'>이메일 인증 확인</a>");
            emailUtils.setTo(userChkEmailPostRequest.getEmail());
            emailUtils.setFrom();
            emailUtils.send();

            return ResponseEntity.ok().build();
        } catch (BadRequestException e) {
            throw new BadRequestException(e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @MethodInfo(name = "createAuthKey", description = "인증 키를 생성합니다.")
    private String createAuthKey() throws BadRequestException {
        try {
            int num = 0;

            Random random = SecureRandom.getInstanceStrong();

            StringBuilder stringBuilder = new StringBuilder();

            do {
                num = random.nextInt(75) + 48;

                if ((num >= 48 && num <= 57)
                        || (num >= 65 && num <= 90)
                        || (num >= 97 && num <= 122)) {
                    stringBuilder.append((char) num);
                } else {
                    continue;
                }
            } while (stringBuilder.length() < 64);

            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new BadRequestException("인증 키 생성에 실패했습니다.");
        }
    }

    @MethodInfo(name = "saveAuthkey", description = "인증 키를 저장합니다.")
    private void saveAuthkey(String email, String authKey) {
        authMailService.save(
                AuthMailEntity.builder()
                        .email(email)
                        .code(authKey)
                        .isVerified(false)
                        .ttl(300L)
                        .build());
    }

    public UserChkEmailUseCase(
            UserService userService,
            AuthMailService authMailService,
            JavaMailSender javaMailSender) {
        this.userService = userService;
        this.authMailService = authMailService;
        this.javaMailSender = javaMailSender;
    }
}

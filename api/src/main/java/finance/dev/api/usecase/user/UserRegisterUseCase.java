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
        try {
            // 유효성 검사 1. 아이디, 비밀번호, 비밀번호 확인, 이메일, 닉네임 중 입력되지 않은 값이 있는 경우
            String errorMessage1 = "입력되지 않은 값이 있습니다.\n";
            if (userRegisterPostRequest.getId() == null) {
                errorMessage1 += "• 아이디\n";
            }
            if (userRegisterPostRequest.getPassword() == null) {
                errorMessage1 += "• 비밀번호\n";
            }
            if (userRegisterPostRequest.getPasswordCheck() == null) {
                errorMessage1 += "• 비밀번호 확인\n";
            }
            if (userRegisterPostRequest.getName() == null) {
                errorMessage1 += "• 닉네임\n";
            }
            if (userRegisterPostRequest.getEmail() == null) {
                errorMessage1 += "• 이메일\n";
            }
            if (!errorMessage1.equals("입력되지 않은 값이 있습니다.\n")) {
                throw new BadRequestException(errorMessage1);
            }

            // 유효성 검사 2. 아이디 유효성 검사
            String errorMessage2 = "아이디 유효성 검사에 실패했습니다.\n";
            if (userRegisterPostRequest.getId().length() < 4
                    || userRegisterPostRequest.getId().length() > 32) {
                errorMessage2 += "• 아이디는 4자 이상 32자 이하로 입력해주세요.\n";
            }
            if (userRegisterPostRequest.getId().contains(" ")) {
                errorMessage2 += "• 아이디에 공백을 포함할 수 없습니다.\n";
            }
            if (!userRegisterPostRequest.getId().matches("^[a-zA-Z0-9]*$")) {
                errorMessage2 += "• 아이디는 영문 대소문자와 숫자로만 입력해주세요.\n";
            }
            if (userRegisterPostRequest.getId().matches(".*([a-zA-Z])\\1{3,}.*")) {
                errorMessage2 += "• 아이디에 4회 이상 반복되는 문자를 사용할 수 없습니다.\n";
            }
            if (userRegisterPostRequest.getId().matches(".*([a-zA-Z0-9])\\1{3,}.*")) {
                errorMessage2 += "• 아이디에 4회 이상 연속되는 문자, 숫자를 사용할 수 없습니다.\n";
            }
            if (userRegisterPostRequest.getId().contains("admin")) {
                errorMessage2 += "• 아이디에 'admin'은 포함될 수 없습니다.\n";
            }
            if (userService.isExistId(userRegisterPostRequest.getId())) {
                errorMessage2 += "• 이미 존재하는 아이디입니다.\n";
            }
            if (!errorMessage2.equals("아이디 유효성 검사에 실패했습니다.\n")) {
                throw new BadRequestException(errorMessage2);
            }

            // 유효성 검사 3. 비밀번호 유효성 검사
            String errorMessage3 = "비밀번호 유효성 검사에 실패했습니다.\n";
            if (userRegisterPostRequest.getPassword().length() < 8
                    || userRegisterPostRequest.getPassword().length() > 64) {
                errorMessage3 += "• 비밀번호는 8자 이상 64자 이하로 입력해주세요.\n";
            }
            if (userRegisterPostRequest.getPassword().contains(" ")) {
                errorMessage3 += "• 비밀번호에 공백을 포함할 수 없습니다.\n";
            }
            if (!userRegisterPostRequest
                    .getPassword()
                    .matches("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[!@#$%^&+=]).*$")) {
                errorMessage3 += "• 비밀번호는 영문 대소문자, 숫자, 특수문자 (!@#$%^&+=)를 모두 포함해야 합니다.\n";
            }
            if (userRegisterPostRequest.getPassword().matches(".*([a-zA-Z])\\1{3,}.*")) {
                errorMessage3 += "• 비밀번호에 4회 이상 반복되는 문자를 사용할 수 없습니다.\n";
            }
            if (userRegisterPostRequest.getPassword().matches(".*([a-zA-Z0-9@#$%^&+=])\\1{3,}.*")) {
                errorMessage3 += "• 비밀번호에 4회 이상 연속되는 문자, 숫자, 특수문자를 사용할 수 없습니다.\n";
            }
            if (userRegisterPostRequest.getPassword().contains(userRegisterPostRequest.getId())) {
                errorMessage3 += "• 비밀번호에 아이디를 포함할 수 없습니다.\n";
            }
            if (userRegisterPostRequest.getPassword().contains(userRegisterPostRequest.getName())) {
                errorMessage3 += "• 비밀번호에 닉네임을 포함할 수 없습니다.\n";
            }
            if (userRegisterPostRequest
                    .getPassword()
                    .contains(userRegisterPostRequest.getEmail())) {
                errorMessage3 += "• 비밀번호에 이메일을 포함할 수 없습니다.\n";
            }
            if (!Objects.equals(
                    userRegisterPostRequest.getPassword(),
                    userRegisterPostRequest.getPasswordCheck())) {
                errorMessage3 += "• 비밀번호와 비밀번호 확인이 일치하지 않습니다.\n";
            }
            if (!errorMessage3.equals("비밀번호 유효성 검사에 실패했습니다.\n")) {
                throw new BadRequestException(errorMessage3);
            }

            // 유효성 검사 4. 이메일 유효성 검사
            String errorMessage4 = "이메일 유효성 검사에 실패했습니다.\n";
            if (!userRegisterPostRequest
                    .getEmail()
                    .matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) {
                errorMessage4 += "• 이메일 형식이 올바르지 않습니다.\n";
            }
            if (userService.isExistEmail(userRegisterPostRequest.getEmail())) {
                errorMessage4 += "• 이미 존재하는 이메일입니다.\n";
            }
            if (!authMailService.existsAuthMailByEmail(userRegisterPostRequest.getEmail())) {
                errorMessage4 += "• 해당 이메일로 인증 메일이 발송되지 않았습니다.\n";
            }
            else if (authMailService.findAuthMailByEmail(userRegisterPostRequest.getEmail()).getIsVerified() == Boolean.FALSE) {
                errorMessage4 += "• 해당 이메일로 인증이 완료되지 않았습니다.\n";
            }
            if (!errorMessage4.equals("이메일 유효성 검사에 실패했습니다.\n")) {
                throw new BadRequestException(errorMessage4);
            }

            // 유효성 검사 5. 닉네임 유효성 검사
            String errorMessage5 = "닉네임 유효성 검사에 실패했습니다.\n";
            if (userRegisterPostRequest.getName().length() < 2
                    || userRegisterPostRequest.getName().length() > 8) {
                errorMessage5 += "• 닉네임은 2자 이상 8자 이하로 입력해주세요.\n";
            }
            if (userRegisterPostRequest.getName().contains(" ")) {
                errorMessage5 += "• 닉네임에 공백을 포함할 수 없습니다.\n";
            }
            if (userService.isExistName(userRegisterPostRequest.getName())) {
                errorMessage5 += "• 이미 존재하는 닉네임입니다.\n";
            }
            if (!errorMessage5.equals("닉네임 유효성 검사에 실패했습니다.\n")) {
                throw new BadRequestException(errorMessage5);
            }

            // 회원가입 처리
            userService.register(
                    userRegisterPostRequest.getId(),
                    userRegisterPostRequest.getPassword(),
                    userRegisterPostRequest.getName(),
                    userRegisterPostRequest.getEmail());

            // 인증 메일 삭제
            authMailService.deleteByEmail(userRegisterPostRequest.getEmail());

            // 회원가입 유효성 검사
            if (!userService.isExistId(userRegisterPostRequest.getId())) {
                throw new BadRequestException("회원가입에 실패했습니다.");
            }

            // 반환
            return ResponseEntity.ok().build();
        } catch (BadRequestException e) {
            throw new BadRequestException(e.getMessage());
        } catch (Exception e) {
            throw new Exception("회원가입 중 오류가 발생했습니다.");
        }
    }

    public UserRegisterUseCase(UserService userService, AuthMailService authMailService) {
        this.userService = userService;
        this.authMailService = authMailService;
    }
}

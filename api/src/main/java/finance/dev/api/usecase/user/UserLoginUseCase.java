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
        try {
            // 유효성 검사 1. 아이디, 비밀번호 모두 입력되지 않았을 경우
            if (userLoginPostRequest.getUserId() == null
                    && userLoginPostRequest.getPassword() == null) {
                throw new BadRequestException("아이디와 비밀번호를 입력해주세요.");
            }

            // 유효성 검사 2. 아이디가 입력되지 않았을 경우
            if (userLoginPostRequest.getUserId() == null) {
                throw new BadRequestException("아이디를 입력해주세요.");
            }

            // 유효성 검사 3. 비밀번호가 입력되지 않았을 경우
            if (userLoginPostRequest.getPassword() == null) {
                throw new BadRequestException("비밀번호를 입력해주세요.");
            }

            // 유효성 검사 4. 아이디와 비밀번호가 일치하지 않을 경우
            if (!userService.login(
                    userLoginPostRequest.getUserId(), userLoginPostRequest.getPassword())) {
                throw new BadRequestException("아이디 또는 비밀번호가 일치하지 않습니다.");
            }

            // 로그인 성공 - 토큰 발급
            String accessToken = jwtHandler.generateAccessToken(userLoginPostRequest.getUserId());
            String refreshToken = jwtHandler.generateRefreshToken(userLoginPostRequest.getUserId());

            // 쿠키 생성 및 헤더에 추가
            ResponseCookie cookie =
                    ResponseCookie.from("Authorization", "Bearer " + refreshToken)
                            .httpOnly(true)
                            .path("/")
                            .maxAge(Duration.ofDays(30))
                            .build();

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.SET_COOKIE, cookie.toString());

            // 값 반환
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(UserLoginPostResponse.builder().accessToken(accessToken).build());

        } catch (BadRequestException e) {
            throw new BadRequestException(e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public UserLoginUseCase(UserService userService, JwtHandler jwtHandler) {
        this.userService = userService;
        this.jwtHandler = jwtHandler;
    }
}

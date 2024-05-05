package finance.dev.api.usecase;

import finance.dev.api.dto.admin.*;
import finance.dev.common.annotation.MethodInfo;
import finance.dev.common.annotation.TypeInfo;
import finance.dev.common.annotation.UseCase;
import finance.dev.domain.entity.AdminEntity;
import finance.dev.domain.handler.JwtHandler;
import finance.dev.domain.service.AdminService;
import lombok.Builder;
import org.apache.coyote.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;

import java.time.Duration;

@UseCase
@TypeInfo(name = "AdminUseCase", description = "관리자 유스케이스 클래스")
public class AdminUseCase {
    private final AdminService adminService;
    private final JwtHandler jwtHandler;

    @MethodInfo(name = "adminLoginPost", description = "관리자 로그인을 처리합니다.")
    public ResponseEntity<AdminLoginPostResponse> adminLoginPost(
            AdminLoginPostRequest adminLoginPostRequest) throws Exception {
        try {
            //유효성 검사 1. ID,PW가 null인지 확인
            if (adminLoginPostRequest.getUserId() == null
                    || adminLoginPostRequest.getPassword() == null) {
                throw new BadRequestException("ID 또는 PW가 null 입니다.");
            }

            //유효성 검사 2. ID,PW가 빈 문자열인지 확인
            if (adminLoginPostRequest.getUserId().isEmpty()
                    || adminLoginPostRequest.getPassword().isEmpty()) {
                throw new BadRequestException("ID 또는 PW가 빈 문자열입니다.");
            }

            AdminEntity adminEntity = adminService.findByUserId(adminLoginPostRequest.getUserId());

            if(adminEntity == null){
                throw new BadRequestException("ID가 존재하지 않습니다.");
            }

            if(!adminEntity.getPassword().equals(adminLoginPostRequest.getPassword())){
                throw new BadRequestException("PW가 존재하지 않습니다.");
            }

            // 로그인 성공(토큰 발급)
            String accessToken = jwtHandler.generateAccessToken(adminLoginPostRequest.getUserId());
            String refreshToken = jwtHandler.generateRefreshToken(adminLoginPostRequest.getUserId());

            //쿠키 생성
            ResponseCookie cookie = ResponseCookie.from("Authorization","Bearer" + refreshToken)
                    .httpOnly(true)
                    .path("/")
                    .maxAge(Duration.ofDays(30))
                    .build();

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.SET_COOKIE, cookie.toString());

            //응답
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(AdminLoginPostResponse.builder()
                            .accessToken(accessToken)
                            .build());

        }catch (BadRequestException e){
            throw new BadRequestException(e.getMessage());
        }catch (Exception e){
            throw new Exception("관리자 로그인에 실패했습니다.");
        }
    }

    @MethodInfo(name = "adminRefreshTokenPost", description = "관리자 토큰 재발급을 처리합니다.")
    public ResponseEntity<AdminRefreshTokenPostResponse> adminRefreshTokenPost(
            String refreshToken) {
        return null;
    }

    @MethodInfo(name = "adminUsersPost", description = "관리자 회원 목록 조회를 처리합니다.")
    public ResponseEntity<AdminUsersPostResponse> adminUsersPost(
            AdminUsersPostRequest adminUsersPostRequest, String refreshToken) {
        return null;
    }

    @MethodInfo(name = "adminUserPost", description = "관리자 회원 조회를 처리합니다.")
    public ResponseEntity<AdminUserPostResponse> adminUserPost(
            AdminUserPostRequest adminUserPostRequest, String userIdx, String refreshToken) {
        return null;
    }

    @MethodInfo(name = "adminUserPatch", description = "관리자 회원 수정을 처리합니다.")
    public ResponseEntity<Void> adminUserPatch(
            AdminUserPatchRequest adminUserPatchRequest, String userIdx, String refreshToken) {
        return null;
    }

    @MethodInfo(name = "adminUserDelete", description = "관리자 회원 삭제를 처리합니다.")
    public ResponseEntity<Void> adminUserDelete(String userIdx, String refreshToken) {
        return null;
    }

    @MethodInfo(name = "adminStoresPost", description = "관리자 가게 목록 조회를 처리합니다.")
    public ResponseEntity<AdminStoresPostResponse> adminStoresPost(
            AdminStoresPostRequest adminStoresPostRequest, String refreshToken) {
        return null;
    }

    @MethodInfo(name = "adminStorePost", description = "관리자 가게 조회를 처리합니다.")
    public ResponseEntity<AdminStorePostResponse> adminStorePost(
            AdminStorePostRequest adminStorePostRequest, String storeIdx, String refreshToken) {
        return null;
    }

    @MethodInfo(name = "adminStorePatch", description = "관리자 가게 수정을 처리합니다.")
    public ResponseEntity<Void> adminStorePatch(
            AdminStorePatchRequest adminStorePatchRequest, String storeIdx, String refreshToken) {
        return null;
    }

    @MethodInfo(name = "adminStoreDelete", description = "관리자 가게 삭제를 처리합니다.")
    public ResponseEntity<Void> adminStoreDelete(String storeIdx, String refreshToken) {
        return null;
    }

    @MethodInfo(name = "adminProductsPost", description = "관리자 상품 목록 조회를 처리합니다.")
    public ResponseEntity<AdminProductsPostResponse> adminProductsPost(
            AdminProductsPostRequest adminProductsPostRequest, String refreshToken) {
        return null;
    }

    @MethodInfo(name = "adminProductPost", description = "관리자 상품 조회를 처리합니다.")
    public ResponseEntity<AdminProductPostResponse> adminProductPost(
            AdminProductPostRequest adminProductPostRequest,
            String productIdx,
            String refreshToken) {
        return null;
    }

    @MethodInfo(name = "adminProductPatch", description = "관리자 상품 수정을 처리합니다.")
    public ResponseEntity<Void> adminProductPatch(
            AdminProductPatchRequest adminProductPatchRequest,
            String productIdx,
            String refreshToken) {
        return null;
    }

    @MethodInfo(name = "adminProductDelete", description = "관리자 상품 삭제를 처리합니다.")
    public ResponseEntity<Void> adminProductDelete(String productIdx, String refreshToken) {
        return null;
    }

    @Builder
    public AdminUseCase(AdminService adminService, JwtHandler jwtHandler) {
        this.adminService = adminService;
        this.jwtHandler = jwtHandler;
    }
}

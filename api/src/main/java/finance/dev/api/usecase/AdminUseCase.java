package finance.dev.api.usecase;

import finance.dev.api.dto.admin.*;
import finance.dev.common.annotation.MethodInfo;
import finance.dev.common.annotation.TypeInfo;
import finance.dev.common.annotation.UseCase;
import finance.dev.domain.handler.JwtHandler;
import finance.dev.domain.service.AdminService;
import lombok.Builder;
import org.springframework.http.ResponseEntity;

@UseCase
@TypeInfo(name = "AdminUseCase", description = "관리자 유스케이스 클래스")
public class AdminUseCase {
    private final AdminService adminService;
    private final JwtHandler jwtHandler;

    @MethodInfo(name = "adminLoginPost", description = "관리자 로그인을 처리합니다.")
    public ResponseEntity<AdminLoginPostResponse> adminLoginPost(
            AdminLoginPostRequest adminLoginPostRequest) throws Exception {
        return null;
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

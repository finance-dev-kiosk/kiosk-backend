package finance.dev.api.usecase;

import finance.dev.api.dto.admin.*;
import finance.dev.common.annotation.MethodInfo;
import finance.dev.common.annotation.TypeInfo;
import finance.dev.common.annotation.UseCase;
import finance.dev.domain.entity.AdminEntity;
import finance.dev.domain.entity.UserEntity;
import finance.dev.domain.handler.JwtHandler;
import finance.dev.domain.service.AdminService;
import finance.dev.domain.service.UserService;
import finance.dev.domain.type.UserSearchSort;
import finance.dev.domain.type.UserSearchType;
import lombok.Builder;
import org.apache.coyote.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import java.time.Duration;
import java.util.stream.Collectors;

@UseCase
@TypeInfo(name = "AdminUseCase", description = "관리자 유스케이스 클래스")
public class AdminUseCase {
    private static final Logger log = LoggerFactory.getLogger(AdminUseCase.class);
    private final AdminService adminService;
    private final JwtHandler jwtHandler;
    private final UserService userService;

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
            AdminUsersPostRequest adminUsersPostRequest) throws Exception{
        try{
            //토큰 파싱
            String userId= jwtHandler.parseAccessToken(adminUsersPostRequest.getAccessToken());

            //아이디 존재 유효성 검사
            if(!adminService.isExistId(userId)){
                throw new BadRequestException("존재하지 않는 아이디입니다.");
            }

            //검색 값 유효성 검사
            if(adminUsersPostRequest.getSearchValue() == null){
                throw new BadRequestException("검색값이 없습니다.");
            }

            //검색
            List<UserEntity> userEntities =
                    userService.searchUsers(
                    adminUsersPostRequest.getUserSearchType(),
                    adminUsersPostRequest.getSearchValue(),
                    adminUsersPostRequest.getSearchPageNum(),
                    adminUsersPostRequest.getSearchPageSize(),
                    adminUsersPostRequest.getUserSearchSort());

            //검색 값 반환
            AdminUsersPostResponse adminUsersPostResponse =
                    AdminUsersPostResponse.builder()
                            .userCount(userEntities.size())
                            .pageCount(
                                    userEntities.size()
                                    / adminUsersPostRequest.getSearchPageSize())
                            .users(
                                    userEntities.stream()
                                            .map(
                                                    userEntity ->
                                                            AdminUserPost.builder()
                                                                    .idx(userEntity.getIdx())
                                                                    .id(userEntity.getId())
                                                                    .name(userEntity.getName())
                                                                    .email(userEntity.getEmail())
                                                                    .build())
                                            .collect(Collectors.toCollection(ArrayList::new)))
                            .build();
            return ResponseEntity.ok().body(adminUsersPostResponse);
        } catch(BadRequestException e){
            throw new BadRequestException(e.getMessage());
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @MethodInfo(name = "adminUserPost", description = "관리자 회원 조회를 처리합니다.")
    public ResponseEntity<AdminUserPostResponse> adminUserPost(
            AdminUserPostRequest adminUserPostRequest, String userIdx) {
        return null;
    }

    @MethodInfo(name = "adminUserPatch", description = "관리자 회원 수정을 처리합니다.")
    public ResponseEntity<Void> adminUserPatch(
            AdminUserPatchRequest adminUserPatchRequest, String userIdx) {
        return null;
    }

    @MethodInfo(name = "adminUserDelete", description = "관리자 회원 삭제를 처리합니다.")
    public ResponseEntity<Void> adminUserDelete(String userIdx) {
        return null;
    }

    @MethodInfo(name = "adminStoresPost", description = "관리자 가게 목록 조회를 처리합니다.")
    public ResponseEntity<AdminStoresPostResponse> adminStoresPost(
            AdminStoresPostRequest adminStoresPostRequest) {
        return null;
    }

    @MethodInfo(name = "adminStorePost", description = "관리자 가게 조회를 처리합니다.")
    public ResponseEntity<AdminStorePostResponse> adminStorePost(
            AdminStorePostRequest adminStorePostRequest, String storeIdx) {
        return null;
    }

    @MethodInfo(name = "adminStorePatch", description = "관리자 가게 수정을 처리합니다.")
    public ResponseEntity<Void> adminStorePatch(
            AdminStorePatchRequest adminStorePatchRequest, String storeIdx) {
        return null;
    }

    @MethodInfo(name = "adminStoreDelete", description = "관리자 가게 삭제를 처리합니다.")
    public ResponseEntity<Void> adminStoreDelete(String storeIdx) {
        return null;
    }

    @MethodInfo(name = "adminProductsPost", description = "관리자 상품 목록 조회를 처리합니다.")
    public ResponseEntity<AdminProductsPostResponse> adminProductsPost(
            AdminProductsPostRequest adminProductsPostRequest) {
        return null;
    }

    @MethodInfo(name = "adminProductPost", description = "관리자 상품 조회를 처리합니다.")
    public ResponseEntity<AdminProductPostResponse> adminProductPost(
            AdminProductPostRequest adminProductPostRequest,
            String productIdx) {
        return null;
    }

    @MethodInfo(name = "adminProductPatch", description = "관리자 상품 수정을 처리합니다.")
    public ResponseEntity<Void> adminProductPatch(
            AdminProductPatchRequest adminProductPatchRequest,
            String productIdx) {
        return null;
    }

    @MethodInfo(name = "adminProductDelete", description = "관리자 상품 삭제를 처리합니다.")
    public ResponseEntity<Void> adminProductDelete(String productIdx) {
        return null;
    }

    @Builder
    public AdminUseCase(AdminService adminService, JwtHandler jwtHandler, UserService userService) {
        this.adminService = adminService;
        this.jwtHandler = jwtHandler;
        this.userService = userService;
    }
}

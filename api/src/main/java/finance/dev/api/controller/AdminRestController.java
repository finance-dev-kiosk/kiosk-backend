package finance.dev.api.controller;

import finance.dev.api.dto.admin.*;
import finance.dev.api.exception.ExceptionResponse;
import finance.dev.api.usecase.AdminUseCase;
import finance.dev.common.annotation.MethodInfo;
import finance.dev.common.annotation.TypeInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@TypeInfo(name = "AdminRestController", description = "관리자 컨트롤러 클래스")
@Tag(name = "Admin", description = "관리자 컨트롤러")
@RequestMapping("v1/api/admin")
@RestController
public class AdminRestController {
    public final AdminUseCase adminUseCase;

    @MethodInfo(name = "adminLoginPost", description = "관리자 로그인을 합니다.")
    @PostMapping("/login")
    @Operation(
            summary = "관리자 로그인",
            description = "관리자 로그인을 합니다.",
            method = "POST",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "관리자 로그인 성공",
                            content =
                            @Content(
                                    schema =
                                    @Schema(
                                            implementation =
                                                    AdminLoginPostResponse.class))),
                    @ApiResponse(
                            responseCode = "400",
                            description = "관리자 로그인 실패",
                            content =
                            @Content(
                                    schema =
                                    @Schema(implementation = ExceptionResponse.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "서버 에러",
                            content =
                            @Content(
                                    schema = @Schema(implementation = ExceptionResponse.class)))
            })
    public ResponseEntity<AdminLoginPostResponse> adminLoginPost(
            AdminLoginPostRequest adminLoginPostRequest) throws Exception {
        return adminUseCase.adminLoginPost(adminLoginPostRequest);
    }

    @MethodInfo(name = "adminRefreshTokenPost", description = "관리자 토큰을 갱신합니다.")
    @PostMapping("/refresh")
    @Operation(
            summary = "관리자 토큰 갱신",
            description = "관리자 토큰을 갱신합니다.",
            method = "POST",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "관리자 토큰 갱신 성공",
                            content =
                            @Content(
                                    schema =
                                    @Schema(
                                            implementation =
                                                    AdminRefreshTokenPostResponse
                                                            .class))),
                    @ApiResponse(
                            responseCode = "400",
                            description = "관리자 토큰 갱신 실패",
                            content =
                            @Content(
                                    schema =
                                    @Schema(implementation = ExceptionResponse.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "서버 에러",
                            content =
                            @Content(
                                    schema = @Schema(implementation = ExceptionResponse.class)))
            })
    public ResponseEntity<AdminRefreshTokenPostResponse> adminRefreshTokenPost(
            @CookieValue String refreshToken) throws Exception {
        return adminUseCase.adminRefreshTokenPost(refreshToken);
    }

    @MethodInfo(name = "adminUsersPost", description = "사용자 목록을 조회합니다.")
    @PostMapping("/users")
    @Operation(
            summary = "사용자 목록 조회",
            description = "사용자 목록을 조회합니다.",
            method = "POST",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "사용자 목록 조회 성공",
                            content =
                            @Content(
                                    schema =
                                    @Schema(
                                            implementation =
                                                    AdminUsersPostResponse.class))),
                    @ApiResponse(
                            responseCode = "400",
                            description = "사용자 목록 조회 실패",
                            content =
                            @Content(
                                    schema =
                                    @Schema(implementation = ExceptionResponse.class))),
                    @ApiResponse(
                            responseCode = "401",
                            description = "권한 없음",
                            content =
                            @Content(
                                    schema =
                                    @Schema(implementation = ExceptionResponse.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "서버 에러",
                            content =
                            @Content(
                                    schema = @Schema(implementation = ExceptionResponse.class)))
            })
    public ResponseEntity<AdminUsersPostResponse> adminUsersPost(
            AdminUsersPostRequest adminUsersPostRequest)
            throws Exception {
        return adminUseCase.adminUsersPost(adminUsersPostRequest);
    }

    @MethodInfo(name = "adminUserPost", description = "사용자 정보를 조회합니다.")
    @PostMapping("/users/{userIdx}")
    @Operation(
            summary = "사용자 조회",
            description = "사용자를 조회합니다.",
            method = "POST",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "사용자 조회 성공",
                            content =
                            @Content(
                                    schema =
                                    @Schema(
                                            implementation =
                                                    AdminUserPostResponse.class))),
                    @ApiResponse(
                            responseCode = "400",
                            description = "사용자 조회 실패",
                            content =
                            @Content(
                                    schema =
                                    @Schema(implementation = ExceptionResponse.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "서버 에러",
                            content =
                            @Content(
                                    schema = @Schema(implementation = ExceptionResponse.class)))
            })
    public ResponseEntity<AdminUserPostResponse> adminUserPost(
            AdminUserPostRequest adminUserPostRequest,
            @PathVariable Long userIdx)
            throws Exception {
        return adminUseCase.adminUserPost(adminUserPostRequest, userIdx);
    }

    @MethodInfo(name = "adminUserPatch", description = "사용자 정보를 수정합니다.")
    @PatchMapping("/users/{userIdx}")
    @Operation(
            summary = "사용자 수정",
            description = "사용자를 수정합니다.",
            method = "PATCH",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "사용자 수정 성공",
                            content =
                            @Content(
                                    schema =
                                    @Schema(implementation = ExceptionResponse.class))),
                    @ApiResponse(
                            responseCode = "400",
                            description = "사용자 수정 실패",
                            content =
                            @Content(
                                    schema =
                                    @Schema(implementation = ExceptionResponse.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "서버 에러",
                            content =
                            @Content(
                                    schema = @Schema(implementation = ExceptionResponse.class)))
            })
    public ResponseEntity<Void> adminUserPatch(
            AdminUserPatchRequest adminUserPatchRequest,
            @PathVariable Long userIdx)
            throws Exception {
        return adminUseCase.adminUserPatch(adminUserPatchRequest, userIdx);
    }

    @MethodInfo(name = "adminUserDelete", description = "사용자 정보를 삭제합니다.")
    @DeleteMapping("/users/{userIdx}")
    @Operation(
            summary = "사용자 삭제",
            description = "사용자를 삭제합니다.",
            method = "DELETE",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "사용자 삭제 성공",
                            content =
                            @Content(
                                    schema =
                                    @Schema(implementation = ExceptionResponse.class))),
                    @ApiResponse(
                            responseCode = "400",
                            description = "사용자 삭제 실패",
                            content =
                            @Content(
                                    schema =
                                    @Schema(implementation = ExceptionResponse.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "서버 에러",
                            content =
                            @Content(
                                    schema = @Schema(implementation = ExceptionResponse.class)))
            })
    public ResponseEntity<Void> adminUserDelete(
            AdminUserDeleteRequest adminUserDeleteRequest,
            @PathVariable Long userIdx) throws Exception {
        return adminUseCase.adminUserDelete(adminUserDeleteRequest,userIdx);
    }

    @MethodInfo(name = "adminStoresPost", description = "가게 목록을 조회합니다.")
    @PostMapping("/stores")
    @Operation(
            summary = "가게 목록 조회",
            description = "가게 목록을 조회합니다.",
            method = "POST",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "가게 목록 조회 성공",
                            content =
                            @Content(
                                    schema =
                                    @Schema(
                                            implementation =
                                                    AdminStoresPostResponse.class))),
                    @ApiResponse(
                            responseCode = "400",
                            description = "가게 목록 조회 실패",
                            content =
                            @Content(
                                    schema =
                                    @Schema(implementation = ExceptionResponse.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "서버 에러",
                            content =
                            @Content(
                                    schema = @Schema(implementation = ExceptionResponse.class)))
            })
    public ResponseEntity<AdminStoresPostResponse> adminStoresPost(
            AdminStoresPostRequest adminStoresPostRequest)
            throws Exception {
        return adminUseCase.adminStoresPost(adminStoresPostRequest);
    }

    @MethodInfo(name = "adminStorePost", description = "가게 정보를 조회합니다.")
    @PostMapping("/stores/{storeIdx}")
    @Operation(
            summary = "가게 조회",
            description = "가게를 조회합니다.",
            method = "POST",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "가게 조회 성공",
                            content =
                            @Content(
                                    schema =
                                    @Schema(
                                            implementation =
                                                    AdminStorePostResponse.class))),
                    @ApiResponse(
                            responseCode = "400",
                            description = "가게 조회 실패",
                            content =
                            @Content(
                                    schema =
                                    @Schema(implementation = ExceptionResponse.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "서버 에러",
                            content =
                            @Content(
                                    schema = @Schema(implementation = ExceptionResponse.class)))
            })
    public ResponseEntity<AdminStorePostResponse> adminStorePost(
            AdminStorePostRequest adminStorePostRequest,
            @PathVariable String storeIdx)
            throws Exception {
        return adminUseCase.adminStorePost(adminStorePostRequest, storeIdx);
    }

    @MethodInfo(name = "adminStorePatch", description = "가게 정보를 수정합니다.")
    @PatchMapping("/stores/{storeIdx}")
    @Operation(
            summary = "가게 수정",
            description = "가게를 수정합니다.",
            method = "PATCH",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "가게 수정 성공",
                            content =
                            @Content(
                                    schema =
                                    @Schema(implementation = ExceptionResponse.class))),
                    @ApiResponse(
                            responseCode = "400",
                            description = "가게 수정 실패",
                            content =
                            @Content(
                                    schema =
                                    @Schema(implementation = ExceptionResponse.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "서버 에러",
                            content =
                            @Content(
                                    schema = @Schema(implementation = ExceptionResponse.class)))
            })
    public ResponseEntity<Void> adminStorePatch(
            AdminStorePatchRequest adminStorePatchRequest,
            @PathVariable String storeIdx)
            throws Exception {
        return adminUseCase.adminStorePatch(adminStorePatchRequest, storeIdx);
    }

    @MethodInfo(name = "adminStoreDelete", description = "가게 정보를 삭제합니다.")
    @DeleteMapping("/stores/{storeIdx}")
    @Operation(
            summary = "가게 삭제",
            description = "가게를 삭제합니다.",
            method = "DELETE",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "가게 삭제 성공",
                            content =
                            @Content(
                                    schema =
                                    @Schema(implementation = ExceptionResponse.class))),
                    @ApiResponse(
                            responseCode = "400",
                            description = "가게 삭제 실패",
                            content =
                            @Content(
                                    schema =
                                    @Schema(implementation = ExceptionResponse.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "서버 에러",
                            content =
                            @Content(
                                    schema = @Schema(implementation = ExceptionResponse.class)))
            })
    public ResponseEntity<Void> adminStoreDelete(
            @PathVariable String storeIdx) throws Exception {
        return adminUseCase.adminStoreDelete(storeIdx);
    }

    @MethodInfo(name = "adminProductsPost", description = "상품 목록을 조회합니다.")
    @PostMapping("/products")
    @Operation(
            summary = "상품 목록 조회",
            description = "상품 목록을 조회합니다.",
            method = "POST",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "상품 목록 조회 성공",
                            content =
                            @Content(
                                    schema =
                                    @Schema(
                                            implementation =
                                                    AdminProductsPostResponse.class))),
                    @ApiResponse(
                            responseCode = "400",
                            description = "상품 목록 조회 실패",
                            content =
                            @Content(
                                    schema =
                                    @Schema(implementation = ExceptionResponse.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "서버 에러",
                            content =
                            @Content(
                                    schema = @Schema(implementation = ExceptionResponse.class)))
            })
    public ResponseEntity<AdminProductsPostResponse> adminProductsPost(
            AdminProductsPostRequest adminProductsPostRequest)
            throws Exception {
        return adminUseCase.adminProductsPost(adminProductsPostRequest);
    }

    @MethodInfo(name = "adminProductPost", description = "상품 정보를 조회합니다.")
    @PostMapping("/products/{productIdx}")
    @Operation(
            summary = "상품 조회",
            description = "상품를 조회합니다.",
            method = "POST",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "상품 조회 성공",
                            content =
                            @Content(
                                    schema =
                                    @Schema(
                                            implementation =
                                                    AdminProductPostResponse.class))),
                    @ApiResponse(
                            responseCode = "400",
                            description = "상품 조회 실패",
                            content =
                            @Content(
                                    schema =
                                    @Schema(implementation = ExceptionResponse.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "서버 에러",
                            content =
                            @Content(
                                    schema = @Schema(implementation = ExceptionResponse.class)))
            })
    public ResponseEntity<AdminProductPostResponse> adminProductPost(
            AdminProductPostRequest adminProductPostRequest,
            @PathVariable String productIdx)
            throws Exception {
        return adminUseCase.adminProductPost(adminProductPostRequest, productIdx);
    }

    @MethodInfo(name = "adminProductPatch", description = "상품 정보를 수정합니다.")
    @PatchMapping("/products/{productIdx}")
    @Operation(
            summary = "상품 수정",
            description = "상품를 수정합니다.",
            method = "PATCH",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "상품 수정 성공",
                            content =
                            @Content(
                                    schema =
                                    @Schema(implementation = ExceptionResponse.class))),
                    @ApiResponse(
                            responseCode = "400",
                            description = "상품 수정 실패",
                            content =
                            @Content(
                                    schema =
                                    @Schema(implementation = ExceptionResponse.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "서버 에러",
                            content =
                            @Content(
                                    schema = @Schema(implementation = ExceptionResponse.class)))
            })
    public ResponseEntity<Void> adminProductPatch(
            AdminProductPatchRequest adminProductPatchRequest,
            @PathVariable String productIdx)
            throws Exception {
        return adminUseCase.adminProductPatch(adminProductPatchRequest, productIdx);
    }

    @MethodInfo(name = "adminProductDelete", description = "상품 정보를 삭제합니다.")
    @DeleteMapping("/products/{productIdx}")
    @Operation(
            summary = "상품 삭제",
            description = "상품를 삭제합니다.",
            method = "DELETE",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "상품 삭제 성공",
                            content =
                            @Content(
                                    schema =
                                    @Schema(implementation = ExceptionResponse.class))),
                    @ApiResponse(
                            responseCode = "400",
                            description = "상품 삭제 실패",
                            content =
                            @Content(
                                    schema =
                                    @Schema(implementation = ExceptionResponse.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "서버 에러",
                            content =
                            @Content(
                                    schema = @Schema(implementation = ExceptionResponse.class)))
            })
    public ResponseEntity<Void> adminProductDelete(
            @PathVariable String productIdx) throws Exception {
        return adminUseCase.adminProductDelete(productIdx);
    }

    public AdminRestController(AdminUseCase adminUseCase) {
        this.adminUseCase = adminUseCase;
    }
}

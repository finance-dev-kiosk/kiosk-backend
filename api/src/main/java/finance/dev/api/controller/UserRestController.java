package finance.dev.api.controller;

import finance.dev.api.dto.user.*;
import finance.dev.api.exception.ExceptionResponse;
import finance.dev.api.usecase.user.*;
import finance.dev.common.annotation.MethodInfo;
import finance.dev.common.annotation.TypeInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@TypeInfo(name = "UserRestController", description = "사용자 컨트롤러 클래스")
@Tag(name = "User", description = "사용자 컨트롤러")
@RequestMapping("v1/api/user")
@RestController
public class UserRestController {
    private final UserLoginUseCase userLoginUseCase;
    private final UserRegisterUseCase userRegisterUseCase;
    private final UserChkUserIdUseCase userChkUserIdUseCase;
    private final UserChkUserNameUseCase userChkUserNameUseCase;
    private final UserChkEmailUseCase userChkEmailUseCase;
    private final UserChkEmailAuthUseCase userChkEmailAuthUseCase;
    private final UserStoresUseCase userStoresUseCase;
    private final UserStoreUseCase userStoreUseCase;
    private final UserStoreReviewsUseCase userStoreReviewsUseCase;
    private final UserStoreCommentsUseCase userStoreCommentsUseCase;
    private final UserStoreCommentCreateUseCase userStoreCommentCreateUseCase;
    private final UserStoreCommentUpdateUseCase userStoreCommentUpdateUseCase;
    private final UserStoreCommentDeleteUseCase userStoreCommentDeleteUseCase;
    private final UserStoreProductsUseCase userStoreProductsUseCase;

    @MethodInfo(name = "userLoginPost", description = "사용자 로그인을 합니다.")
    @PostMapping("/login")
    @Operation(
            summary = "사용자 로그인",
            description = "사용자 로그인을 합니다.",
            method = "POST",
            responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "사용자 로그인 성공",
                        content =
                                @Content(
                                        schema =
                                                @Schema(
                                                        implementation =
                                                                UserLoginPostResponse.class))),
                @ApiResponse(
                        responseCode = "400",
                        description = "사용자 로그인 실패",
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
    public ResponseEntity<UserLoginPostResponse> userLoginPost(
            UserLoginPostRequest userLoginPostRequest) throws Exception {
        return userLoginUseCase.execute(userLoginPostRequest);
    }

    @MethodInfo(name = "userRegisterPost", description = "사용자 회원가입을 합니다.")
    @PostMapping("/register")
    @Operation(
            summary = "사용자 회원가입",
            description = "사용자 회원가입을 합니다.",
            method = "POST",
            responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "사용자 회원가입 성공",
                        content =
                                @Content(
                                        schema =
                                                @Schema(implementation = ExceptionResponse.class))),
                @ApiResponse(
                        responseCode = "400",
                        description = "사용자 회원가입 실패",
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
    public ResponseEntity<Void> userRegisterPost(UserRegisterPostRequest userRegisterPostRequest)
            throws Exception {
        return userRegisterUseCase.execute(userRegisterPostRequest);
    }

    @MethodInfo(name = "userChkUserIdPost", description = "사용자 아이디 중복 체크를 합니다.")
    @PostMapping("/chk/userId")
    @Operation(
            summary = "사용자 아이디 중복 체크",
            description = "사용자 아이디 중복 체크를 합니다.",
            method = "POST",
            responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "사용자 아이디 중복 체크 성공",
                        content =
                                @Content(
                                        schema =
                                                @Schema(implementation = ExceptionResponse.class))),
                @ApiResponse(
                        responseCode = "400",
                        description = "사용자 아이디 중복 체크 실패",
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
    public ResponseEntity<Void> userChkUserIdPost(UserChkUserIdPostRequest userChkUserIdPostRequest)
            throws Exception {
        return userChkUserIdUseCase.execute(userChkUserIdPostRequest);
    }

    @MethodInfo(name = "userChkUserNamePost", description = "사용자 닉네임 중복 체크를 합니다.")
    @PostMapping("/chk/userName")
    @Operation(
            summary = "사용자 닉네임 중복 체크",
            description = "사용자 닉네임 중복 체크를 합니다.",
            method = "POST",
            responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "사용자 닉네임 중복 체크 성공",
                        content =
                                @Content(
                                        schema =
                                                @Schema(implementation = ExceptionResponse.class))),
                @ApiResponse(
                        responseCode = "400",
                        description = "사용자 닉네임 중복 체크 실패",
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
    public ResponseEntity<Void> userChkUserNamePost(
            UserChkUserNamePostRequest userChkUserNamePostRequest) throws Exception {
        return userChkUserNameUseCase.execute(userChkUserNamePostRequest);
    }

    @MethodInfo(name = "userChkEmailPost", description = "사용자 이메일 인증을 합니다.")
    @PostMapping("/chk/email")
    @Operation(
            summary = "사용자 이메일 인증",
            description = "사용자 이메일 인증을 합니다.",
            method = "POST",
            responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "사용자 이메일 인증 성공",
                        content =
                                @Content(
                                        schema =
                                                @Schema(implementation = ExceptionResponse.class))),
                @ApiResponse(
                        responseCode = "400",
                        description = "사용자 이메일 인증 실패",
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
    public ResponseEntity<Void> userChkEmailPost(UserChkEmailPostRequest userChkEmailPostRequest)
            throws Exception {
        return userChkEmailUseCase.execute(userChkEmailPostRequest);
    }

    @MethodInfo(name = "userChkEmailAuthPost", description = "사용자 이메일 인증 확인을 합니다.")
    @PostMapping("/chk/email/{email}/{authCode}")
    @Operation(
            summary = "사용자 이메일 인증 확인",
            description = "사용자 이메일 인증 확인을 합니다.",
            method = "POST",
            responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "사용자 이메일 인증 확인 성공",
                        content =
                                @Content(
                                        schema =
                                                @Schema(implementation = ExceptionResponse.class))),
                @ApiResponse(
                        responseCode = "400",
                        description = "사용자 이메일 인증 확인 실패",
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
    public ResponseEntity<Void> userChkEmailAuthPost(
            @PathVariable String email, @PathVariable String authCode) throws Exception {
        return userChkEmailAuthUseCase.execute(email, authCode);
    }

    @MethodInfo(name = "userStoresPost", description = "사용자 가게 목록을 조회합니다.")
    @PostMapping("/stores")
    @Operation(
            summary = "사용자 가게 목록 조회",
            description = "사용자 가게 목록을 조회합니다.",
            method = "POST",
            responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "사용자 가게 목록 조회 성공",
                        content =
                                @Content(
                                        schema =
                                                @Schema(
                                                        implementation =
                                                                UserStoresPostResponse.class))),
                @ApiResponse(
                        responseCode = "400",
                        description = "사용자 가게 목록 조회 실패",
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
    public ResponseEntity<UserStoresPostResponse> userStoresPost(
            UserStoresPostRequest userStoresPostRequest) throws Exception {
        return userStoresUseCase.execute(userStoresPostRequest);
    }

    @MethodInfo(name = "userStorePost", description = "사용자 가게 정보를 조회합니다.")
    @PostMapping("/stores/{storeId}")
    @Operation(
            summary = "사용자 가게 정보 조회",
            description = "사용자 가게 정보를 조회합니다.",
            method = "POST",
            responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "사용자 가게 정보 조회 성공",
                        content =
                                @Content(
                                        schema =
                                                @Schema(
                                                        implementation =
                                                                UserStorePostResponse.class))),
                @ApiResponse(
                        responseCode = "400",
                        description = "사용자 가게 정보 조회 실패",
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
    public ResponseEntity<UserStorePostResponse> userStorePost(
            UserStorePostRequest userStorePostRequest, @PathVariable Long storeId)
            throws Exception {
        return userStoreUseCase.execute(userStorePostRequest, storeId);
    }

    @MethodInfo(name = "userStoreReviewsPost", description = "사용자 가게 리뷰 목록을 조회합니다.")
    @PostMapping("/stores/{storeId}/reviews")
    @Operation(
            summary = "사용자 가게 리뷰 목록 조회",
            description = "사용자 가게 리뷰 목록을 조회합니다.",
            method = "POST",
            responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "사용자 가게 리뷰 목록 조회 성공",
                        content =
                                @Content(
                                        schema =
                                                @Schema(
                                                        implementation =
                                                                UserStoreReviewsPostResponse
                                                                        .class))),
                @ApiResponse(
                        responseCode = "400",
                        description = "사용자 가게 리뷰 목록 조회 실패",
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
    public ResponseEntity<UserStoreReviewsPostResponse> userStoreReviewsPost(
            UserStoreReviewsPostRequest userStoreReviewsPostRequest, @PathVariable Long storeId)
            throws Exception {
        return userStoreReviewsUseCase.execute(userStoreReviewsPostRequest, storeId);
    }

    @MethodInfo(name = "userStoreCommentsPost", description = "사용자 가게 댓글 목록을 조회합니다.")
    @PostMapping("/stores/{storeId}/comments")
    @Operation(
            summary = "사용자 가게 댓글 목록 조회",
            description = "사용자 가게 댓글 목록을 조회합니다.",
            method = "POST",
            responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "사용자 가게 댓글 목록 조회 성공",
                        content =
                                @Content(
                                        schema =
                                                @Schema(
                                                        implementation =
                                                                UserStoreCommentsPostResponse
                                                                        .class))),
                @ApiResponse(
                        responseCode = "400",
                        description = "사용자 가게 댓글 목록 조회 실패",
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
    public ResponseEntity<UserStoreCommentsPostResponse> userStoreCommentsPost(
            UserStoreCommentsPostRequest userStoreCommentsPostRequest, @PathVariable Long storeId)
            throws Exception {
        return userStoreCommentsUseCase.execute(userStoreCommentsPostRequest, storeId);
    }

    @MethodInfo(name = "userStoreCommentPost", description = "사용자 가게 댓글을 생성합니다.")
    @PostMapping("/stores/{storeId}/comments/create")
    @Operation(
            summary = "사용자 가게 댓글 생성",
            description = "사용자 가게 댓글을 생성합니다.",
            method = "POST",
            responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "사용자 가게 댓글 생성 성공",
                        content =
                                @Content(
                                        schema =
                                                @Schema(implementation = ExceptionResponse.class))),
                @ApiResponse(
                        responseCode = "400",
                        description = "사용자 가게 댓글 생성 실패",
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
    public ResponseEntity<Void> userStoreCommentPost(
            UserStoreCommentPostRequest userStoreCommentPostRequest, @PathVariable Long storeId)
            throws Exception {
        return userStoreCommentCreateUseCase.execute(userStoreCommentPostRequest, storeId);
    }

    @MethodInfo(name = "userStoreCommentPut", description = "사용자 가게 댓글을 수정합니다.")
    @PostMapping("/stores/{storeId}/comments/{commentId}/update")
    @Operation(
            summary = "사용자 가게 댓글 수정",
            description = "사용자 가게 댓글을 수정합니다.",
            method = "POST",
            responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "사용자 가게 댓글 수정 성공",
                        content =
                                @Content(
                                        schema =
                                                @Schema(implementation = ExceptionResponse.class))),
                @ApiResponse(
                        responseCode = "400",
                        description = "사용자 가게 댓글 수정 실패",
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
    public ResponseEntity<Void> userStoreCommentPut(
            UserStoreCommentPutRequest userStoreCommentPutRequest,
            @PathVariable Long storeId,
            @PathVariable Long commentId)
            throws Exception {
        return userStoreCommentUpdateUseCase.execute(
                userStoreCommentPutRequest, storeId, commentId);
    }

    @MethodInfo(name = "userStoreCommentDelete", description = "사용자 가게 댓글을 삭제합니다.")
    @DeleteMapping("/stores/{storeId}/comments/{commentId}/delete")
    @Operation(
            summary = "사용자 가게 댓글 삭제",
            description = "사용자 가게 댓글을 삭제합니다.",
            method = "DELETE",
            responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "사용자 가게 댓글 삭제 성공",
                        content =
                                @Content(
                                        schema =
                                                @Schema(implementation = ExceptionResponse.class))),
                @ApiResponse(
                        responseCode = "400",
                        description = "사용자 가게 댓글 삭제 실패",
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
    public ResponseEntity<Void> userStoreCommentDelete(
            UserStoreCommentDeleteRequest userStoreCommentDeleteRequest,
            @PathVariable Long storeId,
            @PathVariable Long commentId)
            throws Exception {
        return userStoreCommentDeleteUseCase.execute(
                userStoreCommentDeleteRequest, storeId, commentId);
    }

    @MethodInfo(name = "userStoreProductsPost", description = "사용자 가게 상품 목록을 조회합니다.")
    @PostMapping("/stores/{storeId}/products")
    @Operation(
            summary = "사용자 가게 상품 목록 조회",
            description = "사용자 가게 상품 목록을 조회합니다.",
            method = "POST",
            responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "사용자 가게 상품 목록 조회 성공",
                        content =
                                @Content(
                                        schema =
                                                @Schema(
                                                        implementation =
                                                                UserStoreProductsPostResponse
                                                                        .class))),
                @ApiResponse(
                        responseCode = "400",
                        description = "사용자 가게 상품 목록 조회 실패",
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
    public ResponseEntity<UserStoreProductsPostResponse> userStoreProductsPost(
            UserStoreProductsPostRequest userStoreProductsPostRequest, @PathVariable Long storeId)
            throws Exception {
        return userStoreProductsUseCase.execute(userStoreProductsPostRequest, storeId);
    }

    public UserRestController(
            UserLoginUseCase userLoginUseCase,
            UserRegisterUseCase userRegisterUseCase,
            UserChkUserIdUseCase userChkUserIdUseCase,
            UserChkUserNameUseCase userChkUserNameUseCase,
            UserChkEmailUseCase userChkEmailUseCase,
            UserChkEmailAuthUseCase userChkEmailAuthUseCase,
            UserStoresUseCase userStoresUseCase,
            UserStoreUseCase userStoreUseCase,
            UserStoreReviewsUseCase userStoreReviewsUseCase,
            UserStoreCommentsUseCase userStoreCommentsUseCase,
            UserStoreCommentCreateUseCase userStoreCommentCreateUseCase,
            UserStoreCommentUpdateUseCase userStoreCommentUpdateUseCase,
            UserStoreCommentDeleteUseCase userStoreCommentDeleteUseCase,
            UserStoreProductsUseCase userStoreProductsUseCase) {
        this.userLoginUseCase = userLoginUseCase;
        this.userRegisterUseCase = userRegisterUseCase;
        this.userChkUserIdUseCase = userChkUserIdUseCase;
        this.userChkUserNameUseCase = userChkUserNameUseCase;
        this.userChkEmailUseCase = userChkEmailUseCase;
        this.userChkEmailAuthUseCase = userChkEmailAuthUseCase;
        this.userStoresUseCase = userStoresUseCase;
        this.userStoreUseCase = userStoreUseCase;
        this.userStoreReviewsUseCase = userStoreReviewsUseCase;
        this.userStoreCommentsUseCase = userStoreCommentsUseCase;
        this.userStoreCommentCreateUseCase = userStoreCommentCreateUseCase;
        this.userStoreCommentUpdateUseCase = userStoreCommentUpdateUseCase;
        this.userStoreCommentDeleteUseCase = userStoreCommentDeleteUseCase;
        this.userStoreProductsUseCase = userStoreProductsUseCase;
    }
}

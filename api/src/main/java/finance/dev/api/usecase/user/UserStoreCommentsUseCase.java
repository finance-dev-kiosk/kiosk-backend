package finance.dev.api.usecase.user;

import finance.dev.api.dto.user.UserStoreCommentsPostRequest;
import finance.dev.api.dto.user.UserStoreCommentsPostResponse;
import finance.dev.common.annotation.MethodInfo;
import finance.dev.common.annotation.TypeInfo;
import finance.dev.common.annotation.UseCase;
import finance.dev.domain.service.CommentService;
import finance.dev.domain.service.UserService;
import org.springframework.http.ResponseEntity;

@UseCase
@TypeInfo(name = "UserStoreCommentsUseCase", description = "사용자 가게 댓글목록 조회 유스케이스 클래스")
public class UserStoreCommentsUseCase {
    private final UserService userService;
    private final CommentService commentService;

    @MethodInfo(name = "execute", description = "가게에 대한 댓글목록을 조회합니다.")
    public ResponseEntity<UserStoreCommentsPostResponse> execute(
            UserStoreCommentsPostRequest userStoreCommentsPostRequest, Long storeId)
            throws Exception {
        return null;
    }

    public UserStoreCommentsUseCase(UserService userService, CommentService commentService) {
        this.userService = userService;
        this.commentService = commentService;
    }
}

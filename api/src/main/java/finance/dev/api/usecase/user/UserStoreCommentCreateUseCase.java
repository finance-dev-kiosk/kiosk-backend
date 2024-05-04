package finance.dev.api.usecase.user;

import finance.dev.api.dto.user.UserStoreCommentPostRequest;
import finance.dev.common.annotation.MethodInfo;
import finance.dev.common.annotation.TypeInfo;
import finance.dev.common.annotation.UseCase;
import finance.dev.domain.service.CommentService;
import finance.dev.domain.service.UserService;
import org.springframework.http.ResponseEntity;

@UseCase
@TypeInfo(name = "UserStoreCommentCreateUseCase", description = "사용자 가게 댓글 생성 유스케이스 클래스")
public class UserStoreCommentCreateUseCase {
    private final UserService userService;
    private final CommentService commentService;

    @MethodInfo(name = "execute", description = "가게에 대한 댓글을 생성합니다.")
    public ResponseEntity<Void> execute(
            UserStoreCommentPostRequest userStoreCommentPostRequest, Long storeId)
            throws Exception {
        return null;
    }

    public UserStoreCommentCreateUseCase(UserService userService, CommentService commentService) {
        this.userService = userService;
        this.commentService = commentService;
    }
}

package finance.dev.api.usecase.user;

import finance.dev.api.dto.user.UserStoreCommentPutRequest;
import finance.dev.common.annotation.MethodInfo;
import finance.dev.common.annotation.TypeInfo;
import finance.dev.common.annotation.UseCase;
import finance.dev.domain.service.CommentService;
import finance.dev.domain.service.UserService;
import org.springframework.http.ResponseEntity;

@UseCase
@TypeInfo(name = "UserStoreCommentUpdateUseCase", description = "유저 가게 댓글 수정 유스케이스 클래스")
public class UserStoreCommentUpdateUseCase {
    private final UserService userService;
    private final CommentService commentService;

    @MethodInfo(name = "execute", description = "가게에 대한 댓글을 수정합니다.")
    public ResponseEntity<Void> execute(
            UserStoreCommentPutRequest userStoreCommentPutRequest, Long storeId, Long commentId)
            throws Exception {
        return null;
    }

    public UserStoreCommentUpdateUseCase(UserService userService, CommentService commentService) {
        this.userService = userService;
        this.commentService = commentService;
    }
}

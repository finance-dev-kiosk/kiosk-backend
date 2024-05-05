package finance.dev.api.usecase.user;

import finance.dev.api.dto.user.UserStoreCommentDeleteRequest;
import finance.dev.common.annotation.MethodInfo;
import finance.dev.common.annotation.TypeInfo;
import finance.dev.common.annotation.UseCase;
import finance.dev.domain.service.CommentService;
import finance.dev.domain.service.UserService;
import org.springframework.http.ResponseEntity;

@UseCase
@TypeInfo(name = "UserStoreCommentDeleteUseCase", description = "사용자 가게 댓글 삭제 유스케이스 클래스")
public class UserStoreCommentDeleteUseCase {
    private final UserService userService;
    private final CommentService commentService;

    @MethodInfo(name = "execute", description = "가게에 대한 댓글을 삭제합니다.")
    public ResponseEntity<Void> execute(
            UserStoreCommentDeleteRequest userStoreCommentDeleteRequest,
            Long storeId,
            Long commentId)
            throws Exception {
        return null;
    }

    public UserStoreCommentDeleteUseCase(UserService userService, CommentService commentService) {
        this.userService = userService;
        this.commentService = commentService;
    }
}

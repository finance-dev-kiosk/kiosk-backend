package finance.dev.api.usecase.user;

import finance.dev.api.dto.user.UserStoreCommentDeleteRequest;
import finance.dev.common.annotation.MethodInfo;
import finance.dev.common.annotation.TypeInfo;
import finance.dev.common.annotation.UseCase;
import finance.dev.domain.handler.JwtHandler;
import finance.dev.domain.service.CommentService;
import finance.dev.domain.service.UserService;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;

@UseCase
@TypeInfo(name = "UserStoreCommentDeleteUseCase", description = "사용자 가게 댓글 삭제 유스케이스 클래스")
public class UserStoreCommentDeleteUseCase {
    private final UserService userService;
    private final CommentService commentService;
    private final JwtHandler jwtHandler;

    @MethodInfo(name = "execute", description = "가게에 대한 댓글을 삭제합니다.")
    public ResponseEntity<Void> execute(
            UserStoreCommentDeleteRequest userStoreCommentDeleteRequest,
            Long storeId,
            Long commentId)
            throws Exception {
        try {
            // 토큰 파싱
            String userId =
                    jwtHandler.parseAccessToken(userStoreCommentDeleteRequest.getAccessToken());

            // 아이디 존재 유효성 검사
            if (!userService.isExistId(userId)) {
                throw new BadRequestException("존재하지 않는 아이디입니다.");
            }

            // 가게 댓글 삭제
            commentService.deleteStoreComment(
                    storeId, commentId, userService.getUserIdxById(userId));

            // 응답
            return ResponseEntity.ok().build();
        } catch (BadRequestException e) {
            throw new BadRequestException(e.getMessage());
        } catch (IllegalStateException e) {
            throw new IllegalStateException(e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public UserStoreCommentDeleteUseCase(
            UserService userService, CommentService commentService, JwtHandler jwtHandler) {
        this.userService = userService;
        this.commentService = commentService;
        this.jwtHandler = jwtHandler;
    }
}

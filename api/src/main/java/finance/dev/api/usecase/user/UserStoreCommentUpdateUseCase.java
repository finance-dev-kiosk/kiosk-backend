package finance.dev.api.usecase.user;

import finance.dev.api.dto.user.UserStoreCommentPutRequest;
import finance.dev.common.annotation.MethodInfo;
import finance.dev.common.annotation.TypeInfo;
import finance.dev.common.annotation.UseCase;
import finance.dev.domain.handler.JwtHandler;
import finance.dev.domain.service.CommentService;
import finance.dev.domain.service.UserService;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;

@UseCase
@TypeInfo(name = "UserStoreCommentUpdateUseCase", description = "유저 가게 댓글 수정 유스케이스 클래스")
public class UserStoreCommentUpdateUseCase {
    private final UserService userService;
    private final CommentService commentService;
    private final JwtHandler jwtHandler;

    @MethodInfo(name = "execute", description = "가게에 대한 댓글을 수정합니다.")
    public ResponseEntity<Void> execute(
            UserStoreCommentPutRequest userStoreCommentPutRequest, Long storeId, Long commentId)
            throws Exception {
        try {
            // 가게 댓글이 비어있을 경우
            if (userStoreCommentPutRequest.getComment().isEmpty()) {
                throw new BadRequestException("댓글을 입력해주세요.");
            }

            // 토큰 파싱
            String userId =
                    jwtHandler.parseAccessToken(userStoreCommentPutRequest.getAccessToken());

            // 아이디 존재 유효성 검사
            if (!userService.isExistId(userId)) {
                throw new BadRequestException("존재하지 않는 아이디입니다.");
            }

            // 가게 댓글 수정
            commentService.updateStoreComment(
                    storeId,
                    commentId,
                    userService.getUserIdxById(userId),
                    userStoreCommentPutRequest.getComment());

            // 댓글 유효성 검사
            if (commentService.getStoreComments(storeId).isEmpty()) {
                throw new IllegalStateException("댓글 수정에 실패했습니다.");
            }

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

    public UserStoreCommentUpdateUseCase(
            UserService userService, CommentService commentService, JwtHandler jwtHandler) {
        this.userService = userService;
        this.commentService = commentService;
        this.jwtHandler = jwtHandler;
    }
}

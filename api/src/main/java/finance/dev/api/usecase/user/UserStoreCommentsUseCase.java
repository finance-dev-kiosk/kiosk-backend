package finance.dev.api.usecase.user;

import finance.dev.api.dto.user.UserCommentPost;
import finance.dev.api.dto.user.UserStoreCommentsPostRequest;
import finance.dev.api.dto.user.UserStoreCommentsPostResponse;
import finance.dev.common.annotation.MethodInfo;
import finance.dev.common.annotation.TypeInfo;
import finance.dev.common.annotation.UseCase;
import finance.dev.domain.entity.CommentEntity;
import finance.dev.domain.handler.JwtHandler;
import finance.dev.domain.service.CommentService;
import finance.dev.domain.service.UserService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;

@UseCase
@TypeInfo(name = "UserStoreCommentsUseCase", description = "사용자 가게 댓글목록 조회 유스케이스 클래스")
public class UserStoreCommentsUseCase {
    private final UserService userService;
    private final CommentService commentService;
    private final JwtHandler jwtHandler;

    @MethodInfo(name = "execute", description = "가게에 대한 댓글목록을 조회합니다.")
    public ResponseEntity<UserStoreCommentsPostResponse> execute(
            UserStoreCommentsPostRequest userStoreCommentsPostRequest, Long storeId)
            throws Exception {
        try {
            // 토큰 파싱
            String userId =
                    jwtHandler.parseAccessToken(userStoreCommentsPostRequest.getAccessToken());

            // 아이디 존재 유효성 검사
            if (!userService.isExistId(userId)) {
                throw new BadRequestException("존재하지 않는 아이디입니다.");
            }

            // 가게 댓글목록 조회
            List<CommentEntity> commentEntities = commentService.getStoreComments(storeId);

            // 가게 리뷰목록이 없을 경우
            if (commentEntities.isEmpty()) {
                throw new BadRequestException("가게 댓글목록이 없습니다.");
            }

            // 가게 댓글응답
            return ResponseEntity.ok(
                    UserStoreCommentsPostResponse.builder()
                            .commentCount(commentEntities.size())
                            .pageCount(1)
                            .comments(
                                    commentEntities.stream()
                                            .map(
                                                    commentEntity ->
                                                            UserCommentPost.builder()
                                                                    .idx(commentEntity.getIdx())
                                                                    .userIdx(
                                                                            commentEntity
                                                                                    .getUserIdx())
                                                                    .comment(
                                                                            commentEntity
                                                                                    .getContent())
                                                                    .userName(
                                                                            userService.getUserName(
                                                                                    commentEntity
                                                                                            .getUserIdx()))
                                                                    .commentDate(
                                                                            commentEntity
                                                                                    .getCreated())
                                                                    .isMine(
                                                                            userService
                                                                                    .getUserId(
                                                                                            commentEntity
                                                                                                    .getUserIdx())
                                                                                    .equals(userId))
                                                                    .build())
                                            .collect(Collectors.toCollection(ArrayList::new)))
                            .build());
        } catch (BadRequestException e) {
            throw new BadRequestException(e.getMessage());
        } catch (IllegalStateException e) {
            throw new IllegalStateException(e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public UserStoreCommentsUseCase(
            UserService userService, CommentService commentService, JwtHandler jwtHandler) {
        this.userService = userService;
        this.commentService = commentService;
        this.jwtHandler = jwtHandler;
    }
}

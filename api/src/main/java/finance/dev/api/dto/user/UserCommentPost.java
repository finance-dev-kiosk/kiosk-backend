package finance.dev.api.dto.user;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserCommentPost {
    private Long idx; // 댓글 ID
    private Long userIdx; // 사용자 ID
    private String userName; // 사용자 이름
    private String comment; // 댓글 내용
    private LocalDateTime commentDate; // 댓글 작성일
    private boolean isMine; // 내 댓글 여부

    @Builder
    public UserCommentPost(
            Long idx,
            Long userIdx,
            String userName,
            String comment,
            LocalDateTime commentDate,
            boolean isMine) {
        this.idx = idx;
        this.userIdx = userIdx;
        this.userName = userName;
        this.comment = comment;
        this.commentDate = commentDate;
        this.isMine = isMine;
    }
}

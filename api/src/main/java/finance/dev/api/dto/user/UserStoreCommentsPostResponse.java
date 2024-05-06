package finance.dev.api.dto.user;

import java.util.ArrayList;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserStoreCommentsPostResponse {
    private int commentCount; // 댓글 수
    private int pageCount; // 페이지 수
    private ArrayList<UserCommentPost> comments; // 댓글 목록

    @Builder
    public UserStoreCommentsPostResponse(
            int commentCount, int pageCount, ArrayList<UserCommentPost> comments) {
        this.commentCount = commentCount;
        this.pageCount = pageCount;
        this.comments = comments;
    }
}

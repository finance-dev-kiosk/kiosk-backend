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

class UserCommentPost {
    private String idx; // 댓글 ID
    private String userIdx; // 사용자 ID
    private String userName; // 사용자 이름
    private String comment; // 댓글 내용
    private String commentDate; // 댓글 작성일
    private boolean isMine; // 내 댓글 여부

    public UserCommentPost(
            String idx,
            String userIdx,
            String userName,
            String comment,
            String commentDate,
            boolean isMine) {
        this.idx = idx;
        this.userIdx = userIdx;
        this.userName = userName;
        this.comment = comment;
        this.commentDate = commentDate;
        this.isMine = isMine;
    }
}

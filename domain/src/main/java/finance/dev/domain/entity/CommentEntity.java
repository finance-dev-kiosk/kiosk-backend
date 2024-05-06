package finance.dev.domain.entity;

import finance.dev.common.annotation.TypeInfo;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@TypeInfo(name = "CommentEntity", description = "댓글 엔티티 클래스")
@Table(name = "tbl_comment")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentEntity extends BaseDateTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_idx")
    private Long idx; // 댓글 식별자

    @Column(name = "comment_content")
    private String content; // 댓글 내용

    @Column(name = "user_idx")
    private Long userIdx; // 회원 식별자

    @Column(name = "store_idx")
    private Long storeIdx; // 가게 식별자

    @Builder
    public CommentEntity(Long idx, String content, Long userIdx, Long storeIdx) {
        this.idx = idx;
        this.content = content;
        this.userIdx = userIdx;
        this.storeIdx = storeIdx;
    }
}

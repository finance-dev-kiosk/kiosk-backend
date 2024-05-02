package finance.dev.domain.entity;

import finance.dev.common.annotation.TypeInfo;
import jakarta.persistence.*;
import java.util.ArrayList;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@TypeInfo(name = "UserEntity", description = "사용자 엔티티 클래스")
@Table(name = "tbl_user")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEntity extends BaseDateTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_idx")
    private Long idx; // 사용자 식별자

    @Column(name = "user_id", nullable = false, unique = true)
    private String id; // 사용자 아이디

    @Column(name = "user_password", nullable = false)
    private String password; // 사용자 비밀번호

    @Column(name = "user_name", nullable = false)
    private String name; // 사용자 닉네임

    @Column(name = "user_email", nullable = false, unique = true)
    private String email; // 사용자 이메일

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_idx")
    private ArrayList<CommentEntity> comments; // 사용자 댓글 목록

    @Builder
    public UserEntity(Long idx) {
        this.idx = idx;
    }
}

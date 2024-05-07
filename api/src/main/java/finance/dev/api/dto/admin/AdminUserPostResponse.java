package finance.dev.api.dto.admin;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AdminUserPostResponse {
    private Long idx; // 유저 인덱스
    private String id; // 유저 아이디
    private String name; // 유저 닉네임
    private String email; // 유저 이메일
    private LocalDateTime created; // 가입일
    private LocalDateTime updated; // 수정일

    @Builder
    public AdminUserPostResponse(
            Long idx,
            String id,
            String name,
            String email,
            LocalDateTime created,
            LocalDateTime updated) {
        this.idx = idx;
        this.id = id;
        this.name = name;
        this.email = email;
        this.created = created;
        this.updated = updated;
    }
}

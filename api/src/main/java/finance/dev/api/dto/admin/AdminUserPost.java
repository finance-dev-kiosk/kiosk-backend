package finance.dev.api.dto.admin;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AdminUserPost {
    private Long idx;
    private String id;
    private String name;
    private String email;

    @Builder
    public AdminUserPost(Long idx, String id, String name, String email) {
        this.idx = idx;
        this.id = id;
        this.name = name;
        this.email = email;
    }
}


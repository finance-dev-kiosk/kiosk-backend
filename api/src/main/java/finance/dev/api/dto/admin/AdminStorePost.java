package finance.dev.api.dto.admin;

import lombok.Builder;

public class AdminStorePost {
    private Long idx;
    private String name;
    private String category;

    @Builder
    public AdminStorePost(Long idx, String name, String category) {
        this.idx = idx;
        this.name = name;
        this.category = category;
    }
}

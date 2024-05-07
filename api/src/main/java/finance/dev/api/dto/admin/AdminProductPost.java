package finance.dev.api.dto.admin;

import lombok.Builder;

public class AdminProductPost {
    private Long idx;
    private String name;
    private int price;

    @Builder
    public AdminProductPost(Long idx, String name, int price) {
        this.idx = idx;
        this.name = name;
        this.price = price;
    }
}

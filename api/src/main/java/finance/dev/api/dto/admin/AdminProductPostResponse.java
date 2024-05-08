package finance.dev.api.dto.admin;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AdminProductPostResponse {
    private Long idx; // 상품 식별자
    private String name; // 상품 이름
    private int price; // 상품 가격

    @Builder
    public AdminProductPostResponse(Long idx, String name, int price) {
        this.idx = idx;
        this.name = name;
        this.price = price;
    }
}

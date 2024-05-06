package finance.dev.api.dto.user;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserProductPost {
    private Long idx; // 상품 idx
    private String name; // 상품 이름
    private int price; // 상품 가격

    @Builder
    public UserProductPost(Long idx, String name, int price) {
        this.idx = idx;
        this.name = name;
        this.price = price;
    }
}

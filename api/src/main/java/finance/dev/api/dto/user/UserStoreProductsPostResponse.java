package finance.dev.api.dto.user;

import java.util.ArrayList;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserStoreProductsPostResponse {
    private ArrayList<UserProductPost> products; // 상품 목록

    @Builder
    public UserStoreProductsPostResponse(ArrayList<UserProductPost> products) {
        this.products = products;
    }
}

class UserProductPost {
    public UserProductPost(Long idx, String name, int price) {}
}

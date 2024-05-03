package finance.dev.api.dto.admin;

import java.util.ArrayList;
import lombok.Getter;

@Getter
public class AdminProductsPostResponse {
    private int productCount; // 상품 수
    private int pageCount; // 페이지 수
    private ArrayList<AdminProductPost> products; // 상품 목록

    public AdminProductsPostResponse(
            int productCount, int pageCount, ArrayList<AdminProductPost> products) {
        this.productCount = productCount;
        this.pageCount = pageCount;
        this.products = products;
    }
}

class AdminProductPost {
    public AdminProductPost(Long idx, String name, int price) {}
}

package finance.dev.api.dto.admin;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AdminStorePostResponse {
    private Long idx; // 상점 식별자
    private String name; // 상점 이름
    private String category; // 상점 카테고리
    private String address; // 상점 주소
    private String phone; // 상점 전화번호
    private Boolean isDelivery; // 배달 가능 여부
    private Boolean isPackaged; // 포장 가능 여부

    @Builder
    public AdminStorePostResponse(
            Long idx,
            String name,
            String category,
            String address,
            String phone,
            Boolean isDelivery,
            Boolean isPackaged) {
        this.idx = idx;
        this.name = name;
        this.category = category;
        this.address = address;
        this.phone = phone;
        this.isDelivery = isDelivery;
        this.isPackaged = isPackaged;
    }
}

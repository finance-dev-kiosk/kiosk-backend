package finance.dev.api.dto.admin;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AdminStorePatchRequest {
    private String accessToken; // 액세스 토큰
    private String name; // 상점 이름
    private String category; // 상점 카테고리
    private String address1; // 상점 주소1
    private String address2; // 상점 주소2
    private String address3; // 상점 주소3
    private String phone; // 상점 전화번호
    private Boolean isDelivery; // 배달 가능 여부
    private Boolean isPackaged; // 포장 가능 여부

    @Builder
    public AdminStorePatchRequest(
            String accessToken,
            String name,
            String category,
            String address1,
            String address2,
            String address3,
            String phone,
            Boolean isDelivery,
            Boolean isPackaged) {
        this.accessToken = accessToken;
        this.name = name;
        this.category = category;
        this.address1 = address1;
        this.address2 = address2;
        this.address3 = address3;
        this.phone = phone;
        this.isDelivery = isDelivery;
        this.isPackaged = isPackaged;
    }
}

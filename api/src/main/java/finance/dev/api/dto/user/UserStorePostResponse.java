package finance.dev.api.dto.user;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserStorePostResponse {
    private String idx;
    private String name;
    private String category;
    private String address;
    private Boolean isDelivery;
    private Boolean isPackaged;
    private String phone;

    @Builder
    public UserStorePostResponse(
            String idx,
            String name,
            String category,
            String address,
            Boolean isDelivery,
            Boolean isPackaged,
            String phone) {
        this.idx = idx;
        this.name = name;
        this.category = category;
        this.address = address;
        this.isDelivery = isDelivery;
        this.isPackaged = isPackaged;
        this.phone = phone;
    }
}

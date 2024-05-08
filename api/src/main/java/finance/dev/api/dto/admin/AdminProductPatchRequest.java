package finance.dev.api.dto.admin;

import lombok.Getter;

@Getter
public class AdminProductPatchRequest {
    private String name;
    private int price;

    public AdminProductPatchRequest(String name, int price) {
        this.name = name;
        this.price = price;
    }
}

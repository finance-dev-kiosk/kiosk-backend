package finance.dev.api.dto.admin;

import lombok.Getter;

@Getter
public class AdminProductPatchRequest {
    private String name;
    private String price;

    public AdminProductPatchRequest(String name, String price) {
        this.name = name;
        this.price = price;
    }
}

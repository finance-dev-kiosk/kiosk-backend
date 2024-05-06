package finance.dev.api.dto.user;

import java.util.ArrayList;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserStorePost {
    private Long idx;
    private String name;
    private String category;
    private String address;
    private ArrayList<String> period;
    private Boolean isDelivery;
    private Boolean isPackaged;

    @Builder
    public UserStorePost(
            Long idx,
            String name,
            String category,
            String address,
            ArrayList<String> period,
            Boolean isDelivery,
            Boolean isPackaged) {
        this.idx = idx;
        this.name = name;
        this.category = category;
        this.address = address;
        this.period = period;
        this.isDelivery = isDelivery;
        this.isPackaged = isPackaged;
    }
}

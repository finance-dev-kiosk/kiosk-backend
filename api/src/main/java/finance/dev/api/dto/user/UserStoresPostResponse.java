package finance.dev.api.dto.user;

import java.util.ArrayList;
import lombok.Builder;

public class UserStoresPostResponse {
    private int storeCount; // 가게 수
    private int pageCount; // 페이지 수
    private ArrayList<UserStorePost> stores; // 가게 목록

    @Builder
    public UserStoresPostResponse(int storeCount, int pageCount, ArrayList<UserStorePost> stores) {
        this.storeCount = storeCount;
        this.pageCount = pageCount;
        this.stores = stores;
    }
}

class UserStorePost {
    @Builder
    public UserStorePost(
            Long idx,
            String name,
            String category,
            String address,
            ArrayList<String> period,
            Boolean isDelivery,
            Boolean isPackaged) {}
}

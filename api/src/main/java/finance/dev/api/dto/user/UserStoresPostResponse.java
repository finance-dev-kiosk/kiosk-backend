package finance.dev.api.dto.user;

import java.util.ArrayList;
import lombok.Builder;
import lombok.Getter;

@Getter
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

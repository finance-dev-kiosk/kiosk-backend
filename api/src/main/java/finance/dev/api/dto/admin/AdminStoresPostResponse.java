package finance.dev.api.dto.admin;

import java.util.ArrayList;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AdminStoresPostResponse {
    private int storeCount; // 가게 수
    private int pageCount; // 페이지 수
    private ArrayList<AdminStorePost> stores; // 가게 목록

    @Builder
    public AdminStoresPostResponse(
            int storeCount, int pageCount, ArrayList<AdminStorePost> stores) {
        this.storeCount = storeCount;
        this.pageCount = pageCount;
        this.stores = stores;
    }
}


package finance.dev.api.dto.user;

import finance.dev.domain.type.StoreSearchSort;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserStoresPostRequest {
    private String accessToken; // 액세스 토큰
    private String searchValue; // 검색 값
    private int searchPageNum; // 검색 페이지 번호
    private int searchPageSize; // 검색 페이지 사이즈
    private StoreSearchSort userSearchSort; // 검색 정렬

    @Builder
    public UserStoresPostRequest(
            String accessToken,
            String searchValue,
            int searchPageNum,
            int searchPageSize,
            StoreSearchSort userSearchSort) {
        this.accessToken = accessToken;
        this.searchValue = searchValue;
        this.searchPageNum = searchPageNum;
        this.searchPageSize = searchPageSize;
        this.userSearchSort = userSearchSort;
    }
}

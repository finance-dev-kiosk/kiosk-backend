package finance.dev.api.dto.user;

import finance.dev.domain.type.StoreSearchSort;
import finance.dev.domain.type.StoreSearchType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserStoresPostRequest {
    private String accessToken; // 액세스 토큰
    private String searchValue; // 검색 값
    private int searchPageNum; // 검색 페이지 번호
    private int searchPageSize; // 검색 페이지 사이즈
    private StoreSearchSort userSearchSort; // 검색 정렬
    private StoreSearchType userSearchType; // 검색 타입

    @Builder
    public UserStoresPostRequest(
            String accessToken,
            String searchValue,
            int searchPageNum,
            int searchPageSize,
            StoreSearchSort userSearchSort,
            StoreSearchType userSearchType) {
        this.accessToken = accessToken;
        this.searchValue = searchValue;
        this.searchPageNum = searchPageNum;
        this.searchPageSize = searchPageSize;
        this.userSearchSort = userSearchSort;
        this.userSearchType = userSearchType;
    }
}

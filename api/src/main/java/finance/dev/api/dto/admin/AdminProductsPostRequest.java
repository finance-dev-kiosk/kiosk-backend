package finance.dev.api.dto.admin;

import finance.dev.domain.type.ProductSearchSort;
import finance.dev.domain.type.StoreSearchType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class AdminProductsPostRequest {
    private String accessToken; // 액세스 토큰
    private String searchValue; // 검색 값
    private int searchPageNum; // 검색 페이지 번호
    private int searchPageSize; // 검색 페이지 사이즈
    private ProductSearchSort productSearchSort; // 검색 정렬

    @Builder
    public AdminProductsPostRequest(
            String accessToken,
            String searchValue,
            int searchPageNum,
            int searchPageSize,
            ProductSearchSort productSearchSort) {
        this.accessToken = accessToken;
        this.searchValue = searchValue;
        this.searchPageNum = searchPageNum;
        this.searchPageSize = searchPageSize;
        this.productSearchSort = productSearchSort;
    }
}

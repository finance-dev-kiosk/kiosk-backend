package finance.dev.api.dto.admin;

import finance.dev.domain.type.UserSearchSort;
import finance.dev.domain.type.UserSearchType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class AdminUsersPostRequest {
    private String accessToken; // 액세스 토큰
    private UserSearchType userSearchType; // 검색 타입
    private String searchValue; // 검색 값
    private int searchPageNum; // 검색 페이지 번호
    private int searchPageSize; // 검색 페이지 사이즈
    private UserSearchSort userSearchSort; // 검색 정렬

    @Builder
    public AdminUsersPostRequest(
            String accessToken,
            UserSearchType userSearchType,
            String searchValue,
            int searchPageNum,
            int searchPageSize,
            UserSearchSort userSearchSort) {
        this.accessToken = accessToken;
        this.userSearchType = userSearchType;
        this.searchValue = searchValue;
        this.searchPageNum = searchPageNum;
        this.searchPageSize = searchPageSize;
        this.userSearchSort = userSearchSort;
    }
}

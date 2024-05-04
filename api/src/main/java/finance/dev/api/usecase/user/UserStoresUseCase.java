package finance.dev.api.usecase.user;

import finance.dev.api.dto.user.UserStoresPostRequest;
import finance.dev.api.dto.user.UserStoresPostResponse;
import finance.dev.common.annotation.MethodInfo;
import finance.dev.common.annotation.TypeInfo;
import finance.dev.common.annotation.UseCase;
import finance.dev.domain.service.StoreService;
import finance.dev.domain.service.UserService;
import org.springframework.http.ResponseEntity;

@UseCase
@TypeInfo(name = "UserStoresUseCase", description = "사용자 가게목록 조회 유스케이스 클래스")
public class UserStoresUseCase {
    private final UserService userService;
    private final StoreService storeService;

    @MethodInfo(name = "execute", description = "가게목록을 조회합니다.")
    public ResponseEntity<UserStoresPostResponse> execute(
            UserStoresPostRequest userStoresPostRequest) throws Exception {
        return null;
    }

    public UserStoresUseCase(UserService userService, StoreService storeService) {
        this.userService = userService;
        this.storeService = storeService;
    }
}

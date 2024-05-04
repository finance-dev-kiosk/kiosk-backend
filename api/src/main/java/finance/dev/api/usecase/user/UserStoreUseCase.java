package finance.dev.api.usecase.user;

import finance.dev.api.dto.user.UserStorePostRequest;
import finance.dev.api.dto.user.UserStorePostResponse;
import finance.dev.common.annotation.MethodInfo;
import finance.dev.common.annotation.TypeInfo;
import finance.dev.common.annotation.UseCase;
import finance.dev.domain.service.StoreService;
import finance.dev.domain.service.UserService;
import org.springframework.http.ResponseEntity;

@UseCase
@TypeInfo(name = "UserStoreUseCase", description = "사용자 가게 조회 유스케이스 클래스")
public class UserStoreUseCase {
    private final UserService userService;
    private final StoreService storeService;

    @MethodInfo(name = "execute", description = "가게를 조회합니다.")
    public ResponseEntity<UserStorePostResponse> execute(
            UserStorePostRequest userStorePostRequest, Long storeId) throws Exception {
        return null;
    }

    public UserStoreUseCase(UserService userService, StoreService storeService) {
        this.userService = userService;
        this.storeService = storeService;
    }
}

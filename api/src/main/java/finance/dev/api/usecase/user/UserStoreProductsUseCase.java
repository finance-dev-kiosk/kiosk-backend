package finance.dev.api.usecase.user;

import finance.dev.api.dto.user.UserStoreProductsPostRequest;
import finance.dev.api.dto.user.UserStoreProductsPostResponse;
import finance.dev.common.annotation.MethodInfo;
import finance.dev.common.annotation.TypeInfo;
import finance.dev.common.annotation.UseCase;
import finance.dev.domain.service.ProductService;
import finance.dev.domain.service.UserService;
import org.springframework.http.ResponseEntity;

@UseCase
@TypeInfo(name = "UserStoreProductsUseCase", description = "사용자 가게 상품목록 조회 유스케이스 클래스")
public class UserStoreProductsUseCase {
    private final UserService userService;
    private final ProductService productService;

    @MethodInfo(name = "execute", description = "가게에 대한 상품목록을 조회합니다.")
    public ResponseEntity<UserStoreProductsPostResponse> execute(
            UserStoreProductsPostRequest userStoreProductsPostRequest, Long storeId)
            throws Exception {
        return null;
    }

    public UserStoreProductsUseCase(UserService userService, ProductService productService) {
        this.userService = userService;
        this.productService = productService;
    }
}

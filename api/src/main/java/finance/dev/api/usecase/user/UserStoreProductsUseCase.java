package finance.dev.api.usecase.user;

import finance.dev.api.dto.user.UserProductPost;
import finance.dev.api.dto.user.UserStoreProductsPostRequest;
import finance.dev.api.dto.user.UserStoreProductsPostResponse;
import finance.dev.common.annotation.MethodInfo;
import finance.dev.common.annotation.TypeInfo;
import finance.dev.common.annotation.UseCase;
import finance.dev.domain.entity.ProductEntity;
import finance.dev.domain.handler.JwtHandler;
import finance.dev.domain.service.ProductService;
import finance.dev.domain.service.UserService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;

@UseCase
@TypeInfo(name = "UserStoreProductsUseCase", description = "사용자 가게 상품목록 조회 유스케이스 클래스")
public class UserStoreProductsUseCase {
    private final UserService userService;
    private final ProductService productService;
    private final JwtHandler jwtHandler;

    @MethodInfo(name = "execute", description = "가게에 대한 상품목록을 조회합니다.")
    public ResponseEntity<UserStoreProductsPostResponse> execute(
            UserStoreProductsPostRequest userStoreProductsPostRequest, Long storeId)
            throws Exception {
        try {
            // 토큰 파싱
            String userId =
                    jwtHandler.parseAccessToken(userStoreProductsPostRequest.getAccessToken());

            // 아이디 존재 유효성 검사
            if (!userService.isExistId(userId)) {
                throw new BadRequestException("존재하지 않는 아이디입니다.");
            }

            // 가게 상품목록 조회
            List<ProductEntity> productEntities = productService.getStoreProducts(storeId);

            // 가게 상품목록이 없을 경우
            if (productEntities.isEmpty()) {
                throw new BadRequestException("가게 상품목록이 없습니다.");
            }

            // 가게 상품응답
            return ResponseEntity.ok(
                    UserStoreProductsPostResponse.builder()
                            .products(
                                    productEntities.stream()
                                            .map(
                                                    productEntity ->
                                                            UserProductPost.builder()
                                                                    .idx(productEntity.getIdx())
                                                                    .name(productEntity.getName())
                                                                    .price(productEntity.getPrice())
                                                                    .build())
                                            .collect(Collectors.toCollection(ArrayList::new)))
                            .build());
        } catch (BadRequestException e) {
            throw new BadRequestException(e.getMessage());
        } catch (IllegalStateException e) {
            throw new IllegalStateException(e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public UserStoreProductsUseCase(
            UserService userService, ProductService productService, JwtHandler jwtHandler) {
        this.userService = userService;
        this.productService = productService;
        this.jwtHandler = jwtHandler;
    }
}

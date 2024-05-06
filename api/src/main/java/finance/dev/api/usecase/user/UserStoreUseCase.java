package finance.dev.api.usecase.user;

import finance.dev.api.dto.user.UserStorePostRequest;
import finance.dev.api.dto.user.UserStorePostResponse;
import finance.dev.common.annotation.MethodInfo;
import finance.dev.common.annotation.TypeInfo;
import finance.dev.common.annotation.UseCase;
import finance.dev.domain.entity.StoreEntity;
import finance.dev.domain.handler.JwtHandler;
import finance.dev.domain.service.PeriodService;
import finance.dev.domain.service.StoreService;
import finance.dev.domain.service.UserService;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;

@UseCase
@TypeInfo(name = "UserStoreUseCase", description = "사용자 가게 조회 유스케이스 클래스")
public class UserStoreUseCase {
    private final UserService userService;
    private final StoreService storeService;
    private final PeriodService periodService;
    private final JwtHandler jwtHandler;

    @MethodInfo(name = "execute", description = "가게를 조회합니다.")
    public ResponseEntity<UserStorePostResponse> execute(
            UserStorePostRequest userStorePostRequest, Long storeId) throws Exception {
        try {
            // 토큰 파싱
            String userId = jwtHandler.parseAccessToken(userStorePostRequest.getAccessToken());

            // 아이디 존재 유효성 검사
            if (!userService.isExistId(userId)) {
                throw new BadRequestException("존재하지 않는 아이디입니다.");
            }

            // 가게 조회
            StoreEntity storeEntity = storeService.getStore(storeId);

            // 가게가 존재하지 않을 경우
            if (storeEntity == null) {
                throw new BadRequestException("존재하지 않는 가게입니다.");
            }

            // 가게 응답
            return ResponseEntity.ok(
                    UserStorePostResponse.builder()
                            .idx(storeEntity.getIdx().toString())
                            .name(storeEntity.getName())
                            .category(storeEntity.getCategory())
                            .address(
                                    storeEntity.getAddress1()
                                            + " "
                                            + storeEntity.getAddress2()
                                            + " "
                                            + storeEntity.getAddress3())
                            .isDelivery(storeEntity.getIsDelivery())
                            .isPackaged(storeEntity.getIsPackaged())
                            .phone(storeEntity.getTel())
                            .build());
        } catch (BadRequestException e) {
            throw new BadRequestException(e.getMessage());
        } catch (IllegalStateException e) {
            throw new IllegalStateException(e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public UserStoreUseCase(
            UserService userService,
            StoreService storeService,
            PeriodService periodService,
            JwtHandler jwtHandler) {
        this.userService = userService;
        this.storeService = storeService;
        this.periodService = periodService;
        this.jwtHandler = jwtHandler;
    }
}

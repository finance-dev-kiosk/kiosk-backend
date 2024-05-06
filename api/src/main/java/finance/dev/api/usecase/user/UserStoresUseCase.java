package finance.dev.api.usecase.user;

import finance.dev.api.dto.user.UserStorePost;
import finance.dev.api.dto.user.UserStoresPostRequest;
import finance.dev.api.dto.user.UserStoresPostResponse;
import finance.dev.common.annotation.MethodInfo;
import finance.dev.common.annotation.TypeInfo;
import finance.dev.common.annotation.UseCase;
import finance.dev.domain.entity.PeriodEntity;
import finance.dev.domain.entity.StoreEntity;
import finance.dev.domain.handler.JwtHandler;
import finance.dev.domain.service.PeriodService;
import finance.dev.domain.service.StoreService;
import finance.dev.domain.service.UserService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;

@UseCase
@TypeInfo(name = "UserStoresUseCase", description = "사용자 가게목록 조회 유스케이스 클래스")
public class UserStoresUseCase {
    private final UserService userService;
    private final StoreService storeService;
    private final PeriodService periodService;
    private final JwtHandler jwtHandler;

    @MethodInfo(name = "execute", description = "가게목록을 조회합니다.")
    public ResponseEntity<UserStoresPostResponse> execute(
            UserStoresPostRequest userStoresPostRequest) throws Exception {
        try {
            // 토큰 파싱
            String userId = jwtHandler.parseAccessToken(userStoresPostRequest.getAccessToken());

            // 아이디 존재 유효성 검사
            if (!userService.isExistId(userId)) {
                throw new BadRequestException("존재하지 않는 아이디입니다.");
            }

            // 검색 값 유효성 검사
            if (userStoresPostRequest.getSearchValue() == null) {
                throw new BadRequestException("검색 값이 없습니다.");
            }

            // 검색 페이지 번호 유효성 검사
            if (userStoresPostRequest.getSearchPageNum() < 0) {
                throw new BadRequestException("검색 페이지 번호가 잘못되었습니다.");
            }

            // 검색 페이지 사이즈 유효성 검사
            if (userStoresPostRequest.getSearchPageSize() < 0) {
                throw new BadRequestException("검색 페이지 사이즈가 잘못되었습니다.");
            }

            // 검색 정렬 유효성 검사
            if (userStoresPostRequest.getUserSearchSort() == null) {
                throw new BadRequestException("검색 정렬이 잘못되었습니다.");
            }

            // 가게목록 조회
            List<StoreEntity> storeEntities =
                    storeService.getStores(
                            userStoresPostRequest.getSearchValue(),
                            userStoresPostRequest.getSearchPageNum(),
                            userStoresPostRequest.getSearchPageSize(),
                            userStoresPostRequest.getUserSearchSort(),
                            userStoresPostRequest.getUserSearchType());

            // 운영 시간 조회
            List<PeriodEntity> periodEntities = periodService.findAll();

            // 가게목록 응답 생성
            UserStoresPostResponse userStoresPostResponse =
                    UserStoresPostResponse.builder()
                            .storeCount(storeEntities.size())
                            .pageCount(
                                    storeEntities.size()
                                            / userStoresPostRequest.getSearchPageSize())
                            .stores(
                                    storeEntities.stream()
                                            .map(
                                                    storeEntity ->
                                                            UserStorePost.builder()
                                                                    .idx(storeEntity.getIdx())
                                                                    .name(storeEntity.getName())
                                                                    .category(
                                                                            storeEntity
                                                                                    .getCategory())
                                                                    .address(
                                                                            storeEntity
                                                                                            .getAddress1()
                                                                                    + " "
                                                                                    + storeEntity
                                                                                            .getAddress2()
                                                                                    + " "
                                                                                    + storeEntity
                                                                                            .getAddress3())
                                                                    .period(
                                                                            periodEntities.stream()
                                                                                    .filter(
                                                                                            periodEntity ->
                                                                                                    periodEntity
                                                                                                            .getStoreIdx()
                                                                                                            .equals(
                                                                                                                    storeEntity
                                                                                                                            .getIdx()))
                                                                                    .map(
                                                                                            periodEntity ->
                                                                                                    periodEntity
                                                                                                                    .getPeriodTime()
                                                                                                            + " "
                                                                                                            + periodEntity
                                                                                                                    .getDay()
                                                                                                            + " "
                                                                                                            + periodEntity
                                                                                                                    .getTime())
                                                                                    .collect(
                                                                                            Collectors
                                                                                                    .toCollection(
                                                                                                            ArrayList
                                                                                                                    ::new)))
                                                                    .isDelivery(
                                                                            storeEntity
                                                                                    .getIsDelivery())
                                                                    .isPackaged(
                                                                            storeEntity
                                                                                    .getIsPackaged())
                                                                    .build())
                                            .collect(Collectors.toCollection(ArrayList::new)))
                            .build();

            return ResponseEntity.ok(userStoresPostResponse);
        } catch (BadRequestException e) {
            throw new BadRequestException(e.getMessage());
        } catch (IllegalStateException e) {
            throw new IllegalStateException(e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public UserStoresUseCase(
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

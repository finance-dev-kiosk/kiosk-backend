package finance.dev.api.usecase;

import finance.dev.api.dto.admin.*;
import finance.dev.common.annotation.MethodInfo;
import finance.dev.common.annotation.TypeInfo;
import finance.dev.common.annotation.UseCase;
import finance.dev.domain.entity.AdminEntity;
import finance.dev.domain.entity.ProductEntity;
import finance.dev.domain.entity.StoreEntity;
import finance.dev.domain.entity.UserEntity;
import finance.dev.domain.handler.JwtHandler;
import finance.dev.domain.service.AdminService;
import finance.dev.domain.service.ProductService;
import finance.dev.domain.service.StoreService;
import finance.dev.domain.service.UserService;
import finance.dev.domain.type.UserSearchSort;
import finance.dev.domain.type.UserSearchType;
import lombok.Builder;
import org.apache.coyote.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import java.time.Duration;
import java.util.stream.Collectors;

@UseCase
@TypeInfo(name = "AdminUseCase", description = "관리자 유스케이스 클래스")
public class AdminUseCase {
    private final AdminService adminService;
    private final JwtHandler jwtHandler;
    private final UserService userService;
    private final StoreService storeService;
    private final ProductService productService;

    @MethodInfo(name = "adminLoginPost", description = "관리자 로그인을 처리합니다.")
    public ResponseEntity<AdminLoginPostResponse> adminLoginPost(
            AdminLoginPostRequest adminLoginPostRequest) throws Exception {
        try {
            //유효성 검사 1. ID,PW가 null인지 확인
            if (adminLoginPostRequest.getUserId() == null
                    || adminLoginPostRequest.getPassword() == null) {
                throw new BadRequestException("ID 또는 PW가 null 입니다.");
            }

            //유효성 검사 2. ID,PW가 빈 문자열인지 확인
            if (adminLoginPostRequest.getUserId().isEmpty()
                    || adminLoginPostRequest.getPassword().isEmpty()) {
                throw new BadRequestException("ID 또는 PW가 빈 문자열입니다.");
            }

            AdminEntity adminEntity = adminService.findByUserId(adminLoginPostRequest.getUserId());

            if(adminEntity == null){
                throw new BadRequestException("ID가 존재하지 않습니다.");
            }

            if(!adminEntity.getPassword().equals(adminLoginPostRequest.getPassword())){
                throw new BadRequestException("PW가 존재하지 않습니다.");
            }

            // 로그인 성공(토큰 발급)
            String accessToken = jwtHandler.generateAccessToken(adminLoginPostRequest.getUserId());
            String refreshToken = jwtHandler.generateRefreshToken(adminLoginPostRequest.getUserId());

            //쿠키 생성
            ResponseCookie cookie = ResponseCookie.from("Authorization","Bearer" + refreshToken)
                    .httpOnly(true)
                    .path("/")
                    .maxAge(Duration.ofDays(30))
                    .build();

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.SET_COOKIE, cookie.toString());

            //응답
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(AdminLoginPostResponse.builder()
                            .accessToken(accessToken)
                            .build());

        }catch (BadRequestException e){
            throw new BadRequestException(e.getMessage());
        }catch (Exception e){
            throw new Exception("관리자 로그인에 실패했습니다.");
        }
    }

    @MethodInfo(name = "adminRefreshTokenPost", description = "관리자 토큰 재발급을 처리합니다.")
    public ResponseEntity<AdminRefreshTokenPostResponse> adminRefreshTokenPost(
            String refreshToken) {
        return null;
    }

    @MethodInfo(name = "adminUsersPost", description = "관리자 회원 목록 조회를 처리합니다.")
    public ResponseEntity<AdminUsersPostResponse> adminUsersPost(
            AdminUsersPostRequest adminUsersPostRequest) throws Exception{
        try{
            //토큰 파싱
            String userId= jwtHandler.parseAccessToken(adminUsersPostRequest.getAccessToken());

            //아이디 존재 유효성 검사
            if(!adminService.isExistId(userId)){
                throw new BadRequestException("존재하지 않는 아이디입니다.");
            }

            //검색 값 유효성 검사
            if(adminUsersPostRequest.getSearchValue() == null){
                throw new BadRequestException("검색값이 없습니다.");
            }

            //검색
            List<UserEntity> searchUserEntities =
                    userService.searchUsers(
                            adminUsersPostRequest.getUserSearchType(),
                            adminUsersPostRequest.getSearchValue(),
                            adminUsersPostRequest.getSearchPageNum(),
                            adminUsersPostRequest.getSearchPageSize(),
                            adminUsersPostRequest.getUserSearchSort());

            List<UserEntity> userEntities;

            if(searchUserEntities.size() % adminUsersPostRequest.getSearchPageSize() != 0){
                userEntities = searchUserEntities.subList(
                        adminUsersPostRequest.getSearchPageNum() * (adminUsersPostRequest.getSearchPageSize()-1) ,
                        searchUserEntities.size()-1
                );
            } else{
                userEntities = searchUserEntities.subList(
                                adminUsersPostRequest.getSearchPageNum() * adminUsersPostRequest.getSearchPageSize(),
                                adminUsersPostRequest.getSearchPageNum() * adminUsersPostRequest.getSearchPageSize()
                                        + adminUsersPostRequest.getSearchPageSize()
                        );
            }

            //검색 값 반환
            AdminUsersPostResponse adminUsersPostResponse =
                    AdminUsersPostResponse.builder()
                            .userCount(userEntities.size())
                            .pageCount(
                                    userEntities.size()
                                            / adminUsersPostRequest.getSearchPageSize())
                            .users(
                                    userEntities.stream()
                                            .map(
                                                    userEntity ->
                                                            AdminUserPost.builder()
                                                                    .idx(userEntity.getIdx())
                                                                    .id(userEntity.getId())
                                                                    .name(userEntity.getName())
                                                                    .email(userEntity.getEmail())
                                                                    .build())
                                            .collect(Collectors.toCollection(ArrayList::new)))
                            .build();
            return ResponseEntity.ok().body(adminUsersPostResponse);
        } catch(BadRequestException e){
            throw new BadRequestException(e.getMessage());
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @MethodInfo(name = "adminUserPost", description = "관리자 회원 조회를 처리합니다.")
    public ResponseEntity<AdminUserPostResponse> adminUserPost(
            AdminUserPostRequest adminUserPostRequest, Long userIdx) throws Exception {
        try{
            //토큰 파싱
            String userId= jwtHandler.parseAccessToken(adminUserPostRequest.getAccessToken());

            //아이디 존재 유효성 검사
            if(!adminService.isExistId(userId)){
                throw new BadRequestException("존재하지 않는 아이디입니다.");
            }

            UserEntity userEntity = userService.getUser(userIdx);

            if (userEntity == null) {
                throw new BadRequestException("존재하지 않는 회원입니다.");
            }

            return ResponseEntity.ok(
                    AdminUserPostResponse.builder()
                            .idx(userEntity.getIdx())
                            .id(userEntity.getId())
                            .name(userEntity.getName())
                            .email(userEntity.getEmail())
                            .created(userEntity.getCreated())
                            .updated(userEntity.getLastModified())
                            .build());
        } catch(BadRequestException e){
            throw new BadRequestException(e.getMessage());
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @MethodInfo(name = "adminUserPatch", description = "관리자 회원 수정을 처리합니다.")
    public ResponseEntity<Void> adminUserPatch(
            AdminUserPatchRequest adminUserPatchRequest, Long userIdx) throws Exception {
        try{
            //토큰 파싱
            String userId= jwtHandler.parseAccessToken(adminUserPatchRequest.getAccessToken());

            //아이디 존재 유효성 검사
            if(!adminService.isExistId(userId)){
                throw new BadRequestException("존재하지 않는 아이디입니다.");
            }

            String name = adminUserPatchRequest.getName();
            userService.updateUser(userIdx, name);

            return ResponseEntity.ok().build();

        }catch(BadRequestException e){
            throw new BadRequestException(e.getMessage());
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @MethodInfo(name = "adminUserDelete", description = "관리자 회원 삭제를 처리합니다.")
    public ResponseEntity<Void> adminUserDelete(
            AdminUserDeleteRequest adminUserDeleteRequest,
            Long userIdx) throws Exception{
        try{
            //토큰 파싱
            String userId= jwtHandler.parseAccessToken(adminUserDeleteRequest.getAccessToken());

            //아이디 존재 유효성 검사
            if(!adminService.isExistId(userId)){
                throw new BadRequestException("존재하지 않는 아이디입니다.");
            }
            userService.deleteUser(userIdx);

            return ResponseEntity.ok().build();
        } catch (BadRequestException e) {
            throw new BadRequestException(e.getMessage());
        } catch (IllegalStateException e) {
            throw new IllegalStateException(e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @MethodInfo(name = "adminStoresPost", description = "관리자 가게 목록 조회를 처리합니다.")
    public ResponseEntity<AdminStoresPostResponse> adminStoresPost(
            AdminStoresPostRequest adminStoresPostRequest)  throws Exception {
        try{
            //토큰 파싱
            String userId= jwtHandler.parseAccessToken(adminStoresPostRequest.getAccessToken());

            //아이디 존재 유효성 검사
            if(!adminService.isExistId(userId)){
                throw new BadRequestException("존재하지 않는 아이디입니다.");
            }

            //검색 값 유효성 검사
            if(adminStoresPostRequest.getSearchValue() == null){
                throw new BadRequestException("검색값이 없습니다.");
            }

            //검색
            List<StoreEntity> searchStoreEntities =
                    storeService.getStoresList(
                            adminStoresPostRequest.getSearchValue(),
                            adminStoresPostRequest.getSearchPageNum(),
                            adminStoresPostRequest.getSearchPageSize(),
                            adminStoresPostRequest.getStoreSearchSort(),
                            adminStoresPostRequest.getStoreSearchType());

            List<StoreEntity> storeEntities;

            if(searchStoreEntities.size() % adminStoresPostRequest.getSearchPageSize() != 0){
                storeEntities = searchStoreEntities.subList(
                        adminStoresPostRequest.getSearchPageNum() * (adminStoresPostRequest.getSearchPageSize()-1) ,
                        searchStoreEntities.size()-1
                );
            } else{
                storeEntities = searchStoreEntities.subList(
                        adminStoresPostRequest.getSearchPageNum() * adminStoresPostRequest.getSearchPageSize(),
                        adminStoresPostRequest.getSearchPageNum() * adminStoresPostRequest.getSearchPageSize()
                                + adminStoresPostRequest.getSearchPageSize()
                );
            }

            //검색 값 반환
            AdminStoresPostResponse adminStoresPostResponse =
                    AdminStoresPostResponse.builder()
                            .storeCount(storeEntities.size())
                            .pageCount(
                                    storeEntities.size()
                                            / adminStoresPostRequest.getSearchPageSize())
                            .stores(
                                    storeEntities.stream()
                                            .map(
                                                    storeEntity ->
                                                            AdminStorePost.builder()
                                                                    .idx(storeEntity.getIdx())
                                                                    .name(storeEntity.getName())
                                                                    .category(storeEntity.getCategory())
                                                                    .build())
                                            .collect(Collectors.toCollection(ArrayList::new)))
                            .build();
            return ResponseEntity.ok().body(adminStoresPostResponse);
        }catch(BadRequestException e){
            throw new BadRequestException(e.getMessage());
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @MethodInfo(name = "adminStorePost", description = "관리자 가게 조회를 처리합니다.")
    public ResponseEntity<AdminStorePostResponse> adminStorePost(
            AdminStorePostRequest adminStorePostRequest, Long storeIdx)  throws Exception {
        try{
            //토큰 파싱
            String userId= jwtHandler.parseAccessToken(adminStorePostRequest.getAccessToken());

            //아이디 존재 유효성 검사
            if(!adminService.isExistId(userId)){
                throw new BadRequestException("존재하지 않는 아이디입니다.");
            }

            StoreEntity storeEntity = storeService.getStore(storeIdx);

            if (storeEntity == null) {
                throw new BadRequestException("존재하지 않는 가게입니다.");
            }

            return ResponseEntity.ok(
                    AdminStorePostResponse.builder()
                            .idx(storeEntity.getIdx())
                            .name(storeEntity.getName())
                            .category(storeEntity.getCategory())
                            .phone(storeEntity.getTel())
                            .isDelivery(storeEntity.getIsDelivery())
                            .isPackaged(storeEntity.getIsPackaged())
                            .build());
        } catch(BadRequestException e){
            throw new BadRequestException(e.getMessage());
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @MethodInfo(name = "adminStorePatch", description = "관리자 가게 수정을 처리합니다.")
    public ResponseEntity<Void> adminStorePatch(
            AdminStorePatchRequest adminStorePatchRequest, Long storeIdx) throws Exception{
        try{
            //토큰 파싱
            String userId= jwtHandler.parseAccessToken(adminStorePatchRequest.getAccessToken());

            //아이디 존재 유효성 검사
            if(!adminService.isExistId(userId)){
                throw new BadRequestException("존재하지 않는 아이디입니다.");
            }

            storeService.updateStore(
                    storeIdx,
                    adminStorePatchRequest.getName(),
                    adminStorePatchRequest.getCategory(),
                    adminStorePatchRequest.getAddress1(),
                    adminStorePatchRequest.getAddress2(),
                    adminStorePatchRequest.getAddress3(),
                    adminStorePatchRequest.getPhone(),
                    adminStorePatchRequest.getIsDelivery(),
                    adminStorePatchRequest.getIsPackaged()
            );

            return ResponseEntity.ok().build();
        }catch(BadRequestException e){
            throw new BadRequestException(e.getMessage());
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @MethodInfo(name = "adminStoreDelete", description = "관리자 가게 삭제를 처리합니다.")
    public ResponseEntity<Void> adminStoreDelete(Long storeIdx)  throws Exception {
        try{
            storeService.deleteStore(storeIdx);
            return ResponseEntity.ok().build();
        } catch (IllegalStateException e) {
            throw new IllegalStateException(e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @MethodInfo(name = "adminProductsPost", description = "관리자 상품 목록 조회를 처리합니다.")
    public ResponseEntity<AdminProductsPostResponse> adminProductsPost(
            AdminProductsPostRequest adminProductsPostRequest)  throws Exception {
        try{
            //토큰 파싱
            String userId= jwtHandler.parseAccessToken(adminProductsPostRequest.getAccessToken());

            //아이디 존재 유효성 검사
            if(!adminService.isExistId(userId)){
                throw new BadRequestException("존재하지 않는 아이디입니다.");
            }

            //검색 값 유효성 검사
            if(adminProductsPostRequest.getSearchValue() == null){
                throw new BadRequestException("검색값이 없습니다.");
            }

            //검색
            List<ProductEntity> searchProductEntities =
                    productService.searchProducts(
                            adminProductsPostRequest.getSearchValue(),
                            adminProductsPostRequest.getSearchPageNum(),
                            adminProductsPostRequest.getSearchPageSize(),
                            adminProductsPostRequest.getProductSearchSort());

            List<ProductEntity> productEntities;

            if(searchProductEntities.size() % adminProductsPostRequest.getSearchPageSize() != 0){
                productEntities = searchProductEntities.subList(
                        adminProductsPostRequest.getSearchPageNum() * (adminProductsPostRequest.getSearchPageSize()-1) ,
                        searchProductEntities.size()-1
                );
            } else{
                productEntities = searchProductEntities.subList(
                        adminProductsPostRequest.getSearchPageNum() * adminProductsPostRequest.getSearchPageSize(),
                        adminProductsPostRequest.getSearchPageNum() * adminProductsPostRequest.getSearchPageSize()
                                + adminProductsPostRequest.getSearchPageSize()
                );
            }

            //검색 값 반환
            AdminProductsPostResponse adminProductsPostResponse =
                    AdminProductsPostResponse.builder()
                            .productCount(productEntities.size())
                            .pageCount(
                                    productEntities.size()
                                            / adminProductsPostRequest.getSearchPageSize())
                            .products(
                                    productEntities.stream()
                                            .map(
                                                    productEntity ->
                                                            AdminProductPost.builder()
                                                                    .idx(productEntity.getIdx())
                                                                    .name(productEntity.getName())
                                                                    .price(productEntity.getPrice())
                                                                    .build())
                                            .collect(Collectors.toCollection(ArrayList::new)))
                            .build();
            return ResponseEntity.ok().body(adminProductsPostResponse);
        }catch(BadRequestException e){
            throw new BadRequestException(e.getMessage());
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @MethodInfo(name = "adminProductPost", description = "관리자 상품 조회를 처리합니다.")
    public ResponseEntity<AdminProductPostResponse> adminProductPost(
            AdminProductPostRequest adminProductPostRequest,
            Long productIdx)  throws Exception {
        try{
            //토큰 파싱
            String userId= jwtHandler.parseAccessToken(adminProductPostRequest.getAccessToken());

            //아이디 존재 유효성 검사
            if(!adminService.isExistId(userId)){
                throw new BadRequestException("존재하지 않는 아이디입니다.");
            }

            ProductEntity productEntity = productService.getProduct(productIdx);

            if (productEntity == null) {
                throw new BadRequestException("존재하지 않는 가게입니다.");
            }

            return ResponseEntity.ok(
                    AdminProductPostResponse.builder()
                            .idx(productEntity.getIdx())
                            .name(productEntity.getName())
                            .price(productEntity.getPrice())
                            .build());
        } catch(BadRequestException e){
            throw new BadRequestException(e.getMessage());
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @MethodInfo(name = "adminProductPatch", description = "관리자 상품 수정을 처리합니다.")
    public ResponseEntity<Void> adminProductPatch(
            AdminProductPatchRequest adminProductPatchRequest,
            Long productIdx)  throws Exception {
        try{
            String name = adminProductPatchRequest.getName();
            int price = adminProductPatchRequest.getPrice();

            productService.updateProduct(
                    productIdx,
                    adminProductPatchRequest.getName(),
                    adminProductPatchRequest.getPrice()
            );

            return ResponseEntity.ok().build();

        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @MethodInfo(name = "adminProductDelete", description = "관리자 상품 삭제를 처리합니다.")
    public ResponseEntity<Void> adminProductDelete(Long productIdx)  throws Exception {
        try{
            productService.deleteProduct(productIdx);
            return ResponseEntity.ok().build();
        } catch (IllegalStateException e) {
            throw new IllegalStateException(e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Builder
    public AdminUseCase(AdminService adminService, JwtHandler jwtHandler, UserService userService, StoreService storeService, @Qualifier("productService") ProductService productService) {
        this.adminService = adminService;
        this.jwtHandler = jwtHandler;
        this.userService = userService;
        this.storeService = storeService;
        this.productService = productService;
    }
}
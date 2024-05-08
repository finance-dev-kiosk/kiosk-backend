package finance.dev.domain.repository.jpa;

import finance.dev.common.annotation.TypeInfo;
import finance.dev.domain.entity.StoreEntity;
import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@TypeInfo(name = "StoreRepository", description = "가게 레포지토리 인터페이스")
@EnableJpaRepositories
public interface StoreRepository extends JpaRepository<StoreEntity, Long> {
    ArrayList<StoreEntity> findAll();

    StoreEntity findByIdx(Long storeIdx);

    StoreEntity save(StoreEntity storeEntity);

    default void updateStoreEntityByIdx(StoreEntity storeEntity, Long idx){
        save(storeEntity.builder()
                .name(storeEntity.getName())
                .category(storeEntity.getCategory())
                .address1(storeEntity.getAddress1())
                .address2(storeEntity.getAddress2())
                .address3(storeEntity.getAddress3())
                .tel(storeEntity.getTel())
                .isDelivery(storeEntity.getIsDelivery())
                .isPackaged(storeEntity.getIsPackaged())
                .build()
        );
    };

//    default void update(Long idx, String name,
//                             String category, String address1,String address2,String address3,
//                             String phone, Boolean isDelivery, Boolean isPackaged) {
//        StoreEntity store = findByIdx(idx);
//        save(
//                StoreEntity.builder()
//                        .idx(idx)
//                        .name(name)
//                        .category(category)
//                        .address1(address1)
//                        .address2(address2)
//                        .address3(address3)
//                        .tel(phone)
//                        .isDelivery(isDelivery)
//                        .isPackaged(isPackaged)
//                        .build()
//        );
//    }
}

package finance.dev.domain.service;

import finance.dev.common.annotation.TypeInfo;
import finance.dev.domain.entity.StoreEntity;
import finance.dev.domain.repository.jpa.StoreRepository;
import finance.dev.domain.type.StoreSearchSort;
import finance.dev.domain.type.StoreSearchType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.util.List;
import org.springframework.stereotype.Service;

@TypeInfo(name = "StoreService", description = "가게 서비스 클래스")
@Service
public class StoreService {
    private final EntityManager entityManager;
    private final StoreRepository storeRepository;

    public StoreEntity getStore(Long storeId) {
        return entityManager.find(StoreEntity.class, storeId);
    }

    public List<StoreEntity> getStores(
            String searchValue,
            int searchPageNum,
            int searchPageSize,
            StoreSearchSort userSearchSort,
            StoreSearchType userSearchType) {

        StringBuilder queryString = new StringBuilder("SELECT s FROM StoreEntity s");

        if (userSearchType == StoreSearchType.NAME) {
            queryString.append(" WHERE s.name LIKE '%").append(searchValue).append("%'");
        } else if (userSearchType == StoreSearchType.CATEGORY) {
            queryString.append(" WHERE s.category LIKE '%").append(searchValue).append("%'");
        } else if (userSearchType == StoreSearchType.ENTIRE) {
            queryString.append(" WHERE s.name LIKE '%").append(searchValue).append("%'");
            queryString.append(" OR s.category LIKE '%").append(searchValue).append("%'");
        } else {
            queryString.append(" WHERE s.name LIKE '%").append(searchValue).append("%'");
        }

        if (userSearchSort == StoreSearchSort.IDX_ASC) {
            queryString.append(" ORDER BY s.idx ASC");
        } else if (userSearchSort == StoreSearchSort.IDX_DESC) {
            queryString.append(" ORDER BY s.idx DESC");
        } else if (userSearchSort == StoreSearchSort.NAME_ASC) {
            queryString.append(" ORDER BY s.name ASC");
        } else if (userSearchSort == StoreSearchSort.NAME_DESC) {
            queryString.append(" ORDER BY s.name DESC");
        } else {
            queryString.append(" ORDER BY s.idx ASC");
        }

        Query query = entityManager.createQuery(queryString.toString());
        query.setFirstResult(searchPageNum * searchPageSize);
        query.setMaxResults(searchPageSize);

        return query.getResultList();
    }

    public List<StoreEntity> getStoresList(
            String searchValue,
            int searchPageNum,
            int searchPageSize,
            StoreSearchSort storeSearchSort,
            StoreSearchType storeSearchType) {

        StringBuilder queryString = new StringBuilder("SELECT s FROM StoreEntity s");

        if (storeSearchType == StoreSearchType.NAME) {
            queryString.append(" WHERE s.name LIKE '%").append(searchValue).append("%'");
        } else if (storeSearchType == StoreSearchType.CATEGORY) {
            queryString.append(" WHERE s.category LIKE '%").append(searchValue).append("%'");
        } else if (storeSearchType == StoreSearchType.ENTIRE) {
            queryString.append(" WHERE s.name LIKE '%").append(searchValue).append("%'");
            queryString.append(" OR s.category LIKE '%").append(searchValue).append("%'");
        } else {
            queryString.append(" WHERE s.name LIKE '%").append(searchValue).append("%'");
        }

        if (storeSearchSort == StoreSearchSort.IDX_ASC) {
            queryString.append(" ORDER BY s.idx ASC");
        } else if (storeSearchSort == StoreSearchSort.IDX_DESC) {
            queryString.append(" ORDER BY s.idx DESC");
        } else if (storeSearchSort == StoreSearchSort.NAME_ASC) {
            queryString.append(" ORDER BY s.name ASC");
        } else if (storeSearchSort == StoreSearchSort.NAME_DESC) {
            queryString.append(" ORDER BY s.name DESC");
        } else {
            queryString.append(" ORDER BY s.idx ASC");
        }

        Query query = entityManager.createQuery(queryString.toString());

        return query.getResultList();
    }

    public void updateStore(Long storeIdx, String name, String category,
                             String address1,String address2,String address3,
                            String phone,Boolean isDelivery, Boolean isPackaged){
        StoreEntity store = storeRepository.findByIdx(storeIdx);
       storeRepository.updateStoreEntityByIdx(store,storeIdx);
//        storeRepository.update(storeIdx, name, category,
//                address1,address2, address3,
//                phone, isDelivery, isPackaged);
    }

    public void deleteStore(Long storeIdx){
        storeRepository.deleteById(storeIdx);
    }

    public StoreService(EntityManager entityManager, StoreRepository storeRepository) {
        this.entityManager = entityManager;
        this.storeRepository = storeRepository;
    }
}

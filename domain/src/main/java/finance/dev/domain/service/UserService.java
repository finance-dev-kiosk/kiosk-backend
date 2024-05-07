package finance.dev.domain.service;

import finance.dev.common.annotation.TypeInfo;
import finance.dev.domain.entity.StoreEntity;
import finance.dev.domain.entity.UserEntity;
import finance.dev.domain.repository.jpa.UserRepository;
import finance.dev.domain.type.StoreSearchSort;
import finance.dev.domain.type.StoreSearchType;
import finance.dev.domain.type.UserSearchSort;
import finance.dev.domain.type.UserSearchType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

@TypeInfo(name = "UserService", description = "회원 서비스 클래스")
@Service
public class UserService {
    private final UserRepository userRepository;
    private final EntityManager entityManager;
    private final UserEntity userEntity;

    public boolean login(String userId, String password) {
        return userRepository.findByIdAndPassword(userId, password) != null;
    }

    public boolean isExistId(String userId) {
        return userRepository.findById(userId) != null;
    }

    public boolean isExistName(String name) {
        return userRepository.findByName(name) != null;
    }

    public boolean isExistEmail(String email) {
        return userRepository.findByEmail(email) != null;
    }

    public String getUserName(Long userIdx) {
        return userRepository.findByIdx(userIdx).getName();
    }

    public String getUserId(Long userIdx) {
        return userRepository.findByIdx(userIdx).getId();
    }

    public Long getUserIdxById(String userId) {
        return userRepository.findById(userId).getIdx();
    }

    public void register(String userId, String password, String name, String email) {
        userRepository.save(
                UserEntity.builder().id(userId).password(password).name(name).email(email).build());
    }

    public void updateUser(Long userIdx, String name){
        userRepository.update(userIdx, name);
    }

    public void deleteUser(Long userIdx){
//        userRepository.deleteByIdx(userIdx);
        userRepository.deleteById(userIdx);
    }

    public UserEntity getUser(Long userIdx){
        return entityManager.find(UserEntity.class, userIdx);
    }

    //admin에서 search
    public List<UserEntity> searchUsers(
            UserSearchType userSearchType,
            String searchValue,
            int searchPageNum,
            int searchPageSize,
            UserSearchSort userSearchSort
    ){
        StringBuilder queryString = new StringBuilder("SELECT u FROM UserEntity u");

        if (userSearchType == UserSearchType.NAME) {
            queryString.append(" WHERE u.name LIKE '%").append(searchValue).append("%'");
        } else if (userSearchType == UserSearchType.ID) {
            queryString.append(" WHERE u.id LIKE '%").append(searchValue).append("%'");
        } else if(userSearchType == UserSearchType.EMAIL) {
            queryString.append(" WHERE u.email LIKE '%").append(searchValue).append("%'");
        } else if (userSearchType == UserSearchType.ENTIRE) {
            queryString.append(" WHERE u.name LIKE '%").append(searchValue).append("%'");
            queryString.append(" OR u.id LIKE '%").append(searchValue).append("%'");
            queryString.append(" OR u.email LIKE '%").append(searchValue).append("%'");
        } else {
            queryString.append(" WHERE u.id LIKE '%").append(searchValue).append("%'");
        }

        if (userSearchSort == UserSearchSort.IDX_ASC) {
            queryString.append(" ORDER BY u.idx ASC");
        } else if (userSearchSort == UserSearchSort.IDX_DESC) {
            queryString.append(" ORDER BY u.idx DESC");
        } else if(userSearchSort == UserSearchSort.ID_ASC) {
            queryString.append(" ORDER BY u.id ASC");
        } else if(userSearchSort == UserSearchSort.ID_DESC) {
            queryString.append(" ORDER BY u.id DESC");
        } else if(userSearchSort == UserSearchSort.NAME_ASC) {
            queryString.append(" ORDER BY u.name ASC");
        } else if(userSearchSort == UserSearchSort.NAME_DESC) {
            queryString.append(" ORDER BY u.name DESC");
        } else if(userSearchSort == UserSearchSort.CREATED_ASC) {
            queryString.append(" ORDER BY u.created ASC");
        } else if(userSearchSort == UserSearchSort.CREATED_DESC) {
            queryString.append(" ORDER BY u.created DESC");
        } else {
            queryString.append(" ORDER BY u.id ASC");
        }
        Query query = entityManager.createQuery(queryString.toString());

        return query.getResultList();
    }



    //admin에서 search
    public List<StoreEntity> searchStores(
            StoreSearchType storeSearchType,
            String searchValue,
            int searchPageNum,
            int searchPageSize,
            StoreSearchSort storeSearchSort
    ){
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
        } else if(storeSearchSort == StoreSearchSort.NAME_ASC) {
            queryString.append(" ORDER BY s.name ASC");
        } else if(storeSearchSort == StoreSearchSort.NAME_DESC) {
            queryString.append(" ORDER BY s.name DESC");
        } else {
            queryString.append(" ORDER BY s.idx ASC");
        }
        Query query = entityManager.createQuery(queryString.toString());

        return query.getResultList();
    }

    public UserService(UserRepository userRepository, EntityManager entityManager, UserEntity userEntity) {
        this.userRepository = userRepository;
        this.entityManager = entityManager;
        this.userEntity = userEntity;
    }
}
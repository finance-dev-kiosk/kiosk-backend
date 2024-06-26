package finance.dev.domain.service;

import finance.dev.common.annotation.TypeInfo;
import finance.dev.domain.entity.ProductEntity;
import finance.dev.domain.entity.StoreEntity;
import finance.dev.domain.repository.jpa.ProductRepository;
import finance.dev.domain.type.ProductSearchSort;
import finance.dev.domain.type.StoreSearchSort;
import finance.dev.domain.type.StoreSearchType;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import jakarta.persistence.Query;
import org.springframework.stereotype.Service;

@TypeInfo(name = "ProductService", description = "상품 서비스 클래스")
@Service
public class ProductService {
    private final EntityManager entityManager;
    private final ProductRepository productRepository;

    public List<ProductEntity> getStoreProducts(Long storeId) {
        StringBuilder queryString =
                new StringBuilder("SELECT p FROM ProductEntity p WHERE p.storeIdx = ");
        queryString.append(storeId);

        return entityManager
                .createQuery(queryString.toString(), ProductEntity.class)
                .getResultList();
    }
    public ProductEntity getProduct(Long productId){
        return entityManager.find(ProductEntity.class, productId);
    }

    public void updateProduct(Long productIdx, String name, int price){
        Optional<ProductEntity> optionalStoreEntity = productRepository.findById(productIdx);
        ProductEntity productEntity = optionalStoreEntity.get();
        productEntity.setName(name);
        productEntity.setPrice(price);
        productRepository.save(productEntity);
    }

    public void deleteProduct(Long productIdx){
        productRepository.deleteById(productIdx);
    }

    public List<ProductEntity> searchProducts(
            String searchValue,
            int searchPageNum,
            int searchPageSize,
            ProductSearchSort productSearchSort
    ) {
        StringBuilder queryString = new StringBuilder("SELECT p FROM ProductEntity p");
        queryString.append(" WHERE p.name LIKE '%").append(searchValue).append("%'");

        if (productSearchSort == ProductSearchSort.IDX_ASC) {
            queryString.append(" ORDER BY p.idx ASC");
        } else if (productSearchSort == ProductSearchSort.IDX_DESC) {
            queryString.append(" ORDER BY p.idx DESC");
        } else if (productSearchSort == ProductSearchSort.NAME_ASC) {
            queryString.append(" ORDER BY p.name ASC");
        } else if (productSearchSort == ProductSearchSort.NAME_DESC) {
            queryString.append(" ORDER BY p.name DESC");
        } else if (productSearchSort == ProductSearchSort.PRICE_ASC) {
            queryString.append(" ORDER BY p.price ASC");
        } else if (productSearchSort == ProductSearchSort.PRICE_DESC) {
            queryString.append(" ORDER BY p.price DESC");
        } else {
            queryString.append(" ORDER BY p.idx ASC");
        }

        Query query = entityManager.createQuery(queryString.toString());

        return query.getResultList();
    }


    public ProductService(EntityManager entityManager, ProductRepository productRepository) {
        this.entityManager = entityManager;
        this.productRepository = productRepository;
    }
}

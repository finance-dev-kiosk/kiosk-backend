package finance.dev.domain.service;

import finance.dev.common.annotation.TypeInfo;
import finance.dev.domain.repository.jpa.ProductRepository;
import org.springframework.stereotype.Service;

@TypeInfo(name = "ProductService", description = "상품 서비스 클래스")
@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
}

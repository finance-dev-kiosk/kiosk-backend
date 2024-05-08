package finance.dev.domain.entity;

import finance.dev.common.annotation.TypeInfo;
import jakarta.persistence.*;
import lombok.*;

@TypeInfo(name = "ProductEntity", description = "상품 엔티티 클래스")
@Table(name = "tbl_product")
@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_idx")
    private Long idx; // 상품 식별자

    @Column(name = "product_name", nullable = false)
    private String name; // 상품 이름

    @Column(name = "product_price", nullable = false)
    private int price; // 상품 가격

    @Column(name = "store_idx", nullable = false)
    private Long storeIdx; // 가게 식별자

    @Builder
    public ProductEntity(Long idx, String name, int price, Long storeIdx) {
        this.idx = idx;
        this.name = name;
        this.price = price;
        this.storeIdx = storeIdx;
    }
}

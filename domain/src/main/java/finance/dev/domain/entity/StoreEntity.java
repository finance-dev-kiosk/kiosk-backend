package finance.dev.domain.entity;

import finance.dev.common.annotation.TypeInfo;
import jakarta.persistence.*;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@TypeInfo(name = "StoreEntity", description = "가게 엔티티 클래스")
@Table(name = "tbl_store")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StoreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_idx")
    private Long idx; // 가게 식별자

    @Column(name = "store_name", nullable = false)
    private String name; // 가게 이름

    @Column(name = "store_tel")
    private String tel; // 가게 연락처

    @Column(name = "store_address_1", nullable = false)
    private String address1; // 가게 주소1

    @Column(name = "store_address_2", nullable = false)
    private String address2; // 가게 주소2

    @Column(name = "store_address_3")
    private String address3; // 가게 주소3

    @Column(name = "store_category", nullable = false)
    private String category; // 가게 카테고리

    @Column(name = "store_is_packaged")
    private boolean isPackaged; // 포장 가능 여부

    @Column(name = "store_is_delivery")
    private boolean isDelivery; // 배달 가능 여부

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_idx")
    private List<ReviewEntity> reviews; // 가게 리뷰 목록

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_idx")
    private List<CommentEntity> comments; // 가게 댓글 목록

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_idx")
    private List<ProductEntity> products; // 가게 상품 목록

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_idx")
    private List<PeriodEntity> periods; // 가게 운영 시간 목록

    @Builder
    public StoreEntity(
            String name,
            String tel,
            String address1,
            String address2,
            String address3,
            String category,
            boolean isPackaged,
            boolean isDelivery) {
        this.name = name;
        this.tel = tel;
        this.address1 = address1;
        this.address2 = address2;
        this.address3 = address3;
        this.category = category;
        this.isPackaged = isPackaged;
        this.isDelivery = isDelivery;
    }
}

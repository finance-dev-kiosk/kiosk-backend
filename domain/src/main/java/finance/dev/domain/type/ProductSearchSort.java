package finance.dev.domain.type;

import lombok.Getter;

@Getter
public enum ProductSearchSort {
    IDX_ASC("식별자 오름차순", "product_idx", "ASC"),
    IDX_DESC("식별자 내림차순", "product_idx", "DESC"),
    NAME_ASC("상품명 오름차순", "product_name", "ASC"),
    NAME_DESC("상품명 내림차순", "product_name", "DESC"),
    PRICE_ASC("가격 오름차순", "product_price", "ASC"),
    PRICE_DESC("가격 내림차순", "product_price", "DESC");

    private final String name;
    private final String field;
    private final String direction;

    ProductSearchSort(String name, String field, String direction) {
        this.name = name;
        this.field = field;
        this.direction = direction;
    }
}

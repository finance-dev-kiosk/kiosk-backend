package finance.dev.domain.type;

import lombok.Getter;

@Getter
public enum StoreSearchSort {
    IDX_ASC("식별자 오름차순", "store_idx", "ASC"),
    IDX_DESC("식별자 내림차순", "store_idx", "DESC"),
    NAME_ASC("가게명 오름차순", "store_name", "ASC"),
    NAME_DESC("가게명 내림차순", "store_name", "DESC");

    private final String name;
    private final String field;
    private final String direction;

    StoreSearchSort(String name, String field, String direction) {
        this.name = name;
        this.field = field;
        this.direction = direction;
    }
}

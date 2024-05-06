package finance.dev.domain.type;

import lombok.Getter;

@Getter
public enum StoreSearchType {
    ENTIRE("전체"),
    NAME("이름"),
    CATEGORY("카테고리");

    private final String description;

    StoreSearchType(String description) {
        this.description = description;
    }
}

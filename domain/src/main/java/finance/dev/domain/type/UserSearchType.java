package finance.dev.domain.type;

import lombok.Getter;

@Getter
public enum UserSearchType {
    ENTIRE("전체"),
    ID("아이디"),
    NAME("이름"),
    EMAIL("이메일");

    private final String value;

    UserSearchType(String value) {
        this.value = value;
    }
}

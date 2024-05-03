package finance.dev.domain.type;

import lombok.Getter;

@Getter
public enum UserSearchSort {
    IDX_ASC("식별자 오름차순", "user_idx", "ASC"),
    IDX_DESC("식별자 내림차순", "user_idx", "DESC"),
    ID_ASC("아이디 오름차순", "user_id", "ASC"),
    ID_DESC("아이디 내림차순", "user_id", "DESC"),
    NAME_ASC("닉네임 오름차순", "user_name", "ASC"),
    NAME_DESC("닉네임 내림차순", "user_name", "DESC"),
    CREATED_ASC("가입일 오름차순", "user_created", "ASC"),
    CREATED_DESC("가입일 내림차순", "user_created", "DESC");

    private final String name;
    private final String field;
    private final String direction;

    UserSearchSort(String name, String field, String direction) {
        this.name = name;
        this.field = field;
        this.direction = direction;
    }
}

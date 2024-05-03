package finance.dev.api.dto.admin;

import java.util.ArrayList;
import lombok.Getter;

@Getter
public class AdminUsersPostResponse {
    private int userCount; // 유저 수
    private int pageCount; // 페이지 수
    private ArrayList<AdminUserPost> users; // 유저 정보 리스트

    public AdminUsersPostResponse(int userCount, int pageCount, ArrayList<AdminUserPost> users) {
        this.userCount = userCount;
        this.pageCount = pageCount;
        this.users = users;
    }
}

class AdminUserPost {
    public AdminUserPost(Long idx, String id, String name, String email) {}
}

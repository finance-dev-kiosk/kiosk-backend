package finance.dev.api.dto.user;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserReviewPost {
    private Long idx;
    private String name;
    private String content;
    private String date;
    private String image;
    private String blogName;
    private String url;

    @Builder
    public UserReviewPost(
            Long idx,
            String name,
            String content,
            String date,
            String image,
            String blogName,
            String url) {
        this.idx = idx;
        this.name = name;
        this.content = content;
        this.date = date;
        this.image = image;
        this.blogName = blogName;
        this.url = url;
    }
}

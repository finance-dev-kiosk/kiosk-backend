package finance.dev.domain.entity;

import finance.dev.common.annotation.TypeInfo;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@TypeInfo(name = "ReviewEntity", description = "리뷰 엔티티 클래스")
@Table(name = "tbl_review")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_idx")
    private Long idx; // 리뷰 식별자

    @Column(name = "review_title")
    private String title; // 리뷰 제목

    @Column(name = "review_image")
    private String image; // 리뷰 이미지

    @Column(name = "review_content")
    @Lob
    private String content; // 리뷰 내용

    @Column(name = "review_url")
    private String url; // 리뷰 URL

    @Column(name = "review_blog_name")
    private String blogName; // 리뷰 블로그 이름

    @Column(name = "review_date")
    private String date; // 리뷰 작성일

    @Column(name = "store_idx")
    private Long storeIdx; // 가게 식별자

    @Builder
    public ReviewEntity(
            String title,
            String image,
            String content,
            String url,
            String blogName,
            String date,
            Long storeIdx) {
        this.title = title;
        this.image = image;
        this.content = content;
        this.url = url;
        this.blogName = blogName;
        this.date = date;
        this.storeIdx = storeIdx;
    }
}

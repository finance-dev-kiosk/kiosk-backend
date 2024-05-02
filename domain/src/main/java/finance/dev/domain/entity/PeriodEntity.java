package finance.dev.domain.entity;

import finance.dev.common.annotation.TypeInfo;
import finance.dev.domain.type.PeriodTime;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@TypeInfo(name = "PeriodEntity", description = "운영 시간 엔티티 클래스")
@Table(name = "tbl_period")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PeriodEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "period_idx")
    private Long idx; // 운영 시간 식별자

    @Column(name = "period_type")
    private PeriodTime periodTime; // 운영 시간 타입

    @Column(name = "period_day")
    private String day; // 운영 요일

    @Column(name = "period_time")
    private String time; // 운영 시간

    @Column(name = "store_idx")
    private Long storeIdx; // 가게 식별자

    public PeriodEntity(Long idx) {
        this.idx = idx;
    }
}

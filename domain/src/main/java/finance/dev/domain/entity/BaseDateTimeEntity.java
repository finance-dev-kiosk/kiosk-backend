package finance.dev.domain.entity;

import finance.dev.common.annotation.TypeInfo;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@TypeInfo(name = "BaseDateTimeEntity", description = "시간 정보 엔티티 클래스")
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseDateTimeEntity {
    @Column(name = "baseDateTime_created", updatable = false)
    @CreatedDate
    private LocalDateTime created; // 생성 시간

    @Column(name = "baseDateTime_lastModified")
    @LastModifiedDate
    private LocalDateTime lastModified; // 수정 시간
}

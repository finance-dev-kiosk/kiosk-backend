package finance.dev.domain.entity;

import finance.dev.common.annotation.TypeInfo;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

@TypeInfo(name = "AuthMailEntity", description = "인증 메일 엔티티 클래스")
@RedisHash(value = "auth_mail")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AuthMailEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id; // 인증 메일 식별자

    @Indexed private String email; // 이메일 주소

    @Indexed private String code; // 인증 코드

    @TimeToLive private Long ttl; // 만료 시간

    @Builder
    public AuthMailEntity(String email, String code, Long ttl) {
        this.email = email;
        this.code = code;
        this.ttl = ttl;
    }
}

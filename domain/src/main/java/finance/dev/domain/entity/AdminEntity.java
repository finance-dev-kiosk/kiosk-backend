package finance.dev.domain.entity;

import finance.dev.common.annotation.TypeInfo;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@TypeInfo(name = "AdminEntity", description = "관리자 엔티티 클래스")
@Table(name = "tbl_admin")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AdminEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_idx")
    private Long idx; // 관리자 식별자

    @Column(name = "admin_id", nullable = false)
    private String id; // 관리자 아이디

    @Column(name = "admin_password", nullable = false)
    private String password; // 관리자 비밀번호

    @Column(name = "admin_name", nullable = false)
    private String name; // 관리자 닉네임

    @Column(name = "admin_email", nullable = false)
    private String email; // 관리자 이메일

    @Column(name = "admin_otp", nullable = false)
    private String otp; // 관리자 OTP

    @Builder
    public AdminEntity(String id, String password, String name, String email, String otp) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.email = email;
        this.otp = otp;
    }
}

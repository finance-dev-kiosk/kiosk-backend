package finance.dev.infra.properties;

import finance.dev.common.annotation.MethodInfo;
import finance.dev.common.annotation.TypeInfo;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@TypeInfo(name = "JwtProperties", description = "JWT 프로퍼티 클래스")
@Getter
@ConfigurationProperties(prefix = "custom.jwt")
public class JwtProperties {
    private String secret;
    private Long accessExp;
    private Long refreshExp;
    private String prefix;
    private String header;

    @MethodInfo(name = "setSecret", description = "JWT 시크릿 값을 설정합니다.")
    public void setSecret(String secret) {
        this.secret = secret;
    }

    @MethodInfo(name = "setAccessExp", description = "JWT Access Token 만료 시간을 설정합니다.")
    public void setAccessExp(Long accessExp) {
        this.accessExp = accessExp;
    }

    @MethodInfo(name = "setRefreshExp", description = "JWT Refresh Token 만료 시간을 설정합니다.")
    public void setRefreshExp(Long refreshExp) {
        this.refreshExp = refreshExp;
    }

    @MethodInfo(name = "setPrefix", description = "JWT Prefix 값을 설정합니다.")
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    @MethodInfo(name = "setHeader", description = "JWT Header 값을 설정합니다.")
    public void setHeader(String header) {
        this.header = header;
    }
}

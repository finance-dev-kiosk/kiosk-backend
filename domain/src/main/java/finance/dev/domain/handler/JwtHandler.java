package finance.dev.domain.handler;

import finance.dev.common.annotation.MethodInfo;
import finance.dev.common.annotation.TypeInfo;
import finance.dev.infra.properties.JwtProperties;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@TypeInfo(name = "JwtHandler", description = "JWT 핸들러 클래스")
@Component
public class JwtHandler {
    private final JwtProperties jwtProperties;

    @MethodInfo(name = "generateAccessToken", description = "JWT AccessToken을 생성합니다.")
    public String generateAccessToken(String id) {
        final Date issuedAt = new Date();
        final Date accessTokenExpiresIn =
                new Date(issuedAt.getTime() + jwtProperties.getAccessExp() * 1000);

        return buildAccessToken(id, issuedAt, accessTokenExpiresIn, "ACCESS");
    }

    @MethodInfo(name = "generateRefreshToken", description = "JWT RefreshToken을 생성합니다.")
    public String generateRefreshToken(String id) {
        final Date issuedAt = new Date();
        final Date refreshTokenExpiresIn =
                new Date(issuedAt.getTime() + jwtProperties.getRefreshExp() * 1000);

        return buildAccessToken(id, issuedAt, refreshTokenExpiresIn, "REFRESH");
    }

    @MethodInfo(name = "parseAccessToken", description = "JWT AccessToken을 파싱합니다.")
    public String parseAccessToken(String token) {
        try {
            if (parseToken(token).getBody().getExpiration().before(new Date())) {
                throw new IllegalStateException("토큰이 만료되었습니다.");
            }

            return parseToken(token).getBody().getSubject();
        } catch (ExpiredJwtException e) {
            throw new IllegalStateException(e.getMessage());
        }catch (Exception e) {
            throw new IllegalStateException("토큰이 올바르지 않습니다.");
        }
    }

    @MethodInfo(name = "parseRefreshToken", description = "JWT RefreshToken을 파싱합니다.")
    public String parseRefreshToken(String token) {
        return validToken(token);
    }

    @MethodInfo(name = "buildToken", description = "JWT Token을 빌드합니다.")
    private String buildAccessToken(String id, Date issuedAt, Date tokenExpiresIn, String type) {
        return Jwts.builder()
                .setSubject(id)
                .setIssuedAt(issuedAt)
                .setExpiration(tokenExpiresIn)
                .claim("type", type)
                .signWith(getSecretKey())
                .compact();
    }

    @MethodInfo(name = "parseToken", description = "JWT Token을 파싱합니다.")
    private Jws<Claims> parseToken(String token) {
        return Jwts.parserBuilder().setSigningKey(getSecretKey()).build().parseClaimsJws(token);
    }

    @MethodInfo(name = "validToken", description = "JWT Token을 유효성 검사합니다.")
    private String validToken(String token) {
        if (parseToken(token).getBody().get("type") != "REFRESH") {
            throw new IllegalStateException("토큰이 올바르지 않습니다.");
        }
        if (parseToken(token).getBody().getExpiration().before(new Date())) {
            throw new IllegalStateException("토큰이 만료되었습니다.");
        }

        return parseToken(token).getBody().getSubject();
    }

    @MethodInfo(name = "getSecretKey", description = "JWT Secret Key를 생성합니다.")
    private Key getSecretKey() {
        return Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes());
    }

    public JwtHandler(@Qualifier("jwtProperties") JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }
}

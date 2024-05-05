package finance.dev.domain.service;

import finance.dev.common.annotation.MethodInfo;
import finance.dev.common.annotation.TypeInfo;
import finance.dev.domain.entity.AuthMailEntity;
import finance.dev.domain.repository.redis.AuthMailRepository;
import org.springframework.stereotype.Service;

@TypeInfo(name = "AuthMailService", description = "인증 메일 서비스 클래스")
@Service
public class AuthMailService {
    private final AuthMailRepository authMailRepository;

    @MethodInfo(name = "save", description = "인증 메일 정보를 저장합니다.")
    public void save(AuthMailEntity authMail) {
        authMailRepository.save(authMail);
        authMailRepository.existsAuthMailByEmail(authMail.getEmail());
    }

    @MethodInfo(name = "update", description = "인증 메일 정보를 갱신합니다.")
    public void update(AuthMailEntity authMail1, AuthMailEntity authMail2) {
        authMailRepository.delete(authMail1);
        authMailRepository.save(authMail2);
    }

    @MethodInfo(name = "deleteByEmail", description = "인증 이메일 정보를 삭제합니다.")
    public void deleteByEmail(String email) {
        authMailRepository.deleteByEmail(email);
    }

    @MethodInfo(name = "existsAuthMailByEmail", description = "이메일로 인증 이메일 정보가 존재하는지 확인합니다.")
    public Boolean existsAuthMailByEmail(String email) {
        return authMailRepository.existsAuthMailByEmail(email);
    }

    @MethodInfo(name = "findAuthMailByEmail", description = "이메일로 인증 이메일 정보를 탐색합니다.")
    public AuthMailEntity findAuthMailByEmail(String email) {
        return authMailRepository.findAuthMailByEmail(email);
    }

    @MethodInfo(name = "findAuthMailByAuthKey", description = "인증 키로 인증 이메일 정보를 탐색합니다.")
    public AuthMailEntity findAuthMailByCode(String authKey) {
        return authMailRepository.findAuthMailCode(authKey);
    }

    public AuthMailService(AuthMailRepository authMailRepository) {
        this.authMailRepository = authMailRepository;
    }
}

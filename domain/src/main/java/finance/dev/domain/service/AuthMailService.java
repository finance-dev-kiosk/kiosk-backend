package finance.dev.domain.service;

import finance.dev.common.annotation.TypeInfo;
import finance.dev.domain.repository.redis.AuthMailRepository;
import org.springframework.stereotype.Service;

@TypeInfo(name = "AuthMailService", description = "인증 메일 서비스 클래스")
@Service
public class AuthMailService {
    private final AuthMailRepository authMailRepository;

    public AuthMailService(AuthMailRepository authMailRepository) {
        this.authMailRepository = authMailRepository;
    }
}

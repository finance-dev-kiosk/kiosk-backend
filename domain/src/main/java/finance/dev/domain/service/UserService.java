package finance.dev.domain.service;

import finance.dev.common.annotation.TypeInfo;
import finance.dev.domain.repository.jpa.UserRepository;
import org.springframework.stereotype.Service;

@TypeInfo(name = "UserService", description = "회원 서비스 클래스")
@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
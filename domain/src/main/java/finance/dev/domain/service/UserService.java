package finance.dev.domain.service;

import finance.dev.common.annotation.TypeInfo;
import finance.dev.domain.entity.UserEntity;
import finance.dev.domain.repository.jpa.UserRepository;
import org.springframework.stereotype.Service;

@TypeInfo(name = "UserService", description = "회원 서비스 클래스")
@Service
public class UserService {
    private final UserRepository userRepository;

    public boolean login(String userId, String password) {
        return userRepository.findByIdAndPassword(userId, password) != null;
    }

    public boolean isExistId(String userId) {
        return userRepository.findById(userId) != null;
    }

    public boolean isExistName(String name) {
        return userRepository.findByName(name) != null;
    }

    public boolean isExistEmail(String email) {
        return userRepository.findByEmail(email) != null;
    }


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}

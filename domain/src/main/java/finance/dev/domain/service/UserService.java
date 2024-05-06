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

    public String getUserName(Long userIdx) {
        return userRepository.findByIdx(userIdx).getName();
    }

    public String getUserId(Long userIdx) {
        return userRepository.findByIdx(userIdx).getId();
    }

    public Long getUserIdxById(String userId) {
        return userRepository.findById(userId).getIdx();
    }

    public void register(String userId, String password, String name, String email) {
        userRepository.save(
                UserEntity.builder().id(userId).password(password).name(name).email(email).build());
    }

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}

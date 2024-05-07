package finance.dev.domain.service;

import finance.dev.common.annotation.TypeInfo;
import finance.dev.domain.entity.AdminEntity;
import finance.dev.domain.repository.jpa.AdminRepository;
import finance.dev.domain.repository.jpa.UserRepository;
import org.springframework.stereotype.Service;


@TypeInfo(name = "AdminService", description = "관리자 서비스 클래스")
@Service
public class AdminService {
    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository, UserRepository userRepository) {
        this.adminRepository = adminRepository;
    }

    public AdminEntity findByUserId(String adminId){
        return adminRepository.findById(adminId);
    }

    public boolean isExistId(String userId) {
        return adminRepository.findById(userId) != null;
    }

}

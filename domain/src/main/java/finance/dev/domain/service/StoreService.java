package finance.dev.domain.service;

import finance.dev.common.annotation.TypeInfo;
import finance.dev.domain.repository.jpa.StoreRepository;
import org.springframework.stereotype.Service;

@TypeInfo(name = "StoreService", description = "가게 서비스 클래스")
@Service
public class StoreService {
    private final StoreRepository storeRepository;

    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }
}

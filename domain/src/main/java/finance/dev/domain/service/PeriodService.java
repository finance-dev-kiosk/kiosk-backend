package finance.dev.domain.service;

import finance.dev.common.annotation.TypeInfo;
import finance.dev.domain.entity.PeriodEntity;
import finance.dev.domain.repository.jpa.PeriodRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@TypeInfo(name = "PeriodService", description = "가게 운영시간 서비스 클래스")
@Service
public class PeriodService {
    private final PeriodRepository periodRepository;

    public List<PeriodEntity> findAll() {
        return periodRepository.findAll();
    }

    public PeriodService(PeriodRepository periodRepository) {
        this.periodRepository = periodRepository;
    }
}

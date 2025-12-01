package by.pasha.arm_manager.service;

import by.pasha.arm_manager.entity.Client;
import by.pasha.arm_manager.entity.CreditApplication;
import by.pasha.arm_manager.entity.CreditContract;
import by.pasha.arm_manager.repository.CreditApplicationRepository;
import by.pasha.arm_manager.repository.CreditContractRepository;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
@Transactional
public class CreditApplicationService {

    private final CreditApplicationRepository applicationRepository;
    private final CreditContractRepository contractRepository;
    private final Random random = new Random();

    public CreditApplicationService(CreditApplicationRepository applicationRepository,
                                    CreditContractRepository contractRepository) {
        this.applicationRepository = applicationRepository;
        this.contractRepository = contractRepository;
    }

    public CreditApplication createApplication(Client client, BigDecimal desiredAmount) {
        CreditApplication application = new CreditApplication(client, desiredAmount);
        applicationRepository.save(application);
        return application;
    }

    public void makeDecision(Long applicationId) {
        CreditApplication application = applicationRepository.findById(applicationId);
        if (application != null && "PENDING".equals(application.getStatus())) {
            // Случайное решение: 70% шанс одобрения
            boolean isApproved = random.nextDouble() < 0.7;

            if (isApproved) {
                application.setStatus("APPROVED");
                // Одобренная сумма: от 50% до 100% от запрошенной
                BigDecimal approvedAmount = application.getDesiredAmount()
                        .multiply(BigDecimal.valueOf(0.5 + random.nextDouble() * 0.5))
                        .setScale(2, RoundingMode.HALF_UP);
                application.setApprovedAmount(approvedAmount);

                // Срок: от 1 до 12 месяцев (в днях)
                int termMonths = 1 + random.nextInt(12);
                application.setApprovedTermDays(termMonths * 30);

                // Создаем кредитный договор
                CreditContract contract = new CreditContract(application);
                contractRepository.save(contract);
                application.setCreditContract(contract);
            } else {
                application.setStatus("REJECTED");
                application.setApprovedAmount(BigDecimal.ZERO);
                application.setApprovedTermDays(0);
            }

            application.setDecisionDate(LocalDateTime.now());
            applicationRepository.save(application);
        }
    }

    @Transactional(readOnly = true)
    public List<CreditApplication> getAllApplications() {
        return applicationRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<CreditApplication> getApprovedApplications() {
        return applicationRepository.findApprovedApplications();
    }

    @Transactional(readOnly = true)
    public CreditApplication getApplicationById(Long id) {
        return applicationRepository.findById(id);
    }
}

package by.pasha.arm_manager.service;

import by.pasha.arm_manager.entity.CreditContract;
import by.pasha.arm_manager.repository.CreditContractRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Transactional
@Service
public class CreditContractService {
    private final CreditContractRepository contractRepository;

    public CreditContractService(CreditContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    public void signContract(Long contractId) {
        CreditContract contract = contractRepository.findById(contractId);
        if (contract != null && !contract.getIsSigned()) {
            contract.setIsSigned(true);
            contract.setSignatureDate(LocalDateTime.now());
            contractRepository.save(contract);
        }
    }

    @Transactional(readOnly = true)
    public List<CreditContract> getAllContracts() {
        return contractRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<CreditContract> getSignedContracts() {
        return contractRepository.findSignedContracts();
    }

    @Transactional(readOnly = true)
    public CreditContract getContractById(Long id) {
        return contractRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public CreditContract getContractByApplicationId(Long applicationId) {
        return contractRepository.findByApplicationId(applicationId);
    }
}

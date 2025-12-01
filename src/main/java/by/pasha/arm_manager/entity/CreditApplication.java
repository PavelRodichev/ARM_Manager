package by.pasha.arm_manager.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "credit_applications")
public class CreditApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @Column(name = "desired_amount", nullable = false)
    private BigDecimal desiredAmount;

    @Column(name = "application_date", nullable = false)
    private LocalDateTime applicationDate;

    @Column(name = "status")
    private String status; // PENDING, APPROVED, REJECTED

    @Column(name = "approved_amount")
    private BigDecimal approvedAmount;

    @Column(name = "approved_term_days")
    private Integer approvedTermDays;

    @Column(name = "decision_date")
    private LocalDateTime decisionDate;

    @OneToOne(mappedBy = "creditApplication", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private CreditContract creditContract;

    // Конструкторы
    public CreditApplication() {
    }

    public CreditApplication(Client client, BigDecimal desiredAmount) {
        this.client = client;
        this.desiredAmount = desiredAmount;
        this.applicationDate = LocalDateTime.now();
        this.status = "PENDING";
    }

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public BigDecimal getDesiredAmount() {
        return desiredAmount;
    }

    public void setDesiredAmount(BigDecimal desiredAmount) {
        this.desiredAmount = desiredAmount;
    }

    public LocalDateTime getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(LocalDateTime applicationDate) {
        this.applicationDate = applicationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getApprovedAmount() {
        return approvedAmount;
    }

    public void setApprovedAmount(BigDecimal approvedAmount) {
        this.approvedAmount = approvedAmount;
    }

    public Integer getApprovedTermDays() {
        return approvedTermDays;
    }

    public void setApprovedTermDays(Integer approvedTermDays) {
        this.approvedTermDays = approvedTermDays;
    }

    public LocalDateTime getDecisionDate() {
        return decisionDate;
    }

    public void setDecisionDate(LocalDateTime decisionDate) {
        this.decisionDate = decisionDate;
    }

    public CreditContract getCreditContract() {
        return creditContract;
    }

    public void setCreditContract(CreditContract creditContract) {
        this.creditContract = creditContract;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CreditApplication that = (CreditApplication) o;
        return Objects.equals(id, that.id) && Objects.equals(client, that.client) && Objects.equals(desiredAmount, that.desiredAmount) && Objects.equals(applicationDate, that.applicationDate) && Objects.equals(status, that.status) && Objects.equals(approvedAmount, that.approvedAmount) && Objects.equals(approvedTermDays, that.approvedTermDays) && Objects.equals(decisionDate, that.decisionDate) && Objects.equals(creditContract, that.creditContract);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, client, desiredAmount, applicationDate, status, approvedAmount, approvedTermDays, decisionDate, creditContract);
    }

    @Override
    public String toString() {
        return "CreditApplication{" +
               "id=" + id +
               ", client=" + client +
               ", desiredAmount=" + desiredAmount +
               ", applicationDate=" + applicationDate +
               ", status='" + status + '\'' +
               ", approvedAmount=" + approvedAmount +
               ", approvedTermDays=" + approvedTermDays +
               ", decisionDate=" + decisionDate +
               ", creditContract=" + creditContract +
               '}';
    }
}


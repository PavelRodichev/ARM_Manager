package by.pasha.arm_manager.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "credit_contracts")
public class CreditContract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "application_id", nullable = false)
    private CreditApplication creditApplication;

    @Column(name = "signature_date")
    private LocalDateTime signatureDate;

    @Column(name = "is_signed")
    private Boolean isSigned = false;

    // Конструкторы
    public CreditContract() {
    }

    public CreditContract(CreditApplication creditApplication) {
        this.creditApplication = creditApplication;
    }

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CreditApplication getCreditApplication() {
        return creditApplication;
    }

    public void setCreditApplication(CreditApplication creditApplication) {
        this.creditApplication = creditApplication;
    }

    public LocalDateTime getSignatureDate() {
        return signatureDate;
    }

    public void setSignatureDate(LocalDateTime signatureDate) {
        this.signatureDate = signatureDate;
    }

    public Boolean getIsSigned() {
        return isSigned;
    }

    public void setIsSigned(Boolean isSigned) {
        this.isSigned = isSigned;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CreditContract that = (CreditContract) o;
        return Objects.equals(id, that.id) && Objects.equals(creditApplication, that.creditApplication) && Objects.equals(signatureDate, that.signatureDate) && Objects.equals(isSigned, that.isSigned);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, creditApplication, signatureDate, isSigned);
    }

    @Override
    public String toString() {
        return "CreditContract{" +
               "id=" + id +
               ", creditApplication=" + creditApplication +
               ", signatureDate=" + signatureDate +
               ", isSigned=" + isSigned +
               '}';
    }
}
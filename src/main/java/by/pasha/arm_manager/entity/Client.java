package by.pasha.arm_manager.entity;


import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "passport_series", nullable = false)
    private String passportSeries;

    @Column(name = "passport_number", nullable = false)
    private String passportNumber;

    @Column(name = "marital_status", nullable = false)
    private String maritalStatus;

    @Column(name = "registration_address", nullable = false)
    private String registrationAddress;

    @Column(name = "phone", nullable = false, unique = true)
    private String phone;

    @Column(name = "employment_info")
    private String employmentInfo;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<CreditApplication> creditApplications;

    // Конструкторы
    public Client() {
    }

    public Client(String firstName, String lastName, String middleName,
                  String passportSeries, String passportNumber, String maritalStatus,
                  String registrationAddress, String phone, String employmentInfo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.passportSeries = passportSeries;
        this.passportNumber = passportNumber;
        this.maritalStatus = maritalStatus;
        this.registrationAddress = registrationAddress;
        this.phone = phone;
        this.employmentInfo = employmentInfo;
    }

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getPassportSeries() {
        return passportSeries;
    }

    public void setPassportSeries(String passportSeries) {
        this.passportSeries = passportSeries;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getRegistrationAddress() {
        return registrationAddress;
    }

    public void setRegistrationAddress(String registrationAddress) {
        this.registrationAddress = registrationAddress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmploymentInfo() {
        return employmentInfo;
    }

    public void setEmploymentInfo(String employmentInfo) {
        this.employmentInfo = employmentInfo;
    }

    public List<CreditApplication> getCreditApplications() {
        return creditApplications;
    }

    public void setCreditApplications(List<CreditApplication> creditApplications) {
        this.creditApplications = creditApplications;
    }

    public String getFullName() {
        return lastName + " " + firstName + (middleName != null ? " " + middleName : "");
    }
}


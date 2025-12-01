package by.pasha.arm_manager.repository;

import by.pasha.arm_manager.entity.CreditContract;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CreditContractRepository {
    private final SessionFactory sessionFactory;

    public CreditContractRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(CreditContract contract) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(contract);
    }

    public CreditContract findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(CreditContract.class, id);
    }

    public List<CreditContract> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM CreditContract", CreditContract.class).list();
    }

    public List<CreditContract> findBySignedStatus(Boolean isSigned) {
        Session session = sessionFactory.getCurrentSession();
        Query<CreditContract> query = session.createQuery(
                "FROM CreditContract WHERE isSigned = :isSigned", CreditContract.class);
        query.setParameter("isSigned", isSigned);
        return query.list();
    }

    public List<CreditContract> findSignedContracts() {
        return findBySignedStatus(true);
    }

    public CreditContract findByApplicationId(Long applicationId) {
        Session session = sessionFactory.getCurrentSession();
        Query<CreditContract> query = session.createQuery(
                "FROM CreditContract cc WHERE cc.creditApplication.id = :applicationId",
                CreditContract.class);
        query.setParameter("applicationId", applicationId);
        return query.uniqueResult();
    }
}
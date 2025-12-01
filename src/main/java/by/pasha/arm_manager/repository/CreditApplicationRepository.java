package by.pasha.arm_manager.repository;

import by.pasha.arm_manager.entity.CreditApplication;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CreditApplicationRepository {
    private final SessionFactory sessionFactory;

    public CreditApplicationRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(CreditApplication application) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(application);
    }

    public CreditApplication findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(CreditApplication.class, id);
    }

    public List<CreditApplication> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM CreditApplication", CreditApplication.class).list();
    }

    public List<CreditApplication> findByStatus(String status) {
        Session session = sessionFactory.getCurrentSession();
        Query<CreditApplication> query = session.createQuery(
                  "FROM CreditApplication WHERE status = :status", CreditApplication.class);
        query.setParameter("status", status);
        return query.list();
    }

    public List<CreditApplication> findApprovedApplications() {
        return findByStatus("APPROVED");
    }
}

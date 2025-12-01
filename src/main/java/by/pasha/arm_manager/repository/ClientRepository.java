package by.pasha.arm_manager.repository;


import by.pasha.arm_manager.entity.Client;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.hibernate.query.Query;

import java.util.List;

@Repository
public class ClientRepository {
    private final SessionFactory sessionFactory;

    public ClientRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(Client client) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(client);
    }


    public List<Client> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Query<Client> query = session.createQuery(
                "SELECT DISTINCT c FROM Client c LEFT JOIN FETCH c.creditApplications",
                Client.class
        );
        return query.getResultList();
    }

    public Client findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Client.class, id);
    }

    public List<Client> searchClients(String searchTerm) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM Client c WHERE " +
                     "LOWER(c.firstName) LIKE LOWER(:searchTerm) OR " +
                     "LOWER(c.lastName) LIKE LOWER(:searchTerm) OR " +
                     "LOWER(c.middleName) LIKE LOWER(:searchTerm) OR " +
                     "c.phone LIKE :searchTerm OR " +
                     "CONCAT(c.passportSeries, c.passportNumber) LIKE :searchTerm";

        Query<Client> query = session.createQuery(hql, Client.class);
        query.setParameter("searchTerm", "%" + searchTerm + "%");
        return query.list();
    }

    public Client findByPhone(String phone) {
        Session session = sessionFactory.getCurrentSession();
        Query<Client> query = session.createQuery("FROM Client WHERE phone = :phone", Client.class);
        query.setParameter("phone", phone);
        return query.uniqueResult();
    }
}
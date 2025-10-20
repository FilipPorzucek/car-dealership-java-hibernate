package com.filip.infrastructure.database.repository;

import com.filip.business.dao.CustomerDao;
import com.filip.business.dao.SalesmanDao;
import com.filip.infrastructure.configuration.HibernateUtil;
import com.filip.infrastructure.database.entity.CustomerEntity;
import com.filip.infrastructure.database.entity.SalesmanEntity;
import org.hibernate.Session;

import java.util.Objects;
import java.util.Optional;

public class CustomerRepository implements CustomerDao {

    @Override
    public Optional<CustomerEntity> findByEmail(String email) {
        try (Session session = HibernateUtil.getSessionFactory()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }

            session.beginTransaction();

            String query = "SELECT se FROM CustomerEntity se WHERE se.email=:email";
            Optional<CustomerEntity> result = session.createQuery(query, CustomerEntity.class)
                    .setParameter("email", email)
                    .uniqueResultOptional();
            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public void issueInvoice(CustomerEntity customer) {
        try (Session session = HibernateUtil.getSessionFactory()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            if(Objects.isNull(customer.getCustomerId())){
                session.persist(customer);
            }

            customer.getInvoices().stream()
                    .filter(invoice->Objects.isNull(invoice.getInvoiceId()))
                            .forEach(invoice->{
                                invoice.setCustomer(customer);
                                        session.persist(invoice);
                            });

            session.getTransaction().commit();
        }
    }

    @Override
    public void saveServiceRequest(CustomerEntity customer) {
        try (Session session = HibernateUtil.getSessionFactory()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            customer.getCarServiceRequests().stream()
                            .filter(request->Objects.isNull(request.getCarServiceRequestId()))
                                    .forEach(session::persist);

            session.getTransaction().commit();
        }
    }
}

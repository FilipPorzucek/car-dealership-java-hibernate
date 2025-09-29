package com.filip.infrastructure.database.repository;

import com.filip.business.dao.CarDealerShipManagmentDao;
import com.filip.infrastructure.configuration.HibernateUtil;
import org.hibernate.Session;

import java.util.List;
import java.util.Objects;

public class CarDealerShipMenagmentRepository implements CarDealerShipManagmentDao {
    @Override
    public void purge() {
        try (Session session=HibernateUtil.getSessionFactory()){
            if(Objects.isNull(session)){
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            session.createMutationQuery("DELETE FROM ServiceMechanicEntity ent").executeUpdate();
            session.createMutationQuery("DELETE FROM ServicePartEntity ent").executeUpdate();
            session.createMutationQuery("DELETE FROM CarServiceRequestEntity ent").executeUpdate();
            session.createMutationQuery("DELETE FROM InvoiceEntity ent").executeUpdate();
            session.createMutationQuery("DELETE FROM MechanicEntity ent").executeUpdate();
            session.createMutationQuery("DELETE FROM PartEntity ent").executeUpdate();
            session.createMutationQuery("DELETE FROM ServiceEntity ent").executeUpdate();
            session.createMutationQuery("DELETE FROM CarToBuyEntity ent").executeUpdate();
            session.createMutationQuery("DELETE FROM CarToServiceEntity ent").executeUpdate();
            session.createMutationQuery("DELETE FROM AddressEntity ent").executeUpdate();
            session.createMutationQuery("DELETE FROM CustomerEntity ent").executeUpdate();
            session.createMutationQuery("DELETE FROM SalesmanEntity ent").executeUpdate();

            session.getTransaction().commit();
        }
    }

    @Override
    public void saveAll(List<?> entites) {
        try(Session session=HibernateUtil.getSessionFactory()) {
            if (Objects.isNull(session)){
                throw new RuntimeException("Session is null");
            }

            session.beginTransaction();
            for (var entite : entites) {
                session.persist(entite);
            }

            session.getTransaction().commit();

        }

    }
}

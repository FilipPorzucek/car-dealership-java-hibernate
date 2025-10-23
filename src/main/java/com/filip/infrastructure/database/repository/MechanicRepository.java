package com.filip.infrastructure.database.repository;

import com.filip.business.dao.MechanicDao;
import com.filip.infrastructure.configuration.HibernateUtil;
import com.filip.infrastructure.database.entity.MechanicEntity;
import org.hibernate.Session;

import java.util.Objects;
import java.util.Optional;

public class MechanicRepository implements MechanicDao {

    @Override
    public Optional<MechanicEntity> findByPesel(String pesel) {
        try(Session session= HibernateUtil.getSessionFactory()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }

            session.beginTransaction();

            String query="SELECT se FROM MechanicEntity se WHERE se.pesel=:pesel";
            Optional<MechanicEntity> result = session.createQuery(query, MechanicEntity.class)
                    .setParameter("pesel", pesel)
                    .uniqueResultOptional();
            session.getTransaction().commit();
        return result;
        }
    }
}

package com.filip.infrastructure.database.repository;

import com.filip.business.dao.CarServiceRequestDao;
import com.filip.infrastructure.configuration.HibernateUtil;
import com.filip.infrastructure.database.entity.CarServiceRequestEntity;
import org.hibernate.Session;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class CarServiceRequestRepository implements CarServiceRequestDao {
    @Override
    public Set<CarServiceRequestEntity> findActiveServiceRequestByCarVin(String carVin) {
        try (Session session = HibernateUtil.getSessionFactory()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");

            }
            session.beginTransaction();

            String query = """
                    SELECT sr FROM CarServiceRequestEntity sr WHERE sr.car.vin=:vin
                    AND sr.completedDateTime IS NULL
                    """;
            List<CarServiceRequestEntity> result = session.createQuery(query, CarServiceRequestEntity.class)
                    .setParameter("vin", carVin)
                    .list();

            session.getTransaction().commit();
            return new HashSet<>(result);
        }
    }
}

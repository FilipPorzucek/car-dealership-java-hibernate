package com.filip.infrastructure.database.repository;

import com.filip.business.dao.CarDao;
import com.filip.business.dao.SalesmanDao;
import com.filip.infrastructure.configuration.HibernateUtil;
import com.filip.infrastructure.database.entity.CarToBuyEntity;
import com.filip.infrastructure.database.entity.SalesmanEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.ParameterExpression;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;

import java.util.Objects;
import java.util.Optional;

public class SalesmanRepository implements SalesmanDao {

    @Override
    public Optional<SalesmanEntity> findByPesel(String pesel) {
        try(Session session= HibernateUtil.getSessionFactory()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }

            session.beginTransaction();

            String query="SELECT se FROM SalesmanEntity se WHERE se.pesel=:pesel";
            Optional<SalesmanEntity> result = session.createQuery(query, SalesmanEntity.class)
                    .setParameter("pesel", pesel)
                    .uniqueResultOptional();
            session.getTransaction().commit();
        return result;
        }
    }
}

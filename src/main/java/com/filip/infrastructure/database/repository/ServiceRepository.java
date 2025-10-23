package com.filip.infrastructure.database.repository;

import com.filip.business.dao.ServiceDao;
import com.filip.infrastructure.configuration.HibernateUtil;
import com.filip.infrastructure.database.entity.ServiceEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.ParameterExpression;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.Objects;
import java.util.Optional;

public class ServiceRepository implements ServiceDao {


    @Override
    public Optional<ServiceEntity> findByServiceCode(String serviceCode) {
        try (Session session = HibernateUtil.getSessionFactory()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }

            session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<ServiceEntity> criteriaQuery = criteriaBuilder.createQuery(ServiceEntity.class);

            Root<ServiceEntity> root = criteriaQuery.from(ServiceEntity.class);
            ParameterExpression<String> parameter1 = criteriaBuilder.parameter(String.class);
            criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("serviceCode"), parameter1));

            Query<ServiceEntity> query = session.createQuery(criteriaQuery);
            query.setParameter(parameter1, serviceCode);
            try {
                ServiceEntity result = query.getSingleResult();
                session.getTransaction().commit();
                return Optional.of(result);
            } catch (Throwable ex) {
                return Optional.empty();
            }

        }
    }
}

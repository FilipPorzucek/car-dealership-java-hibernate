package com.filip.infrastructure.database.repository;

import com.filip.business.dao.CarDao;
import com.filip.infrastructure.configuration.HibernateUtil;
import com.filip.infrastructure.database.entity.CarToBuyEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.ParameterExpression;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;

import java.util.Objects;
import java.util.Optional;

public class CarRepository implements CarDao {
    @Override
    public Optional<CarToBuyEntity> findCarToBuyByVin(String vin) {

        try(Session session= HibernateUtil.getSessionFactory()) {
            if (Objects.isNull(session)){
                throw new RuntimeException("Session is null");
            }

            session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<CarToBuyEntity> criteriaQuery = criteriaBuilder.createQuery(CarToBuyEntity.class);
            Root<CarToBuyEntity> root = criteriaQuery.from(CarToBuyEntity.class);
            ParameterExpression<String> parameter1 = criteriaBuilder.parameter(String.class);
            criteriaQuery.select(root).where(criteriaBuilder.equal(root.get(vin),parameter1));

            session.getTransaction().commit();

        }
    }
}

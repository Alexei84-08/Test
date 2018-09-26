package ru.exercise.webserver.da.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.exercise.webserver.da.model.ComputerPartEntity;
import ru.exercise.webserver.da.model.ComputerPartEntityListRequest;

@Component
public class ComputerPartDao {

    private static final int PAGE_SIZE = 10;

    private final EntityManager entityManager;
    private final ComputerPartListQueryBuilder queryBuilder;
    private final ComputerPartMinValueBuilder requiredCountBuilder;
    private final ComputerPartTotalCountBuilder totalCountBuilder;
    private final FilterValueApplier valueApplier;

    @Autowired
    public ComputerPartDao(final EntityManager entityManager,
                           final ComputerPartListQueryBuilder queryBuilder,
                           final ComputerPartMinValueBuilder requiredCountBuilder,
                           final ComputerPartTotalCountBuilder totalCountBuilder,
                           final FilterValueApplier valueApplier) {
        this.entityManager = entityManager;
        this.queryBuilder = queryBuilder;
        this.requiredCountBuilder = requiredCountBuilder;
        this.totalCountBuilder = totalCountBuilder;
        this.valueApplier = valueApplier;
    }

    public Long totalCount(final ComputerPartEntityListRequest request) {
        final TypedQuery<Long> query = entityManager.createQuery(
                totalCountBuilder.build(entityManager.getCriteriaBuilder(), request)
        );

        return valueApplier.apply(request, query).getSingleResult();
    }

    public Long totalCountAll(final ComputerPartEntityListRequest request) {
        final TypedQuery<Long> query = entityManager.createQuery(
                totalCountBuilder.build(entityManager.getCriteriaBuilder(), request)
        );

        return query.getSingleResult();
    }

    public List<ComputerPartEntity> list(final ComputerPartEntityListRequest request) {
        final TypedQuery<ComputerPartEntity> query = entityManager.createQuery(
                queryBuilder.build(entityManager.getCriteriaBuilder(), request)
        );
        query.setFirstResult((request.getPageNumber()) * PAGE_SIZE);
        query.setMaxResults(PAGE_SIZE);

        return valueApplier.apply(request, query).getResultList();
    }

    public List<ComputerPartEntity> listAll(final ComputerPartEntityListRequest request) {
        final TypedQuery<ComputerPartEntity> query = entityManager.createQuery(
                queryBuilder.build(entityManager.getCriteriaBuilder(), request)
        );
        query.setFirstResult((request.getPageNumber()) * PAGE_SIZE);
        query.setMaxResults(PAGE_SIZE);

        return query.getResultList();
    }

    public Integer requiredCount() {
        final TypedQuery<Integer> query = entityManager.createQuery(
                requiredCountBuilder.build(entityManager.getCriteriaBuilder())
        );

        return query.getSingleResult();
    }

    @Transactional
    public ComputerPartEntity update(final ComputerPartEntity computerPart) {
        final ComputerPartEntity entity = entityManager.merge(computerPart);
        entityManager.flush();
        
        return entity;
    }

    @Transactional
    public void delete(final Long id) {
        final ComputerPartEntity entity = entityManager.find(ComputerPartEntity.class, id);
        if (entity != null) {
            entityManager.remove(entity);
        }
        entityManager.flush();
    }
}

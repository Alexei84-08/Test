package ru.exercise.webserver.da.dao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.exercise.webserver.da.model.ComputerPartEntity;
import ru.exercise.webserver.da.model.ComputerPartEntityListRequest;

@Component
public class ComputerPartListQueryBuilder {

    private final FilterNameExpressionResolver expressionResolver;

    @Autowired
    public ComputerPartListQueryBuilder(final FilterNameExpressionResolver expressionResolver) {
        this.expressionResolver = expressionResolver;
    }

    public CriteriaQuery build(final CriteriaBuilder criteriaBuilder,
                               final ComputerPartEntityListRequest manageRequest) {
        final CriteriaQuery<ComputerPartEntity> criteriaQuery = criteriaBuilder.createQuery(ComputerPartEntity.class);
        final Root<ComputerPartEntity> queryRoot = criteriaQuery.from(ComputerPartEntity.class);
        criteriaQuery.select(queryRoot);
        if (manageRequest.getFilter() != null && manageRequest.getFilter().getName() != null && !"".equals(manageRequest.getFilter().getName())) {
            if ("name".equals(manageRequest.getFilter().getName())) {
                criteriaQuery.where(
                        criteriaBuilder.like(
                                queryRoot.get(manageRequest.getFilter().getName()),
                                criteriaBuilder.parameter(String.class, manageRequest.getFilter().getName())
                        )
                );
            } else {
                criteriaQuery.where(
                        criteriaBuilder.equal(
                                queryRoot.get(manageRequest.getFilter().getName()),
                                expressionResolver.resolve(manageRequest, criteriaBuilder)
                        )
                );
            }
        }
        return criteriaQuery;
    }
}

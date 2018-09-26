package ru.exercise.webserver.da.dao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.exercise.webserver.da.model.ComputerPartEntity;

@Component
public class ComputerPartMinValueBuilder {

    private final FilterNameExpressionResolver expressionResolver;

    @Autowired
    public ComputerPartMinValueBuilder(final FilterNameExpressionResolver expressionResolver) {
        this.expressionResolver = expressionResolver;
    }

    public CriteriaQuery build(final CriteriaBuilder criteriaBuilder) {
        final CriteriaQuery<Integer> criteriaQuery = criteriaBuilder.createQuery(Integer.class);
        final Root<ComputerPartEntity> queryRoot = criteriaQuery.from(ComputerPartEntity.class);
        criteriaQuery
                .select(criteriaBuilder.min(queryRoot.get("quantity")))
                .where(criteriaBuilder.equal(queryRoot.get("required"), true));
        return criteriaQuery;
    }
}

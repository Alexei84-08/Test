package ru.exercise.webserver.da.dao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import org.springframework.stereotype.Component;
import ru.exercise.webserver.common.exception.BusinessException;
import ru.exercise.webserver.da.model.ComputerPartEntityListRequest;

@Component
public class FilterNameExpressionResolver {

    public Expression<?> resolve(final ComputerPartEntityListRequest request, final CriteriaBuilder criteriaBuilder) {
        if ("quantity".equals(request.getFilter().getName())) {
            return criteriaBuilder.parameter(Integer.class, request.getFilter().getName());
        }
        if ("required".equals(request.getFilter().getName())) {
            return criteriaBuilder.parameter(Boolean.class, request.getFilter().getName());
        }
        if ("name".equals(request.getFilter().getName())) {
            return criteriaBuilder.parameter(String.class, request.getFilter().getName());
        }

        throw new BusinessException(String.format("Unknown filter name specified: %s", request.getFilter().getName()));
    }
}

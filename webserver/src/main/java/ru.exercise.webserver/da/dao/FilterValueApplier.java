package ru.exercise.webserver.da.dao;

import org.springframework.stereotype.Component;
import ru.exercise.webserver.da.model.ComputerPartEntityListRequest;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;

@Component
public class FilterValueApplier {

    public <T> TypedQuery<T> apply(final ComputerPartEntityListRequest request, final TypedQuery<T> query) {
        if ("name".equals(request.getFilter().getName())) {
            query.setParameter(request.getFilter().getName(), "%" + request.getFilter().getValue() + "%");
        } else {
            if ("required".equals(request.getFilter().getName())) {
                query.setParameter(request.getFilter().getName(), Boolean.valueOf(request.getFilter().getValue()));
            }
        }
        return query;
    }
}

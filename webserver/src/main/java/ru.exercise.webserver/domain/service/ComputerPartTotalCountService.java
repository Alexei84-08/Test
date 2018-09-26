package ru.exercise.webserver.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.exercise.webserver.common.converter.Converter;
import ru.exercise.webserver.da.dao.ComputerPartDao;
import ru.exercise.webserver.da.model.ComputerPartEntityListRequest;
import ru.exercise.webserver.domain.model.ComputerPartListRequest;

@Service
public class ComputerPartTotalCountService {

    private final ComputerPartDao computerPartDao;
    private final Converter<ComputerPartListRequest, ComputerPartEntityListRequest> requestConverter;

    @Autowired
    public ComputerPartTotalCountService(final ComputerPartDao computerPartDao,
                                         final Converter<ComputerPartListRequest, ComputerPartEntityListRequest> requestConverter) {
        this.computerPartDao = computerPartDao;
        this.requestConverter = requestConverter;
    }

    public Long count(final ComputerPartListRequest request) {
        if (request.getFilter() == null || request.getFilter().getName() == null || "".equals(request.getFilter().getName())) {
            return computerPartDao.totalCountAll(requestConverter.convert(request));
        }
        return computerPartDao.totalCount(requestConverter.convert(request));
    }
}

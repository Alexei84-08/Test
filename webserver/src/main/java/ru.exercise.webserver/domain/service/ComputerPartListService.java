package ru.exercise.webserver.domain.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.exercise.webserver.common.converter.Converter;
import ru.exercise.webserver.da.dao.ComputerPartDao;
import ru.exercise.webserver.da.model.ComputerPartEntity;
import ru.exercise.webserver.da.model.ComputerPartEntityListRequest;
import ru.exercise.webserver.domain.model.ComputerPart;
import ru.exercise.webserver.domain.model.ComputerPartListRequest;

@Service
public class ComputerPartListService {

    private final ComputerPartDao computerPartDao;
    private final Converter<ComputerPartEntity, ComputerPart> computerPartConverter;
    private final Converter<ComputerPartListRequest, ComputerPartEntityListRequest> requestConverter;

    @Autowired
    public ComputerPartListService(final ComputerPartDao computerPartDao,
                                   final Converter<ComputerPartEntity, ComputerPart> computerPartConverter,
                                   final Converter<ComputerPartListRequest, ComputerPartEntityListRequest> requestConverter) {
        this.computerPartDao = computerPartDao;
        this.computerPartConverter = computerPartConverter;
        this.requestConverter = requestConverter;
    }

    public List<ComputerPart> list(final ComputerPartListRequest request) {
        if (request.getFilter() == null || request.getFilter().getName() == null || "".equals(request.getFilter().getName())) {
            return computerPartDao.listAll(requestConverter.convert(request))
                    .stream()
                    .map(computerPartConverter::convert)
                    .collect(Collectors.toList());
        }
        return computerPartDao.list(requestConverter.convert(request))
                .stream()
                .map(computerPartConverter::convert)
                .collect(Collectors.toList());
    }
}

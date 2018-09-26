package ru.exercise.webserver.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.exercise.webserver.common.converter.Converter;
import ru.exercise.webserver.da.dao.ComputerPartDao;
import ru.exercise.webserver.da.model.ComputerPartEntity;
import ru.exercise.webserver.domain.model.ComputerPart;

@Service
public class ComputerPartDeleteService {

    private final ComputerPartDao computerPartDao;
    private final Converter<ComputerPart, ComputerPartEntity> entityConverter;

    @Autowired
    public ComputerPartDeleteService(final ComputerPartDao computerPartDao,
                                     final Converter<ComputerPart, ComputerPartEntity> entityConverter) {
        this.computerPartDao = computerPartDao;
        this.entityConverter = entityConverter;
    }

    public void delete(final Long id) {
        computerPartDao.delete(id);
    }
}

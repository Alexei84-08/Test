package ru.exercise.webserver.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.exercise.webserver.common.converter.Converter;
import ru.exercise.webserver.da.dao.ComputerPartDao;
import ru.exercise.webserver.da.model.ComputerPartEntity;
import ru.exercise.webserver.domain.model.ComputerPart;

@Service
public class ComputerPartUpdateService {

    private final ComputerPartDao computerPartDao;
    private final Converter<ComputerPart, ComputerPartEntity> entityConverter;
    private final Converter<ComputerPartEntity, ComputerPart> domainConverter;

    @Autowired
    public ComputerPartUpdateService(final ComputerPartDao computerPartDao,
                                     final Converter<ComputerPart, ComputerPartEntity> entityConverter,
                                     final Converter<ComputerPartEntity, ComputerPart> domainConverter) {
        this.computerPartDao = computerPartDao;
        this.domainConverter = domainConverter;
        this.entityConverter = entityConverter;
    }

    public ComputerPart update(final ComputerPart computerPart) {
        return domainConverter.convert(
                computerPartDao.update(
                        entityConverter.convert(computerPart)
                )
        );
    }
}

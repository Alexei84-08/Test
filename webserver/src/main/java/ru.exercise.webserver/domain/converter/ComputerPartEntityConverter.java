package ru.exercise.webserver.domain.converter;

import org.springframework.stereotype.Component;
import ru.exercise.webserver.common.converter.Converter;
import ru.exercise.webserver.da.model.ComputerPartEntity;
import ru.exercise.webserver.domain.model.ComputerPart;

@Component
public class ComputerPartEntityConverter implements Converter<ComputerPart, ComputerPartEntity> {

    @Override
    public ComputerPartEntity convert(final ComputerPart source) {
        return new ComputerPartEntity()
                .setId(source.getId())
                .setName(source.getName())
                .setQuantity(source.getQuantity())
                .setRequired(source.getRequired());
    }
}

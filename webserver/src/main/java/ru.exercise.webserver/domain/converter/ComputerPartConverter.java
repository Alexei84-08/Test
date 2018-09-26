package ru.exercise.webserver.domain.converter;

import org.springframework.stereotype.Component;
import ru.exercise.webserver.common.converter.Converter;
import ru.exercise.webserver.da.model.ComputerPartEntity;
import ru.exercise.webserver.domain.model.ComputerPart;

@Component
public class ComputerPartConverter implements Converter<ComputerPartEntity, ComputerPart> {

    @Override
    public ComputerPart convert(final ComputerPartEntity source) {
        return new ComputerPart()
                .setId(source.getId())
                .setName(source.getName())
                .setQuantity(source.getQuantity())
                .setRequired(source.getRequired());
    }
}

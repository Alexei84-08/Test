package ru.exercise.webserver.web.converter;

import org.springframework.stereotype.Component;
import ru.exercise.webserver.common.converter.Converter;
import ru.exercise.webserver.da.model.ComputerPartEntity;
import ru.exercise.webserver.domain.model.ComputerPart;
import ru.exercise.webserver.web.model.ComputerPartJson;

@Component
public class ComputerPartDomainConverter implements Converter<ComputerPartJson, ComputerPart> {

    @Override
    public ComputerPart convert(final ComputerPartJson source) {
        return new ComputerPart()
                .setId(source.getId())
                .setName(source.getName())
                .setQuantity(source.getQuantity())
                .setRequired(source.getRequired());
    }
}

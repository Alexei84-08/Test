package ru.exercise.webserver.web.converter;

import org.springframework.stereotype.Component;
import ru.exercise.webserver.common.converter.Converter;
import ru.exercise.webserver.domain.model.ComputerPartListRequestFilter;
import ru.exercise.webserver.web.model.ComputerPartListRequestFilterJson;

@Component
public class ComputerPartListRequestFilterConverter
        implements Converter<ComputerPartListRequestFilterJson, ComputerPartListRequestFilter> {

    @Override
    public ComputerPartListRequestFilter convert(final ComputerPartListRequestFilterJson source) {
        if (source == null) {
            return null;
        }
        return new ComputerPartListRequestFilter()
                .setName(source.getName())
                .setValue(source.getValue());
    }
}

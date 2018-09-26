package ru.exercise.webserver.domain.converter;

import org.springframework.stereotype.Component;
import ru.exercise.webserver.common.converter.Converter;
import ru.exercise.webserver.da.model.ComputerPartEntityListRequestFilter;
import ru.exercise.webserver.domain.model.ComputerPartListRequestFilter;

@Component
public class ComputerPartEntityListRequestFilterConverter 
        implements Converter<ComputerPartListRequestFilter, ComputerPartEntityListRequestFilter> {

    @Override
    public ComputerPartEntityListRequestFilter convert(final ComputerPartListRequestFilter source) {
        if (source == null) {
            return null;
        }
        return new ComputerPartEntityListRequestFilter()
                .setName(source.getName())
                .setValue(source.getValue());
    }
}

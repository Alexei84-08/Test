package ru.exercise.webserver.domain.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.exercise.webserver.common.converter.Converter;
import ru.exercise.webserver.da.model.ComputerPartEntityListRequest;
import ru.exercise.webserver.da.model.ComputerPartEntityListRequestFilter;
import ru.exercise.webserver.domain.model.ComputerPartListRequest;
import ru.exercise.webserver.domain.model.ComputerPartListRequestFilter;

@Component
public class ComputerPartEntityListRequestConverter implements Converter<ComputerPartListRequest, ComputerPartEntityListRequest> {

    private final Converter<ComputerPartListRequestFilter, ComputerPartEntityListRequestFilter> converter;

    @Autowired
    public ComputerPartEntityListRequestConverter(final Converter<ComputerPartListRequestFilter, ComputerPartEntityListRequestFilter> converter) {
        this.converter = converter;
    }

    @Override
    public ComputerPartEntityListRequest convert(final ComputerPartListRequest source) {
        return new ComputerPartEntityListRequest()
                .setPageNumber(source.getPageNumber())
                .setFilter(converter.convert(source.getFilter()));
    }
}

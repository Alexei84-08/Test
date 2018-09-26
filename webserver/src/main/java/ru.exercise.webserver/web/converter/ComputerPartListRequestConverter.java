package ru.exercise.webserver.web.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.exercise.webserver.common.converter.Converter;
import ru.exercise.webserver.domain.model.ComputerPartListRequest;
import ru.exercise.webserver.domain.model.ComputerPartListRequestFilter;
import ru.exercise.webserver.web.model.ComputerPartListRequestFilterJson;
import ru.exercise.webserver.web.model.ComputerPartListRequestJson;

@Component
public class ComputerPartListRequestConverter implements Converter<ComputerPartListRequestJson, ComputerPartListRequest> {

    private final Converter<ComputerPartListRequestFilterJson, ComputerPartListRequestFilter> converter;

    @Autowired
    public ComputerPartListRequestConverter(
            final Converter<ComputerPartListRequestFilterJson, ComputerPartListRequestFilter> converter) {
        this.converter = converter;
    }

    @Override
    public ComputerPartListRequest convert(final ComputerPartListRequestJson source) {
        return new ComputerPartListRequest()
                .setPageNumber(source.getPageNumber())
                .setFilter(converter.convert(source.getFilter()));
    }
}

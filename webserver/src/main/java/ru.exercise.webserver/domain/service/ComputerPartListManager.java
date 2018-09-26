package ru.exercise.webserver.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.exercise.webserver.domain.model.ComputerPartListRequest;
import ru.exercise.webserver.domain.model.ComputerPartListResponse;

@Component
public class ComputerPartListManager {

    private final ComputerPartListService listService;
    private final ComputerPartTotalCountService totalCountService;
    private final ComputerPartRequiredCountService requiredCountService;

    @Autowired
    public ComputerPartListManager(final ComputerPartListService listService,
                                   final ComputerPartTotalCountService totalCountService,
                                   final ComputerPartRequiredCountService requiredCountService) {
        this.listService = listService;
        this.totalCountService = totalCountService;
        this.requiredCountService = requiredCountService;
    }

    public ComputerPartListResponse list(final ComputerPartListRequest request) {
        return new ComputerPartListResponse()
                .setComputerParts(listService.list(request))
                .setTotalCount(totalCountService.count(request))
                .setRequiredCount(requiredCountService.count());
    }

    public ComputerPartListResponse listAll(final Integer pageNumber) {
        return list(new ComputerPartListRequest().setPageNumber(pageNumber));
    }
}

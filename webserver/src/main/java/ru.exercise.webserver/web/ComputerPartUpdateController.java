package ru.exercise.webserver.web;

import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.exercise.webserver.domain.model.ComputerPart;
import ru.exercise.webserver.domain.model.ComputerPartListRequest;
import ru.exercise.webserver.domain.model.ComputerPartListRequestFilter;
import ru.exercise.webserver.domain.service.ComputerPartManageService;
import ru.exercise.webserver.domain.service.ComputerPartUpdateService;
import ru.exercise.webserver.web.model.ComputerPartManage;

@Controller
@RequestMapping("")
public class ComputerPartUpdateController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ComputerPartUpdateController.class);

    private final ComputerPartUpdateService updateService;
    private final ComputerPartManageService manageService;

    @Autowired
    public ComputerPartUpdateController(final ComputerPartUpdateService updateService,
                                        final ComputerPartManageService manageService) {
        this.updateService = updateService;
        this.manageService = manageService;
    }

    @RequestMapping(value = "/update")
    public String update(
            @RequestParam(value = "id", required = false) final Long id,
            @RequestParam("name") final String name,
            @RequestParam("required") final Boolean required,
            @RequestParam("quantity") final Integer quantity,
            @RequestParam("filterName") final String filterName,
            @RequestParam("filterValue") final String filterValue,
            @RequestParam("pageNumber") final Integer pageNumber,
            final HttpSession session) {
        final ComputerPartManage request = new ComputerPartManage()
                .setComputerPart(
                        new ComputerPart()
                                .setId(id)
                                .setName(name)
                                .setQuantity(quantity)
                                .setRequired(required)
                )
                .setListRequest(
                        new ComputerPartListRequest()
                                .setFilter(new ComputerPartListRequestFilter().setName(filterName).setValue(filterValue))
                                .setPageNumber(pageNumber)
                );

        LOGGER.info("Изменение комплектующих: {}", request);
        session.setAttribute(
                "list",
                manageService.manage(
                        () -> updateService.update(
                                request.getComputerPart()
                        ),
                        request.getListRequest()
                )
        );
        session.setAttribute("request", request.getListRequest());

        return "index";
    }
}

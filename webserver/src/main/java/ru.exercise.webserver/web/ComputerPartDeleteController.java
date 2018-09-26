package ru.exercise.webserver.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.exercise.webserver.domain.model.ComputerPartListRequest;
import ru.exercise.webserver.domain.model.ComputerPartListRequestFilter;
import ru.exercise.webserver.domain.service.ComputerPartDeleteService;
import ru.exercise.webserver.domain.service.ComputerPartManageService;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class ComputerPartDeleteController {

    private final ComputerPartDeleteService deleteService;
    private final ComputerPartManageService manageService;

    @Autowired
    public ComputerPartDeleteController(final ComputerPartDeleteService deleteService,
                                        final ComputerPartManageService manageService) {
        this.deleteService = deleteService;
        this.manageService = manageService;
    }

    @RequestMapping(value = "/delete")
    public String update(
            @RequestParam("id") final Long id,
            @RequestParam("filterName") final String filterName,
            @RequestParam("filterValue") final String filterValue,
            @RequestParam("pageNumber") final Integer pageNumber,
            final HttpSession session) {
        final ComputerPartListRequest request = new ComputerPartListRequest()
                .setFilter(new ComputerPartListRequestFilter().setName(filterName).setValue(filterValue))
                .setPageNumber(pageNumber);

        session.setAttribute(
                "list",
                manageService.manage(() -> deleteService.delete(id), request)
        );
        session.setAttribute("request", request);

        return "index";
    }
}

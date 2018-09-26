package ru.exercise.webserver.web;

import static org.springframework.web.bind.annotation.RequestMethod.POST;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.exercise.webserver.domain.model.ComputerPartListRequest;
import ru.exercise.webserver.domain.model.ComputerPartListRequestFilter;
import ru.exercise.webserver.domain.service.ComputerPartListManager;

@Controller
@RequestMapping("/")
public class ComputerPartListController {

    private final ComputerPartListManager listManager;

    @Autowired
    public ComputerPartListController(final ComputerPartListManager listManager) {
        this.listManager = listManager;
    }

    @RequestMapping(value = "/list", method = POST)
    public String list(final HttpSession session,
                       @RequestParam(value = "filterName", required = false) final String filterName,
                       @RequestParam(value = "filterValue", required = false) final String filterValue,
                       @RequestParam("pageNumber") final String pageNumber) {
        final ComputerPartListRequest request = new ComputerPartListRequest()
                .setPageNumber(Integer.valueOf(pageNumber))
                .setFilter(
                        new ComputerPartListRequestFilter()
                                .setName("".equals(filterValue) ? "" : filterName)
                                .setValue(filterValue)
                );
        session.setAttribute("list", listManager.list(request));
        session.setAttribute("request", request);

        return "index";
    }
}

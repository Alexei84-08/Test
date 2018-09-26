package ru.exercise.webserver.web;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.exercise.webserver.domain.model.ComputerPartListRequest;
import ru.exercise.webserver.domain.service.ComputerPartListManager;

@Controller
@RequestMapping("/")
public class IndexController {

    private final ComputerPartListManager listManager;

    @Autowired
    public IndexController(final ComputerPartListManager listManager) {
        this.listManager = listManager;
    }

    @RequestMapping("/")
    public String main(final HttpSession session) {
        session.setAttribute("list", listManager.listAll(0));
        session.setAttribute(
                "request",
                new ComputerPartListRequest()
                        .setPageNumber(0)
        );

        return "index";
    }
}

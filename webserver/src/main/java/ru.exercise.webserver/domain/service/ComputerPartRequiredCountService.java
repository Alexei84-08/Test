package ru.exercise.webserver.domain.service;

import org.springframework.stereotype.Service;
import ru.exercise.webserver.da.dao.ComputerPartDao;

@Service
public class ComputerPartRequiredCountService {

    private final ComputerPartDao computerPartDao;

    public ComputerPartRequiredCountService(final ComputerPartDao computerPartDao) {
        this.computerPartDao = computerPartDao;
    }

    public Integer count() {
        return computerPartDao.requiredCount();
    }
}

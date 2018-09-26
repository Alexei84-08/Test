package ru.exercise.webserver.domain.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.exercise.webserver.common.applier.Applier;
import ru.exercise.webserver.domain.model.ComputerPart;
import ru.exercise.webserver.domain.model.ComputerPartListRequest;
import ru.exercise.webserver.domain.model.ComputerPartListResponse;

@Component
public class ComputerPartManageService {
    
    private final ComputerPartListManager listManager;
    
    @Autowired
    public ComputerPartManageService(final ComputerPartListManager listManager) {
        this.listManager = listManager;
    }
    
    public ComputerPartListResponse manage(final Applier supplier, final ComputerPartListRequest listRequest) {
        supplier.apply();
        return listManager.list(listRequest);
    }
}

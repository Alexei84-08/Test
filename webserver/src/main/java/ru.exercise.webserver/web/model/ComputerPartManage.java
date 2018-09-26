package ru.exercise.webserver.web.model;

import ru.exercise.webserver.domain.model.ComputerPart;
import ru.exercise.webserver.domain.model.ComputerPartListRequest;

public class ComputerPartManage {
    
    private ComputerPart computerPart;
    private ComputerPartListRequest listRequest;

    public ComputerPart getComputerPart() {
        return computerPart;
    }

    public ComputerPartManage setComputerPart(ComputerPart computerPart) {
        this.computerPart = computerPart;
        return this;
    }

    public ComputerPartListRequest getListRequest() {
        return listRequest;
    }

    public ComputerPartManage setListRequest(ComputerPartListRequest listRequest) {
        this.listRequest = listRequest;
        return this;
    }

    @Override
    public String toString() {
        return "ComputerPartManage{"
                + " computerPart=" + computerPart
                + ",listRequest=" + listRequest
                + '}';
    }
}

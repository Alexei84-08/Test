package ru.exercise.webserver.web.model;

public class ComputerPartListRequestJson {
    
    private ComputerPartListRequestFilterJson filter;
    private int pageNumber;

    public ComputerPartListRequestFilterJson getFilter() {
        return filter;
    }

    public ComputerPartListRequestJson setFilter(ComputerPartListRequestFilterJson filter) {
        this.filter = filter;
        return this;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public ComputerPartListRequestJson setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
        return this;
    }

    @Override
    public String toString() {
        return "DetailListRequest{"
                + " filter=" + filter
                + ",pageNumber=" + pageNumber
                + '}';
    }
}

package ru.exercise.webserver.domain.model;

public class ComputerPartListRequest {
    
    private ComputerPartListRequestFilter filter;
    private int pageNumber;

    public ComputerPartListRequestFilter getFilter() {
        return filter;
    }

    public ComputerPartListRequest setFilter(ComputerPartListRequestFilter filter) {
        this.filter = filter;
        return this;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public ComputerPartListRequest setPageNumber(int pageNumber) {
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

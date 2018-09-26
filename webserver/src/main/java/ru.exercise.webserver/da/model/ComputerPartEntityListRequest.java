package ru.exercise.webserver.da.model;

public class ComputerPartEntityListRequest {

    private ComputerPartEntityListRequestFilter filter;
    private int pageNumber;

    public ComputerPartEntityListRequestFilter getFilter() {
        return filter;
    }

    public ComputerPartEntityListRequest setFilter(ComputerPartEntityListRequestFilter filter) {
        this.filter = filter;
        return this;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public ComputerPartEntityListRequest setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
        return this;
    }

    @Override
    public String toString() {
        return "ComputerPartEntityListRequest{"
                + " filter=" + filter
                + ",pageNumber=" + pageNumber
                + '}';
    }
}

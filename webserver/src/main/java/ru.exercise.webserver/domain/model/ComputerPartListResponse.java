package ru.exercise.webserver.domain.model;

import java.util.List;

public class ComputerPartListResponse {

    private List<ComputerPart> computerParts;
    private Long totalCount;
    private Integer requiredCount;

    public List<ComputerPart> getComputerParts() {
        return computerParts;
    }

    public ComputerPartListResponse setComputerParts(List<ComputerPart> computerParts) {
        this.computerParts = computerParts;
        return this;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public ComputerPartListResponse setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
        return this;
    }

    public Integer getRequiredCount() {
        return requiredCount;
    }

    public ComputerPartListResponse setRequiredCount(Integer requiredCount) {
        this.requiredCount = requiredCount;
        return this;
    }

    @Override
    public String toString() {
        return "ComputerPartListResponse{"
                + " computerParts=" + computerParts
                + ",totalCount=" + totalCount
                + ",requiredCount=" + requiredCount
                + '}';
    }
}

package ru.exercise.webserver.domain.model;

public class ComputerPartListRequestFilter {
    
    private String name;
    private String value;

    public String getName() {
        return name;
    }

    public ComputerPartListRequestFilter setName(String name) {
        this.name = name;
        return this;
    }

    public String getValue() {
        return value;
    }

    public ComputerPartListRequestFilter setValue(String value) {
        this.value = value;
        return this;
    }

    @Override
    public String toString() {
        return "DetailListRequestFilter{"
                + " name='" + name + '\''
                + ",value='" + value + '\''
                + '}';
    }
}

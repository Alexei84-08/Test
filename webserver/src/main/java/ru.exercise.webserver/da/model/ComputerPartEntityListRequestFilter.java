package ru.exercise.webserver.da.model;

public class ComputerPartEntityListRequestFilter {
    
    private String name;
    private String value;

    public String getName() {
        return name;
    }

    public ComputerPartEntityListRequestFilter setName(String name) {
        this.name = name;
        return this;
    }

    public String getValue() {
        return value;
    }

    public ComputerPartEntityListRequestFilter setValue(String value) {
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

package ru.exercise.webserver.web.model;

public class ComputerPartListRequestFilterJson {
    
    private String name;
    private String value;

    public String getName() {
        return name;
    }

    public ComputerPartListRequestFilterJson setName(String name) {
        this.name = name;
        return this;
    }

    public String getValue() {
        return value;
    }

    public ComputerPartListRequestFilterJson setValue(String value) {
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

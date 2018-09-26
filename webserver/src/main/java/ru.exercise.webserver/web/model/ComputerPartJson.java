package ru.exercise.webserver.web.model;

public class ComputerPartJson {

    private Long id;
    private Boolean required;
    private String name;
    private Integer quantity;

    public Long getId() {
        return id;
    }

    public ComputerPartJson setId(Long id) {
        this.id = id;
        return this;
    }

    public Boolean getRequired() {
        return required;
    }

    public ComputerPartJson setRequired(Boolean required) {
        this.required = required;
        return this;
    }

    public String getName() {
        return name;
    }

    public ComputerPartJson setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public ComputerPartJson setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    @Override
    public String toString() {
        return "ComputerPartJson{"
                + " id=" + id
                + ",required=" + required
                + ",name='" + name + '\''
                + ",quantity=" + quantity
                + '}';
    }
}

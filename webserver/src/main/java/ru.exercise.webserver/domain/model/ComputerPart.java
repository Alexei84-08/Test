package ru.exercise.webserver.domain.model;

public class ComputerPart {

    private Long id;
    private Boolean required;
    private String name;
    private Integer quantity;

    public Long getId() {
        return id;
    }

    public ComputerPart setId(Long id) {
        this.id = id;
        return this;
    }

    public Boolean getRequired() {
        return required;
    }

    public ComputerPart setRequired(Boolean required) {
        this.required = required;
        return this;
    }

    public String getName() {
        return name;
    }

    public ComputerPart setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public ComputerPart setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    @Override
    public String toString() {
        return "ComputerPart{"
                + " id=" + id
                + ",required=" + required
                + ",name='" + name + '\''
                + ",quantity=" + quantity
                + '}';
    }
}

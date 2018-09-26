package ru.exercise.webserver.da.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "computer_part", indexes = {
        @Index(columnList = "name", name = "computer_part_index_by_name"),
        @Index(columnList = "required", name = "computer_part_index_by_required")
})
public class ComputerPartEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "required", nullable = false)
    private Boolean required;
    
    @Column(name = "quantity")
    private Integer quantity;

    public Long getId() {
        return id;
    }

    public ComputerPartEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public Boolean getRequired() {
        return required;
    }

    public ComputerPartEntity setRequired(Boolean required) {
        this.required = required;
        return this;
    }

    public String getName() {
        return name;
    }

    public ComputerPartEntity setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public ComputerPartEntity setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    @Override
    public String toString() {
        return "ComputerPartEntity{"
                + " id=" + id
                + ",required=" + required
                + ",name='" + name + '\''
                + ",quantity=" + quantity
                + '}';
    }
}

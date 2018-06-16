package pjatk.pl.bicycles.model;

import java.math.BigDecimal;

/**
 * Created by mateuszsielawa on 15.06.2018.
 */
public class Bicycle {

    private Integer id;
    private String name;
    private String description;
    private BigDecimal price;
    private String  picture;


    public Bicycle(Integer id, String name, String description, BigDecimal price, String picture) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.picture = picture;
    }

    public Bicycle(String name, String description, BigDecimal price, String picture) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.picture = picture;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return name;
    }
}

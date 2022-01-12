package com.leo;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;
@Entity
@Table(name = "goods")
public class CGood{
    @Id
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    @Column(name = "id", updatable = true, nullable = false)
    private UUID id;
    @Column(name = "name", updatable = true)
    private String name;
    @Column(name = "price", updatable = true)
    private double price;
    @Column(name = "category", updatable = true)
    private String category;

    //ID
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    //NAME
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    //PRICE
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    //CATEGORY
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public CGood(UUID id, String name, double price, String category){
        setId(id);
        setName(name);
        setPrice(price);
        setCategory(category);
    }
    public CGood(){
        setId(UUID.randomUUID());
        setCategory("a");
        setName("ss");
        setPrice(100);
    }
}
package com.lypgod.demo.springboot.unittest.model.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Data
@Accessors(chain = true)
@Entity
public class Item {
    @Id
    private int id;
    private String name;
    private int price;
    private int quantity;
    @Transient
    private int value;
}

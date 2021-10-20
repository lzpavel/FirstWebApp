package com.pfl.firstwebapp.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FwaTable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String data;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public FwaTable() {
    }

    public FwaTable(String data) {
        this.data = data;
    }

    public FwaTable(Long id, String data) {
        this.id = id;
        this.data = data;
    }
}

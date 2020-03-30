package com.example.demo.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
public class Produit implements Serializable {

    @Id @GeneratedValue
    private long id ;
    @NotNull
    private String  designation;
    private double prix;
    @DecimalMin("1")
    private int quantitie;

    public Produit(){
        super();
    }

    public Produit(String designation, double prix, int quantitie) {
        super();
        this.designation= designation;
        this.prix = prix;
        this.quantitie = quantitie;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getQuantitie() {
        return quantitie;
    }

    public void setQuantitie(int quantitie) {
        this.quantitie = quantitie;
    }
}

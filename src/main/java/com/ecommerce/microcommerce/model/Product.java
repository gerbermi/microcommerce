/*
 * ------------------------------------------------------------------------------------------------
 * Copyright 2014 by Swiss Post, Information Technology Services
 * ------------------------------------------------------------------------------------------------
 * $Id$
 * ------------------------------------------------------------------------------------------------
 */

package com.ecommerce.microcommerce.model;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;

/**
 * Class Product.
 * This represents TODO.
 *
 * @author gerbermi
 * @version $$Revision$$§
 */

//@JsonFilter("monFiltreDynamique")
@Entity
public class Product {
    @Id
    @GeneratedValue
    private int id;

    @Length(min=3, message = "Un produit doit avoir au minimum 3 caractères.")
    private String nom;

    @Min(value = 1, message = "Le montant minimal est de 1.")
    private int prix;
    //information que nous ne souhaitons pas exposer
    private int prixAchat;

    //constructeur par défaut
    public Product() {
    }

    //constructeur pour nos tests
    public Product(int id, String nom, int prix, int prixAchat) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.prixAchat = prixAchat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getPrixAchat() {
        return prixAchat;
    }

    public void setPrixAchat(int prixAchat) {
        this.prixAchat = prixAchat;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prix=" + prix +
                ", prixAchat=" + prixAchat +
                '}';
    }
}



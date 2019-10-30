package com.ecommerce.microcommerce.web.controller;

import com.ecommerce.microcommerce.dao.ProductDao;
import com.ecommerce.microcommerce.model.Product;
import com.ecommerce.microcommerce.web.exceptions.ProduitIntrouvableException;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Api(description = "API pour les opérations CRUD sur les produits.")
@RestController
public class ProductController {

    @Autowired
    private ProductDao productDao;

    //Récupérer la liste des produits
    @RequestMapping(value = "/Produits", method = RequestMethod.GET)
    public MappingJacksonValue listeProduits() {
        Iterable<Product> produits = productDao.findAll();

        SimpleBeanPropertyFilter monFiltre = SimpleBeanPropertyFilter.serializeAllExcept("prixAchat", "id");

        FilterProvider listDeNosFiltres = new SimpleFilterProvider().addFilter("monFiltreDynamique", monFiltre);

        MappingJacksonValue prododuitsFiltres = new MappingJacksonValue(produits);

        prododuitsFiltres.setFilters(listDeNosFiltres);

        return prododuitsFiltres;

    }

    //Récupérer un produit par son Id
    @ApiOperation(value = "Récupère un produit grâce à son ID à condition que celui-ci soit en stock!")
    @GetMapping(value = "/Produits/{id}")
    public Product afficherUnProduit(@PathVariable int id) throws ProduitIntrouvableException {
        Product produit = productDao.findById(id);
        if (produit == null) {
            throw new ProduitIntrouvableException("Le produit avec l'id " + id + " n'existe pas !");
        }
        return produit;
    }

    //ajouter un produit
    @ApiOperation(value = "Ajouter un produit dans la base de données!")
    @PostMapping(value = "/Produits")
    public ResponseEntity<Void> ajouterProduit(@Valid @RequestBody Product product) {

        Product productAdded = productDao.save(product);

        if (productAdded == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(productAdded.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    // supprimer produit
    @ApiOperation(value = "Supprimer un produit de la base de données!")
    @DeleteMapping(value = "/Produits/{id}")
    public void supprimerProduit(@PathVariable Product id) {
        productDao.delete(id);
    }

    // mise à jour produit produit
    @PutMapping(value = "/Produits")
    public void updateProduit(@RequestBody Product product) {
        productDao.save(product);
    }

    @GetMapping(value = "test/produits/{recherche}")
    public List<Product> testeDeRecherches(@PathVariable String recherche) {
        return productDao.findByNomLike("%" + recherche + "%");
    }

//    @GetMapping(value = "test/produits/{prixLimit}")
//    public List<Product> testeDeRequetes(@PathVariable int prixLimit) {
//        return productDao.chercherProduitCher(prixLimit);
//    }

}
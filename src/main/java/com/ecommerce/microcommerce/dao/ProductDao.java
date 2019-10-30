/*
 * ------------------------------------------------------------------------------------------------
 * Copyright 2014 by Swiss Post, Information Technology Services
 * ------------------------------------------------------------------------------------------------
 * $Id$
 * ------------------------------------------------------------------------------------------------
 */

package com.ecommerce.microcommerce.dao;

import com.ecommerce.microcommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Class ProductDao.
 * This represents TODO.
 *
 * @author gerbermi
 * @version $$Revision$$
 */
@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {
    Product findById(int id);

    List<Product> findByPrixGreaterThan(int prixLimit);

    @Query("select id, nom, prix from Product p where p.prix > :prixLimit")
    List<Product> chercherProduitCher(@Param("prixLimit") int prix);

    List<Product> findByNomLike(String recherche);

}

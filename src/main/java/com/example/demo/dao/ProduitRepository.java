package com.example.demo.dao;

import com.example.demo.entities.Produit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

public interface ProduitRepository extends JpaRepository<Produit,Long> {

    @Query("select page from Produit page where page.designation like :x")
    public Page<Produit> chercher(@Param("x") String key , Pageable pageable);
//
//    @Query("select designation,prix,quantitie from Produit where id=:i")
//    public Produit lookingfor(@Param("i") Long id);

}


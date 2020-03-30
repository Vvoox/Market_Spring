package com.example.demo;

import com.example.demo.dao.ProduitRepository;
import com.example.demo.entities.Produit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        ApplicationContext ctx =SpringApplication.run(DemoApplication.class, args);
        ProduitRepository produitRepository = ctx.getBean(ProduitRepository.class);
//        produitRepository.save(new Produit("ASUS 1",10000 ,6));
//        produitRepository.save(new Produit("HP 2",10584 ,2));
//        produitRepository.save(new Produit("SAMSUNG S10",10850 ,3));
//        produitRepository.save(new Produit("IPHONE 8",10698 ,8));

        produitRepository.findAll().forEach(p -> System.out.println(p.getDesignation() + p.getPrix() + p.getQuantitie()));
    }

}

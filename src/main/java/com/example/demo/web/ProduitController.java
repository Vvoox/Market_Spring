package com.example.demo.web;

import com.example.demo.dao.ProduitRepository;
import com.example.demo.entities.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class ProduitController  {

    @Autowired
    private ProduitRepository produitRepository;

    @RequestMapping(value="/index")
    public String index(Model model , @RequestParam(name="page",defaultValue = "0") int page ,
                        @RequestParam(name="size",defaultValue = "8") int size,
                        @RequestParam(name="key",defaultValue = "" ) String key){

        Page<Produit> pageproduits = produitRepository.chercher("%"+key+"%",PageRequest.of(page,size));

        model.addAttribute("ListProduits",pageproduits.getContent());
        int[] pages = new int[pageproduits.getTotalPages()];
        model.addAttribute("NombrePages",pages);
        model.addAttribute("size",size);
        model.addAttribute("pageCourante",page);
        model.addAttribute("mc",key);
        return "produits";
    }

    @RequestMapping(value="/delete" , method = RequestMethod.GET)
    public String delete(Long id , int page , int size ,String key ){
        produitRepository.deleteById(id);

        return "redirect:/index?page="+page+"&size="+size+"&key="+key;
    }



}

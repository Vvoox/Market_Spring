package com.example.demo.web;

import com.example.demo.dao.ProduitRepository;

import com.example.demo.entities.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Optional;



@Controller
public class ProduitController {

    @Autowired
    private ProduitRepository produitRepository;

    @RequestMapping(value="/user/index")
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

    @RequestMapping(value="/admin/delete" , method = RequestMethod.GET)
    public String delete(Long id , int page , int size ,String key ){
        produitRepository.deleteById(id);

        return "redirect:/admin/index?page="+page+"&size="+size+"&key="+key;
    }

    @RequestMapping(value = "/admin/form" , method = RequestMethod.GET)
    public String form(Model model){
        model.addAttribute("produit", new Produit());

        return "FormProduit";
    }

    @RequestMapping(value = "/admin/edit" , method = RequestMethod.GET)
    public String edit(Model model,Long id){
        Produit p = produitRepository.getOne(id);
        model.addAttribute("produit",p);
        return "EditProduit";
    }

    @RequestMapping(value = "/admin/save",method = RequestMethod.POST)
    public String save(Model model , @Valid Produit produit , BindingResult bindingResult){

        if(bindingResult.hasErrors())
        {
            return "FormProduit";
        }
        else
            {
        produitRepository.save(produit);
        return "Confirmation";
        }

    }

    @RequestMapping(value = "/admin/Confirmation" ,method = RequestMethod.GET)
    public String confirmation(){
        return "redirect:/admin/index";
    }

    @RequestMapping(value = "/")
    public String home(){
        return "redirect:/user/index";
    }

    @RequestMapping(value = "/user/403")
    public String accessDenied(){
        return "403";
    }

    @RequestMapping(value = "/admin/index")
    public String index2(){
        return "redirect:/user/index";
    }



}

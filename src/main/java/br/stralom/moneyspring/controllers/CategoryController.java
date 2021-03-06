/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.stralom.moneyspring.controllers;

import br.stralom.moneyspring.dao.CategoryDAO;
import br.stralom.moneyspring.entities.Category;
import br.stralom.moneyspring.entities.User;
import br.stralom.moneyspring.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Bruno Strano
 */
@Controller
@RequestMapping("/transactions/categories")
public class CategoryController {

    @Autowired
    private CategoryService catSVC;
    
    @RequestMapping("/form")
    public String formCategory() {

        return "transactions/categories/form";
    }

    @RequestMapping( method = RequestMethod.POST)
    public String saveCategory(@AuthenticationPrincipal User user, Category cat) {
        catSVC.save(user,cat);
        return "transactions/ok";
    }

}

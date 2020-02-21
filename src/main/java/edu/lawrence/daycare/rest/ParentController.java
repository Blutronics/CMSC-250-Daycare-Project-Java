/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.lawrence.daycare.rest;

import edu.lawrence.daycare.data.Parent;
import edu.lawrence.daycare.data.ParentDAO;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Khanh Toan
 */
@RestController
@RequestMapping("/parent")
@CrossOrigin(origins = "*")
public class ParentController {

    private ParentDAO parentDAO;

    public ParentController(ParentDAO dao) {
        this.parentDAO = dao;
    }

    @GetMapping(params = {"user"})
    public Parent findId(@RequestParam(value = "user") int user) {
        Parent parent = parentDAO.findById(user);
        return parent;

    }

    @PostMapping
    public int save(@RequestBody Parent parent) {
        return parentDAO.save(parent);
    }

    @PutMapping
    public int update(@RequestBody Parent parent) {
        return parentDAO.update(parent);
    }

}

/** Mostly correct, with one problem in your dao class. See the comment there for details.
 * 
 *  95/100
 */

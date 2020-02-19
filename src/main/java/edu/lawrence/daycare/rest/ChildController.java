/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.lawrence.daycare.rest;

import edu.lawrence.daycare.data.Child;
import edu.lawrence.daycare.data.ChildDAO;
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
@RequestMapping("/children")
@CrossOrigin(origins = "*")
public class ChildController {

    private ChildDAO childDAO;

    public ChildController(ChildDAO dao) {
        childDAO = dao;
    }

    @PostMapping
    public int save(@RequestBody Child child) {
        return childDAO.save(child);
    }

    @GetMapping(params = {"parent"})
    public Child findId(@RequestParam(value = "parent") int parentID) {
        Child parent = childDAO.findByParentId(parentID);
        return parent;
    }

    @PutMapping
    public int update(@RequestBody Child child) {
        return childDAO.update(child);
    }

}

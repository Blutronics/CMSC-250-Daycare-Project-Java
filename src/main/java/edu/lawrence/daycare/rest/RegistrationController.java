/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.lawrence.daycare.rest;

import edu.lawrence.daycare.data.registration;
import edu.lawrence.daycare.data.registrationDAO;
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
@RequestMapping("/registration")
@CrossOrigin(origins = "*")
public class RegistrationController {

    private registrationDAO registrationDAO;

    public RegistrationController(registrationDAO dao) {
        registrationDAO = dao;
    }

    @PostMapping
    public int save(@RequestBody registration r) {
        return registrationDAO.save(r);
    }
}
